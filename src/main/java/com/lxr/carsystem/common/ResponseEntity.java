package com.lxr.carsystem.common;

import java.io.Serializable;

/**
 * @Author: LinXueRui
 * @Date: 2019/5/13 18:02
 * @Desc: 统一的响应
 */

public class ResponseEntity implements Serializable {

    public static final int SUCCESS_CODE = 0;
    private static final String SUCCESS_MSG = "数据请求成功";
    public static final int FAIL_CODE = 1;
    private static final String FAIL_MSG = "数据请求失败";

    public ResponseEntity() {}

    /**
     * 状态码
     */
    private Integer code;
    /**
     * 返回信息
     */
    private String msg;
    /**
     * 响应内容
     */
    private Object data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    private ResponseEntity(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 成功的响应
     * @param data
     * @param msg
     * @return
     */
    public static ResponseEntity successResponse(Object data, String msg) {
        return new ResponseEntity(SUCCESS_CODE,msg,data);
    }

    /**
     * 成功的响应
     * @param data
     * @return
     */
    public static ResponseEntity successResponse(Object data) {
        return new ResponseEntity(SUCCESS_CODE,SUCCESS_MSG,data);
    }

    /**
     * 失败的响应 数据
     * @return
     */
    public static ResponseEntity failResponse(Object data, String msg) {
        return new ResponseEntity(FAIL_CODE,msg,data);
    }

    /**
     * 失败的响应
     * @param data
     * @return
     */
    public static ResponseEntity failResponse(Object data) {
        return new ResponseEntity(FAIL_CODE,FAIL_MSG,data);
    }

    /**
     * 失败的响应 数据
     * @return
     */
    public static ResponseEntity failResponse(String msg) {
        return new ResponseEntity(FAIL_CODE,msg,null);
    }

    /**
     * 失败的响应 无数据
     * @return
     */
    public static ResponseEntity failResponse() {
        return new ResponseEntity(FAIL_CODE,FAIL_MSG,null);
    }
}

