package dev.yidafu.auncel.user.center.api;

import dev.yidafu.auncel.user.center.common.response.PlainResult;
import dev.yidafu.auncel.user.center.domain.User;
import dev.yidafu.auncel.user.center.common.dto.UserDTO;

public interface RegistrationService {
    public PlainResult<User> email(UserDTO user);
}
