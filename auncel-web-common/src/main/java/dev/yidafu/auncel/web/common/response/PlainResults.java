package dev.yidafu.auncel.web.common.response;

import dev.yidafu.auncel.web.common.response.PlainResult;

public class PlainResults {

  public static <T> PlainResult<T> success(T data, String msg) {
    PlainResult result = new PlainResult(true, data, 1, msg);
    return result;
  }

  public static PlainResult error(Integer code, String msg) {
      PlainResult result = new PlainResult();
      result.setCode(code);
      result.setMsg(msg);
      result.setSuccess(false);
      return result;
  }

  public static <T> PlainResult success(T data, Integer code, String msg) {
      PlainResult<T> result = PlainResults.success(data, msg);
      result.setCode(code);
      return result;
  }
}
