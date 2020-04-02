package dev.yidafu.auncel.user.center.api;

import dev.yidafu.auncel.common.api.response.PlainResult;
import dev.yidafu.auncel.user.center.domain.User;
import dev.yidafu.auncel.user.center.api.dto.UserDTO;

public interface RegistrationService {
    public PlainResult<User> email(UserDTO user);
}
