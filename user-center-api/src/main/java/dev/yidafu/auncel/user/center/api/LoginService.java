package dev.yidafu.auncel.user.center.api;

import dev.yidafu.auncel.user.center.common.response.PlainResult;
import dev.yidafu.auncel.user.center.common.dto.UserDTO;
import dev.yidafu.auncel.user.center.common.dto.UserResponseDTO;

public interface LoginService {
    public PlainResult<UserResponseDTO> email(UserDTO userDTO);
}
