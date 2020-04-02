package dev.yidafu.auncel.user.center.api;

import dev.yidafu.auncel.common.api.response.PlainResult;
import dev.yidafu.auncel.user.center.api.dto.UserDTO;
import dev.yidafu.auncel.user.center.api.dto.UserResponseDTO;

public interface LoginService {
    public PlainResult<UserResponseDTO> email(UserDTO userDTO);
}
