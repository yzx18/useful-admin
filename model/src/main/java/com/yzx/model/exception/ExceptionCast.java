package com.yzx.model.exception;


import com.yzx.model.ResultCode;

/**
 * 抛出自定义异常的操作类
 */
public class ExceptionCast {

    public static void cast(ResultCode resultCode){
        throw new CustomException(resultCode);
    }
}