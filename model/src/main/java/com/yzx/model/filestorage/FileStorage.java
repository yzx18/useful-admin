package com.yzx.model.filestorage;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 文件存储表
 * </p>
 *
 * @author 翱翔
 * @since 2025-03-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("file_storage")
public class FileStorage implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 原文件名称
     */
    private String fileName;

    /**
     * 文件类型 (images, videos, documents 等)
     */
    private String fileType;

    /**
     * 文件哈希 (SHA-256 用于去重)
     */
    private String fileHash;

    /**
     * 文件存储路径
     */
    private String filePath;

    /**
     * 上传用户ID (可选, 关联用户表)
     */
    private Long uploader;

    /**
     * 文件大小bite
     */
    private Long fileSize;

    private String fileSystemType;

    /**
     * 上传时间
     */
    @TableField(value = "created_at", fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createdAt;

    @TableField(value = "updated_at", fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updatedAt;
}
