package com.yzx.filestorage.config;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

@Configuration
public class OkHttpConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(OkHttpConfig.class);

    @Autowired
    private OkHttpProperties properties;

    @Bean
    public X509TrustManager x509TrustManager() {
        return new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) {
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        };
    }

    @Bean
    public SSLSocketFactory sslSocketFactory() {
        try {
            TrustManager[] trustManagers = new TrustManager[]{x509TrustManager()};
            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustManagers, new SecureRandom());
            return sslContext.getSocketFactory();
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            LOGGER.error("SSLContext initialization failed", e);
        }
        return null;
    }

    @Bean
    public ConnectionPool pool() {
        return new ConnectionPool(properties.getMaxIdleConnections(), properties.getKeepAliveDuration(), TimeUnit.MINUTES);
    }

    @Bean(name = "okHttpClientWithProxy")
    public OkHttpClient okHttpClientWithProxy() {
        Proxy PROXY = null;
        if (properties.isAllowProxy()) {
            PROXY = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(properties.getPROXY_HOST(), properties.getPROXY_PORT()));
        }

        return new OkHttpClient.Builder()
                .sslSocketFactory(sslSocketFactory(), x509TrustManager())
                .hostnameVerifier((s, sslSession) -> true)
                .retryOnConnectionFailure(properties.isRetryOnConnectionFailure())
                .connectionPool(pool())
                .connectTimeout(properties.getConnectTimeout(), TimeUnit.SECONDS)
                .readTimeout(properties.getReadTimeout(), TimeUnit.SECONDS)
                .followRedirects(properties.isFollowRedirects())
                .proxy(PROXY)
                .build();
    }

    @Bean
    @Primary
    public OkHttpClient okHttpClient() {
        return new OkHttpClient.Builder()
                .sslSocketFactory(sslSocketFactory(), x509TrustManager())
                .hostnameVerifier((s, sslSession) -> true)
                .retryOnConnectionFailure(properties.isRetryOnConnectionFailure())
                .connectionPool(pool())
                .connectTimeout(properties.getConnectTimeout(), TimeUnit.SECONDS)
                .readTimeout(properties.getReadTimeout(), TimeUnit.SECONDS)
                .followRedirects(properties.isFollowRedirects())
                .build();
    }
}
