package com.yzx.filestorage.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "okhttp")
@Data
public class OkHttpProperties {

    /**
     * 连接超时时间（秒）
     */
    private long connectTimeout = 15;

    /**
     * 读取超时时间（秒）
     */
    private long readTimeout = 15;

    /**
     * 最大空闲连接数
     */
    private int maxIdleConnections = 200;

    /**
     * 连接存活时间（分钟）
     */
    private long keepAliveDuration = 5;

    /**
     * 是否允许重试
     */
    private boolean retryOnConnectionFailure = false;

    /**
     * 是否允许重定向
     */
    private boolean followRedirects = true;

    private boolean allowProxy = false;
    private String PROXY_HOST;
    private Integer PROXY_PORT;
}
