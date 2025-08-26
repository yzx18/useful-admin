package com.yzx.filestorage.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * Description:
 *
 * @author: aoxiang
 * @create: 2025-03-11 14:47
 **/

@Component
@RefreshScope
@Data
@ConfigurationProperties(prefix = "file-storage")
public class FileLoadProperties {

    private String uploadBaseDir;

    private String downloadBaseUrl;
}
