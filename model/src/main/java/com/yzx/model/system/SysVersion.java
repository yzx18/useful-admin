package com.yzx.model.system;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

/**
 * @program: xz-framework-parent
 * @description: 版本实体类
 * @author: Mr.Pan
 * @create: 2020-03-20 11:34
 */
@Data
public class SysVersion {

    /**
     * id
     */
    private Integer id;

    /**
     * 下载地址
     */
    private String address;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 版本号
     */
    private String version;

    /**
     * apk名称
     */
    private String apkName;

    /**
     * 状态
     */
    private String status;

    /**
     * apk大小
     */
    private String size;

    /**
     * 修改时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String updateTime;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 更新说明
     */
    private String note;
}
