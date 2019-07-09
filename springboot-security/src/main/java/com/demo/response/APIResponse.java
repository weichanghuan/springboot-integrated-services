package com.demo.response;


import java.io.Serializable;

/**
 * @Author: wch
 * @Description: 响应基类
 * @Date: 2019-07-09 09:41
 */
public class APIResponse<T> implements Serializable {

    private static final long serialVersionUID = 77027745177829703L;

    public interface Codes {

        int SUCCESS = 200;

        String SUCCESS_MSG = "OK";

        int REQ_INVALID_PARAM = 400;

        int INTERNAL_ERROR = 500;
    }


    private T data;

    private Integer code;

    private String msg;


    public T getData() {
        return data;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    private APIResponse<T> setOkayData(T data) {
        return setData(data, Codes.SUCCESS, "");
    }

    private APIResponse<T> setData(T data, int code, String msg) {
        this.data = data;
        this.code = code;
        this.msg = msg;
        return this;
    }

    private APIResponse<T> setErrorData(Integer code, String errorMsg) {
        return setData(null, code, errorMsg);
    }

    private APIResponse<T> setErrorData(Integer code, String errorMsg, T data) {
        return setData(data, code, errorMsg);
    }

    public static <T> APIResponse<T> okay(T data) {
        return new APIResponse<T>()
                .setOkayData(data);
    }

    public static <T> APIResponse<T> error(Integer code, String errorMsg) {
        return new APIResponse<T>()
                .setErrorData(code, errorMsg);
    }

    public static <T> APIResponse<T> error(Integer code, String errorMsg, T data) {
        return new APIResponse<T>()
                .setErrorData(code, errorMsg, data);
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"data\":")
                .append(data);
        sb.append(",\"code\":")
                .append(code);
        sb.append(",\"msg\":\"")
                .append(msg).append('\"');
        sb.append('}');
        return sb.toString();
    }
}

