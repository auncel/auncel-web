package dev.yidafu.auncel.user.center.biz;

import dev.yidafu.auncel.user.center.api.RegistrationService;
import dev.yidafu.auncel.user.center.api.common.ErrorCodes;
import dev.yidafu.auncel.user.center.api.common.IdentifierType;
import dev.yidafu.auncel.user.center.api.common.PlainResult;
import dev.yidafu.auncel.user.center.api.common.dto.user.UserDTO;
import dev.yidafu.auncel.user.center.dal.UserAuthRepository;
import dev.yidafu.auncel.user.center.dal.UserRepository;
import dev.yidafu.auncel.user.center.domain.User;
import dev.yidafu.auncel.user.center.domain.UserAuth;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Service
public class RegistrationServiceImpl implements RegistrationService {
    @Autowired
    PasswordBox passwordBox;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserAuthRepository userAuthRepository;

    @Override
    public PlainResult<User> email(UserDTO userDTO) {

        Optional<String> passwdOpt = this.passwordBox.encode(userDTO.getPassword());
        if (passwdOpt.isPresent()) {
            User user = new User();
            user.setUsername(userDTO.getUsername());
            UserAuth userAuth = new UserAuth();
            userAuth.setIdentityType(IdentifierType.EMAIL);
            userAuth.setIdentifier(userDTO.getEmail());
            userAuth.setCredential(passwdOpt.get());

            userRepository.save(user);
            userAuth.setAuthUser(user);
            userAuthRepository.save(userAuth);
            return (PlainResult<User>) PlainResult.make(user, "注册用户成功");
        }
        return (PlainResult<User>) PlainResult.error(ErrorCodes.ENCODE_FAILED, "秘密加密失败");
    }
}
