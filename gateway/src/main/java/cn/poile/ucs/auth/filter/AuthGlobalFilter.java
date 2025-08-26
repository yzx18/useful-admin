package cn.poile.ucs.auth.filter;

import cn.poile.ucs.auth.config.RestErrorResponse;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

/**
 * 网关全局过滤器：校验 JWT 令牌是否过期（基于本地公钥验签）
 */
@Component
@Slf4j
public class AuthGlobalFilter implements GlobalFilter, Ordered {

    @Autowired
    private TokenStore tokenStore; // 注入 JwtTokenStore（本地解析 JWT）

    private static List<String> whiteList = new ArrayList<>();
    private final AntPathMatcher pathMatcher = new AntPathMatcher(); // 路径匹配器

    // 静态加载白名单（从 security-whitelist.properties 读取）
    static {
        try {
            InputStream is = AuthGlobalFilter.class.getResourceAsStream("/security-whitelist.properties");
            if (is == null) {
                log.error("未找到资源文件：/security-whitelist.properties");
            }
            Properties props = new Properties();
            props.load(is);
            Set<String> urls = props.stringPropertyNames();
            whiteList.addAll(urls);
            log.info("加载白名单成功，共 {} 条规则：{}", whiteList.size(), whiteList);
        } catch (Exception e) {
            log.error("加载白名单失败", e);
        }
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String requestUrl = exchange.getRequest().getPath().value();

        // 1. 白名单路径直接放行
        for (String whiteUrl : whiteList) {
            if (pathMatcher.match(whiteUrl, requestUrl)) {
                log.debug("白名单路径放行：{}", requestUrl);
                return chain.filter(exchange);
            }
        }

        // 2. 提取令牌（Authorization: Bearer {token}）
        String token = getTokenFromHeader(exchange);
        if (StringUtils.isBlank(token)) {
            return buildErrorResponse(exchange, "未提供认证令牌（Authorization 头缺失或格式错误）");
        }

        // 3. 校验令牌是否过期（核心：本地解析 JWT，无远程请求）
        try {
            // JwtTokenStore 会用本地公钥验签并解析 JWT，直接判断过期状态
            boolean isExpired = tokenStore.readAccessToken(token).isExpired();
            if (isExpired) {
                return buildErrorResponse(exchange, "认证令牌已过期，请重新获取");
            }
            // 令牌有效，继续路由
            return chain.filter(exchange);
        } catch (InvalidTokenException e) {
            log.warn("令牌无效（签名错误或格式不正确）：{}", token, e);
            return buildErrorResponse(exchange, "认证令牌无效（签名错误或格式不正确）");
        } catch (Exception e) {
            log.error("令牌校验过程异常", e);
            return buildErrorResponse(exchange, "令牌校验失败，请联系管理员");
        }
    }

    /**
     * 从请求头提取令牌
     */
    private String getTokenFromHeader(ServerWebExchange exchange) {
        String authHeader = exchange.getRequest().getHeaders().getFirst("Authorization");
        if (StringUtils.isBlank(authHeader) || !authHeader.startsWith("Bearer ")) {
            return null;
        }
        String[] parts = authHeader.split(" ");
        return parts.length == 2 ? parts[1].trim() : null;
    }

    /**
     * 构建 401 错误响应
     */
    private Mono<Void> buildErrorResponse(ServerWebExchange exchange, String message) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED); // 401 未授权
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");

        RestErrorResponse error = new RestErrorResponse(message);
        byte[] body = JSON.toJSONString(error).getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = response.bufferFactory().wrap(body);
        return response.writeWith(Mono.just(buffer));
    }

    /**
     * 过滤器优先级（最高，确保在路由前执行）
     */
    @Override
    public int getOrder() {
        return -100;
    }
}