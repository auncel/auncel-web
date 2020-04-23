package dev.yidafu.auncel.web.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AuthLogDto implements Serializable {
    private long id;

    private String loginIp = "0.0.0.0";

    private UserDto logUser;

    private String title;

    private String content;

    private Date createdAt;

    private Date updatedAt;
}
