package dev.yidafu.auncel.user.center.api.dto;

import dev.yidafu.auncel.user.center.domain.User;
import lombok.Data;

@Data
public class UserResponseDTO {
    private Boolean isLogin;
    private String username;
    private String realname;
    private String avatar;
    private String slogan;
    private String email;
    private String github;
    private String hdu;

    public static UserResponseDTO merge(UserResponseDTO userResponseDTO, User user) {
        userResponseDTO.setUsername(user.getUsername());
        userResponseDTO.setRealname(user.getRealname());
        userResponseDTO.setAvatar(user.getAvatar());
        userResponseDTO.setSlogan(user.getSlogan());
        return userResponseDTO;
    }

    public static UserResponseDTO merge(User user) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setUsername(user.getUsername());
        userResponseDTO.setRealname(user.getRealname());
        userResponseDTO.setAvatar(user.getAvatar());
        userResponseDTO.setSlogan(user.getSlogan());
        return userResponseDTO;
    }
}
