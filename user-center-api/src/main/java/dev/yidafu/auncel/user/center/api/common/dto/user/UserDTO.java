package dev.yidafu.auncel.user.center.api.common.dto.user;

import lombok.Data;

@Data
public class UserDTO {
    private String username;
    private String password;
    private String email;
}
