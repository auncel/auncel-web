package dev.yidafu.auncel.web.advice;

import dev.yidafu.auncel.web.common.exception.AuncelBaseException;
import dev.yidafu.auncel.web.common.response.PlainResult;
import dev.yidafu.auncel.web.common.response.PlainResults;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class AuncelControllerAdvice {

    @ResponseBody
    @ExceptionHandler(AuncelBaseException.class)
    private PlainResult handleCustomException(AuncelBaseException exception) {
        return PlainResults.error(exception.getCode(), exception.getMsg());
    }
}
