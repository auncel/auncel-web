package dev.yidafu.auncel.user.center.biz;

import dev.yidafu.auncel.common.api.response.PlainResult;
import dev.yidafu.auncel.common.api.response.PlainResults;
import dev.yidafu.auncel.common.api.SessionService;
import dev.yidafu.auncel.common.api.response.PlainResults;
import dev.yidafu.auncel.user.center.api.RegistrationService;
import dev.yidafu.auncel.user.center.api.common.IdentifierType;
import dev.yidafu.auncel.user.center.api.dto.UserDTO;
import dev.yidafu.auncel.user.center.biz.uuid.SessionIdGenerator;
import dev.yidafu.auncel.user.center.dal.UserAuthRepository;
import dev.yidafu.auncel.user.center.dal.UserRepository;
import dev.yidafu.auncel.user.center.domain.User;
import dev.yidafu.auncel.user.center.domain.UserAuth;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * invoke RegistrationService.email({username: "test1", password: "encodepassword", email: "xx@qq.com"})
 */
@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    SessionIdGenerator seesionIdGenerator;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserAuthRepository userAuthRepository;

    @Reference
    SessionService sesssionService;

    @Override
    public PlainResult<User> email(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        UserAuth userAuth = new UserAuth();
        userAuth.setIdentityType(IdentifierType.EMAIL);
        userAuth.setIdentifier(userDTO.getEmail());
        userAuth.setCredential(userDTO.getPassword());

        userRepository.save(user);
        userAuth.setAuthUser(user);
        userAuthRepository.save(userAuth);
        String sessionId = seesionIdGenerator.getSessionId();
        PlainResult<Boolean> result = sesssionService.create(sessionId, userDTO);
        System.out.println("======================> " + result);
        return (PlainResult<User>) PlainResults.success(user, "注册用户成功");
    }
}
