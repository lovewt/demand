package com.dcmd.service.demand.utils;

public class Result<T>{
    private Integer code;
    private String message;
    private T data;
    public Integer getCode() {
        return code;
    }
    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
    @Override
    public String toString() {
        return "{\"code\": "+code+",\"message\": \""+message+"\",\"data\": "+data+"}";
    }

    public Result(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
    public Result(String message, T data) {
        this.code = 500;
        this.message = message;
        this.data = data;
    }

    public Result(T data) {
        this.code = 200;
        this.message = "请求成功!";
        this.data = data;
    }
    public Result() {
        this.code = 200;
        this.message = "请求成功!";
    }

    public Result(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
