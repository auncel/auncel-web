package dev.yidafu.auncel.web.controller;

import dev.yidafu.auncel.web.common.md5.MD5Util;
import dev.yidafu.auncel.web.common.response.PlainResult;
import dev.yidafu.auncel.web.common.response.PlainResults;
import dev.yidafu.auncel.web.common.IdentifierType;
import dev.yidafu.auncel.web.common.RegisterType;
import dev.yidafu.auncel.web.common.ResponseCodes;
import dev.yidafu.auncel.web.common.dto.UserDTO;
import dev.yidafu.auncel.web.common.dto.UserResponseDTO;
import dev.yidafu.auncel.web.common.uuid.SessionIdGenerator;
import dev.yidafu.auncel.web.dal.UserAuthRepository;
import dev.yidafu.auncel.web.dal.UserRepository;
import dev.yidafu.auncel.web.domain.User;
import dev.yidafu.auncel.web.domain.UserAuth;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

@RestController()
@RequestMapping("/user")
public class UserController {
    public static Log logger = LogFactory.getLog(UserController.class);

    @Autowired
    UserAuthRepository userAuthRepository;

    @Autowired
    SessionIdGenerator sessionIdGenerator;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/login")
    public PlainResult<UserResponseDTO> login(@RequestBody  UserDTO userDTO, HttpSession session){
        UserAuth userAuthIfExist = userAuthRepository.findByIdentityTypeAndIdentifier(RegisterType.EMAIL, userDTO.getEmail());
        if (userAuthIfExist != null) {
            String expectPasswd = userAuthIfExist.getCredential();
            Boolean match = MD5Util.matches(userDTO.getPassword(), expectPasswd);
            if (match) {
                User user = userAuthIfExist.getAuthUser();
                logger.info("登录成功：" + user);
                session.setAttribute("user", userDTO);
                logger.info("Session 写入成功");
                return PlainResults.success(UserResponseDTO.merge(user), ResponseCodes.LOGIN_SUCCESS, "登录成功");
            } else {
                logger.info("用户密码不正确: expectPassword: " + expectPasswd + ", actualPassword: " + userDTO.getPassword());
                return PlainResults.error(ResponseCodes.PASSWORD_ERROR, "邮箱或密码错误");
            }
        }
        logger.info("用户未注册" + userDTO);
        return PlainResults.error(ResponseCodes.NOT_REGISTER, "账号未注册");
    }

    @PostMapping("/register")
    public PlainResult<User> register(UserDTO userDTO, HttpSession session) {
        logger.info("邮箱注册:  " + userDTO);
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

            String sessionId = sessionIdGenerator.getSessionId();
            logger.info("new SESSIONID: " + sessionId);
            session.setAttribute("user", userDTO);
            return PlainResults.success(user, "注册用户成功");
        }
        return PlainResults.error(ResponseCodes.ALREADY_REGISTE, "该用户已经注册过了");
    }

    @PutMapping(value = "/u/profile")
    public PlainResult<Boolean> updateProfile(UserDTO userDTO) {
        Long userId = userDTO.getId();
        Boolean userExist = userRepository.existsById(userId);
        if (userExist) {
            User userWillUpdate = new User();
            userWillUpdate.setId(userId);
            userWillUpdate.setUsername(userDTO.getUsername());
            userWillUpdate.setRealname(userDTO.getRealname());
            userWillUpdate.setSlogan(userDTO.getSlogan());
            userWillUpdate.setSchool(userDTO.getSchool());
            userRepository.save(userWillUpdate);
            PlainResults.success(true, "用户信息更新成功");
        }
        return PlainResults.error(ResponseCodes.USER_NOT_EXIST, "用户不存在");
    }

    @GetMapping("/u/{userId}")
    public PlainResult<User> getInfo(@PathVariable("userId") Long userId) {
        logger.info("get user by id: " + userId);
        User user = userRepository.getOne(userId);
        if (user != null) {
//            UserResponseDTO userResponseDTO = UserResponseDTO.merge(user);
           return PlainResults.success(user, "获取用户信息成功");
        }
        return PlainResults.error(ResponseCodes.USER_NOT_EXIST, "用户不存在");
    }
}
