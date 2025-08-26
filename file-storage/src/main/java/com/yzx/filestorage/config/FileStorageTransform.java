package com.yzx.filestorage.config;


import com.yzx.model.filestorage.FileDetailResponse;
import com.yzx.model.filestorage.FileStorage;
import org.mapstruct.Mapper;

/**
 * Description:
 *
 * @author: aoxiang
 * @create: 2025-03-12 13:16
 **/

@Mapper(componentModel = "spring")
public interface FileStorageTransform {
    FileDetailResponse toFileDetailResponse(FileStorage entity);
}
