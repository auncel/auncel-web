package dev.yidafu.auncel.user.center.api.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserDTO implements Serializable {
    private String username;
    private String password;
    private String email;
}
