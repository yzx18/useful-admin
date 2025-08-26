//package cn.poile.ucs.auth.config;
//
//import lombok.extern.log4j.Log4j2;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.core.io.Resource;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
//import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.stream.Collectors;
//
///**
// * 资源服务配置
// * @author: yaohw
// * @create: 2019-10-08 10:04
// **/
//@Configuration
////启用资源服务
//@EnableResourceServer
////启用方法级权限控制
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//@Log4j2
//public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
//
//    private static final String RESOURCE_ID = "auth-server";
//    /**
//     * 公钥
//     */
//    private static final String PUBLIC_KEY = "publickey.txt";
//
//    /**
//     *  配置资源接口安全，http.authorizeRequests()针对的所有url，但是由于登录页面url包含在其中，这里配置会进行token校验，校验不通过返回错误json，
//     *  而授权码模式获取code时需要重定向登录页面，重定向过程并不能携带token，所有不能用http.authorizeRequests()，
//     *  而是用requestMatchers().antMatchers("")，这里配置的是需要资源接口拦截的url数组
//     * @param http
//     * @return void
//     */
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        http    //配置需要保护的资源接口
//                .requestMatchers().antMatchers("/user", "/test/need_token", "/update", "/logout", "/test/need_admin", "/test/scope")
//                .and().authorizeRequests().anyRequest().authenticated();
//    }
//
//    /**定义JwtTokenStore，使用jwt令牌*/
//    @Bean(name = "resourceServerTokenStore")
//    public TokenStore tokenStore(@Qualifier("resourceServerJwtConverter") JwtAccessTokenConverter jwtConverter) {
//        return new JwtTokenStore(jwtConverter);
//    }
//
//    /**定义JJwtAccessTokenConverter，使用jwt令牌*/
//    @Bean(name = "resourceServerJwtConverter")
//    public JwtAccessTokenConverter jwtAccessTokenConverter() {
//        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//        String pubKey = getPubKey();
//        //设置校验的公钥
//        converter.setVerifierKey(getPubKey());
//        log.debug("加载的公钥: \n{}", pubKey); // 打印公钥内容
//        return converter;
//    }
//
//    /**
//     * 获取非对称加密公钥 Key
//     * @return 公钥 Key
//     */
//    private String getPubKey() {
//        Resource resource = new ClassPathResource(PUBLIC_KEY);
//        try {
//            InputStreamReader inputStreamReader = new InputStreamReader(resource.getInputStream());
//            BufferedReader br = new BufferedReader(inputStreamReader);
//            String pubKey = br.lines().collect(Collectors.joining("\n"));
//            log.debug("读取的公钥内容: \n{}", pubKey); // 打印完整公钥
//            return pubKey;
//        } catch (IOException ioe) {
//            log.error("加载公钥失败", ioe); // 补充日志，捕获异常
//            return null;
//        }
//    }
//
//    /**
//     * 这个是跟服务绑定的，注意要跟client配置一致，如果客户端没有，则不能访问
//     * @param resources
//     * @throws Exception
//     */
//    @Override
//    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
//        resources.resourceId(RESOURCE_ID).stateless(true);
//    }
//
//
//}
