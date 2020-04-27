package dev.yidafu.auncel.web.common.response;

import dev.yidafu.auncel.web.common.exception.ResponseCode;

public class PlainResults {

    public static <T> PlainResult<T> success(T data) {
        PlainResult result = new PlainResult(true, data, 1, "请求成功");
        return result;
    }

    public static <T> PlainResult<T> success(T data, String msg) {
        PlainResult result = new PlainResult(true, data, 1, msg);
        return result;
    }

    public static PlainResult error(Integer code, String msg) {
        PlainResult result = new PlainResult(false, null, code, msg);
        return result;
    }

    public static PlainResult error(ResponseCode responseCode) {
        PlainResult result = new PlainResult(false, null, responseCode.getCode(), responseCode.getMsg());
        return result;
    }

    public static <T> PlainResult success(T data, Integer code, String msg) {
        PlainResult<T> result = PlainResults.success(data, msg);
        result.setCode(code);
        return result;
    }
}
