package com.yzx.model;

/**
 * @program: xz-framework-parent-reversion
 * @description: 对结果响应的格式的封装
 * @author: wdw
 * @create: 2020-01-20 23:13
 **/
public interface ResultCode {


    /**
     * 操作代码
     */
    int code();
    /**
     * 提示信息
     */
    String msg();

}
