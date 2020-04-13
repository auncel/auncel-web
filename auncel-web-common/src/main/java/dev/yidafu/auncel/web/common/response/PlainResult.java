package dev.yidafu.auncel.web.common.response;

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
}
