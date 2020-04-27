package dev.yidafu.auncel.web.common.exception;

import dev.yidafu.auncel.web.common.ErrorCodes;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class AuncelBaseException extends RuntimeException implements Serializable {

    private int code;

    private String msg;

    public AuncelBaseException() {
        this.code = ErrorCodes.UNKNOWN_FAILED;
        this.msg = msg;
    }

    public AuncelBaseException(ResponseCode responseCode) {
        this.code = responseCode.getCode();
        this.msg = responseCode.getMsg();
    }
}
