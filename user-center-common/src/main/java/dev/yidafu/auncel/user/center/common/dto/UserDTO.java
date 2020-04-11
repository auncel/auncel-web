package dev.yidafu.auncel.user.center.common.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor

@Data
public class UserDTO implements Serializable {
    private  Long id;
    private String username;
    private  String realname;
    private String password;
    private String email;
    private String slogan;
    private String school;
    private String sessionId;
}
