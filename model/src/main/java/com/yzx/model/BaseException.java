package com.yzx.model;

import lombok.Data;

/**
 * Description:
 *
 * @author: aoxiang
 * @create: 2024-12-21 16:47
 **/

@Data
public class BaseException extends RuntimeException{
    private String code;
    private String msg;
    public BaseException(String code, String msg){
        super();
        this.code = code;
        this.msg = msg;
    }
    public BaseException(ErrorCodeEnum errorCodeEnum){
        super();
        this.msg = errorCodeEnum.getDesc();
        this.code = errorCodeEnum.getCode();
    }
}
