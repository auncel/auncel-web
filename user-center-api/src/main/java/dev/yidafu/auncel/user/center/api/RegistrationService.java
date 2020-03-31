package dev.yidafu.auncel.user.center.api;

import dev.yidafu.auncel.user.center.api.common.PlainResult;
import dev.yidafu.auncel.user.center.api.common.dto.user.UserDTO;
import dev.yidafu.auncel.user.center.domain.User;

public interface RegistrationService {
    public PlainResult<User> email(UserDTO user);
}
