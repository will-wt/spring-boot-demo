package com.will.demo.ultimate.common;

/**
 * @author Will.WT
 * @date 2022/10/21 00:19
 */
public class Result<T> {

    private int code;
    private String message;
    private T data;

    public Result() {
        this.code = 200;
        this.message = "success";
    }
    public Result(T data) {
        this.code = 200;
        this.message = "success";
        this.data = data;
    }

    public Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public boolean hasSucceed(){
        return this.code == 200;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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
}
