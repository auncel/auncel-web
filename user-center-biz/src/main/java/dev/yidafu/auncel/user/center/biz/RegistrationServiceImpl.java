package dev.yidafu.auncel.user.center.biz;

import dev.yidafu.auncel.common.api.response.PlainResult;
import dev.yidafu.auncel.common.api.response.PlainResults;
import dev.yidafu.auncel.common.api.SessionService;
import dev.yidafu.auncel.user.center.api.RegistrationService;
import dev.yidafu.auncel.user.center.api.common.ErrorCodes;
import dev.yidafu.auncel.user.center.api.common.IdentifierType;
import dev.yidafu.auncel.user.center.api.dto.UserDTO;
import dev.yidafu.auncel.user.center.biz.uuid.SessionIdGenerator;
import dev.yidafu.auncel.user.center.dal.UserAuthRepository;
import dev.yidafu.auncel.user.center.dal.UserRepository;
import dev.yidafu.auncel.user.center.domain.User;
import dev.yidafu.auncel.user.center.domain.UserAuth;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * invoke RegistrationService.email({username: "test1", password: "encodepassword", email: "xx@qq.com"})
 */
@Service
public class RegistrationServiceImpl implements RegistrationService {

    public static Log logger = LogFactory.getLog(RegistrationServiceImpl.class);

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
        logger.info("email:  " + userDTO);
        UserAuth userAuthIfExist = userAuthRepository.findByIdentityTypeAndIdentifier(
                "email",
                userDTO.getEmail()
        );
        if (userAuthIfExist != null) {
            User user = new User();
            user.setUsername(userDTO.getUsername());
            UserAuth userAuth = new UserAuth();
            userAuth.setIdentityType(IdentifierType.EMAIL);
            userAuth.setIdentifier(userDTO.getEmail());
            userAuth.setCredential(userDTO.getPassword());
            userRepository.save(user);
            logger.info("userRepository.save(" + user + ")");
            userAuth.setAuthUser(user);
            userAuthRepository.save(userAuth);
            logger.info("userAuthRepository.save(" + userAuth + ")");

            String sessionId = seesionIdGenerator.getSessionId();
            logger.info("new SESSIONID: " + sessionId);
            PlainResult<Boolean> result = sesssionService.create(sessionId, userDTO);
            logger.info("sesssionService insert data: " + result);
            return PlainResults.success(user, "注册用户成功");
        }
        return PlainResults.error(ErrorCodes.ALREADY_REGISTE, "该用户已经注册过了");
    }
}
