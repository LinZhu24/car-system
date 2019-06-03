package com.lxr.carsystem.tool;

/**
 * @Author: LinXueRui
 * @Date: 2019/3/11 19:01
 * @Desc:
 */
public class Result {
    private Integer code;
    private String msg;
    private boolean success;
    private Object object;

    public Result(){}

    public Result(Integer code,String msg,boolean success){
        this.code = code;
        this.msg = msg;
        this.success = success;
    }

    public static Result success(Object object){
        Result result = new Result(200,"数据接收成功",true);
        result.setObject(object);
        return result;
    }

    public static Result success(String msg,Object object){
        Result result = new Result(200,msg,true);
        result.setObject(object);
        return result;
    }

    public static Result fail(Object object){
        Result result = new Result(100,"数据接收失败",false);
        result.setObject(object);
        return result;
    }

    public static Result fail(String msg,Object object){
        Result result = new Result(200,msg,true);
        result.setObject(object);
        return result;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

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

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
