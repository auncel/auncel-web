package dev.yidafu.auncel.user.center.api.common;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlainResult<T> implements Serializable {
    private Boolean success;
    private T data = null;
    private Integer code = -1;
    private String msg = "";
    public static PlainResult<?> make(Object data, String msg) {
        PlainResult result = new PlainResult(true, data, 1, msg);
        return result;
    }

    public static PlainResult<?> error(int code, String msg) {
        PlainResult result = new PlainResult();
        result.setCode(code);
        result.setMsg(msg);
        result.setSuccess(false);
        return result;
    }
}
