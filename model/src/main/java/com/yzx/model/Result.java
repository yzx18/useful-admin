package com.yzx.model;

import lombok.Data;

@Data
public class Result<T> {
    // 是否成功
    private Boolean success;
    // 状态码
    private String code;
    // 提示信息
    private String msg;
    // 数据
    private T data;

    public Result() {

    }

    private Result(Boolean success, String code, String msg, T data) {
        this.success = success;
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Boolean isSuccess() {
        return success;
    }

    public static <T> Result<T> success() {
        return new Result<>(true, ErrorCodeEnum.SUCCESS.getCode(), ErrorCodeEnum.SUCCESS.getDesc(), null);
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(true, ErrorCodeEnum.SUCCESS.getCode(), ErrorCodeEnum.SUCCESS.getDesc(), data);
    }

    public static Result<String> error(Exception e) {
        Result<String> result = new Result<>();
        result.setSuccess(false);
        result.setCode(ErrorCodeEnum.SYSTEM_ERROR.getCode());
        result.setMsg(e.getMessage());
        result.setData(null);
        return result;
    }
    public static Result<String> error(BaseException e) {
        Result<String> result = new Result<>();
        result.setMsg(e.getMsg());
        result.setCode(e.getCode());
        result.setSuccess(false);
        result.setData(null);
        return result;
    }

    // 其他异常处理方法返回的结果
    public static Result<String> error(ErrorCodeEnum errorEnum) {
        Result<String> result = new Result<>();
        result.setMsg(errorEnum.getDesc());
        result.setCode(errorEnum.getCode());
        result.setSuccess(false);
        result.setData(null);
        return result;
    }

    public static Result<String> error(String code, String msg) {
        Result<String> result = new Result<>();
        result.setMsg(msg);
        result.setCode(code);
        result.setSuccess(false);
        result.setData(null);
        return result;
    }
}
