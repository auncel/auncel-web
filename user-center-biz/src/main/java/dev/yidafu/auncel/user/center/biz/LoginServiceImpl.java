package dev.yidafu.auncel.user.center.biz;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.yidafu.auncel.common.api.SessionService;
import dev.yidafu.auncel.common.api.response.PlainResult;
import dev.yidafu.auncel.common.api.response.PlainResults;
import dev.yidafu.auncel.user.center.api.LoginService;
import dev.yidafu.auncel.user.center.api.common.ErrorCodes;
import dev.yidafu.auncel.user.center.api.common.RegisterType;
import dev.yidafu.auncel.user.center.api.dto.UserDTO;
import dev.yidafu.auncel.user.center.api.dto.UserResponseDTO;
import dev.yidafu.auncel.user.center.biz.uuid.SessionIdGenerator;
import dev.yidafu.auncel.user.center.dal.UserAuthRepository;
import dev.yidafu.auncel.user.center.domain.AuthLog;
import dev.yidafu.auncel.user.center.domain.User;
import dev.yidafu.auncel.user.center.domain.UserAuth;
import netscape.javascript.JSObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.dubbo.common.logger.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class LoginServiceImpl implements LoginService {

    public static Log logger = LogFactory.getLog(LoginServiceImpl.class);

    @Autowired
    SessionService sessionService;

    @Autowired
    UserAuthRepository userAuthRepository;

    @Autowired
    AuthLog authLog;

    @Autowired
    PasswordBox passwordBox;

    @Autowired
    SessionIdGenerator sessionIdGenerator;

    public PlainResult<UserResponseDTO> email(UserDTO userDTO){
//        String sessionId = userDTO.getSessionId();
//        if (sessionId != null) {
//           PlainResult<JSONObject> userJSON = sessionService.getAll(sessionId);
//            UserDTO userDto = JSON.parseObject(JSONObject.toJSONString(userJSON), UserDTO.class);
//            return PlainResults.success(UserResponseDTO.merge(userDto), "登录成功");
//        }
        UserAuth userAuthIfExist = userAuthRepository.findByIdentityTypeAndIdentifier(RegisterType.EMAIL, userDTO.getEmail());
        if (userAuthIfExist != null) {
            String expectPasswd = userAuthIfExist.getCredential();
            Boolean match = passwordBox.matchs(userDTO.getPassword(), expectPasswd);
            if (match) {
                User user = userAuthIfExist.getAuthUser();
                logger.info("登录成功：" + user);
                String seesionId = sessionIdGenerator.getSessionId();
                sessionService.create(seesionId, userDTO);
                logger.info("Session 写入成功");
                return PlainResults.success(UserResponseDTO.merge(user), "登录成功");
            } else {
                logger.info("用户密码不正确: expectPassword: " + expectPasswd + ", actualPassword: " + userDTO.getPassword());
               return PlainResults.error(ErrorCodes.PASSWORD_ERROR, "密码错误");
            }
        }
        logger.info("用户未注册" + userDTO);
        return PlainResults.error(ErrorCodes.NOT_REGISTER, "账号未注册");
    }
}
