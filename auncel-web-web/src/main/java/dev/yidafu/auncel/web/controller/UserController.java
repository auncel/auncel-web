package dev.yidafu.auncel.web.controller;

import com.github.dozermapper.core.Mapper;
import dev.yidafu.auncel.web.common.ErrorCodes;
import dev.yidafu.auncel.web.common.md5.MD5Util;
import dev.yidafu.auncel.web.common.response.PlainResult;
import dev.yidafu.auncel.web.common.response.PlainResults;
import dev.yidafu.auncel.web.common.utils.UpdateUtil;
import dev.yidafu.auncel.web.dal.AuthLogRepository;
import dev.yidafu.auncel.web.domain.AuthLog;
import dev.yidafu.auncel.web.domain.IdentifierType;
import dev.yidafu.auncel.web.common.RegisterType;
import dev.yidafu.auncel.web.domain.dto.UserDto;
import dev.yidafu.auncel.web.domain.dto.UserRequestDto;
import dev.yidafu.auncel.web.common.utils.Utils;
import dev.yidafu.auncel.web.dal.UserAuthRepository;
import dev.yidafu.auncel.web.dal.UserRepository;
import dev.yidafu.auncel.web.domain.User;
import dev.yidafu.auncel.web.domain.UserAuth;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Optional;

@RestController()
@RequestMapping("/user")
public class UserController {
    public static Log logger = LogFactory.getLog(UserController.class);

    @Autowired
    Mapper mapper;

    @Autowired
    UserAuthRepository userAuthRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthLogRepository authLogRepository;

    @GetMapping
    public PlainResult<UserDto> getInfo(@RequestParam("id") Long userId, HttpSession session) {
        logger.info("get user by id: " + userId);
        User user = (User) session.getAttribute("user");
        if (user != null) {
            return PlainResults.success(mapper.map(user, UserDto.class), "获取用户信息成功");
        }
        return PlainResults.error(ErrorCodes.USER_NOT_EXIST, "用户不存在");
    }

    @PostMapping("/login")
    public PlainResult<UserDto> login(@RequestBody UserRequestDto userDTO, HttpSession session){
        UserAuth userAuthIfExist = userAuthRepository.findByIdentityTypeAndIdentifier(RegisterType.EMAIL, userDTO.getEmail());
        if (userAuthIfExist != null) {
            String expectPasswd = userAuthIfExist.getCredential();
            Boolean match = MD5Util.matches(userDTO.getPassword(), expectPasswd);
            if (match) {
                User user = userAuthIfExist.getAuthUser();
                logger.info("登录成功：" + user);
                session.setAttribute("user", user);
                AuthLog authLog = new AuthLog();
                authLog.setTitle("正常登录");
                authLog.setLoginIp(Utils.getIpAddr());
                String logContent = "用户: " + user.getUsername() + "(" + user.getRealname()+ ") 成功登录";
                authLog.setContent(logContent);
                authLog.setLogUser(user);
                authLogRepository.save(authLog);
                logger.info("Session 写入成功");
                return PlainResults.success(mapper.map(user, UserDto.class), ErrorCodes.LOGIN_SUCCESS, "登录成功");
            } else {
                logger.info("用户密码不正确: expectPassword: " + expectPasswd + ", actualPassword: " + userDTO.getPassword());
                return PlainResults.error(ErrorCodes.PASSWORD_ERROR, "邮箱或密码错误");
            }
        }
        logger.info("用户未注册" + userDTO);
        return PlainResults.error(ErrorCodes.NOT_REGISTER, "账号未注册");
    }

    @PostMapping("/register")
    public PlainResult<UserDto> register(@RequestBody UserRequestDto userRequestDto, HttpSession session) {
        logger.info("邮箱注册:  " + userRequestDto);
        UserAuth userAuthIfExist = userAuthRepository.findByIdentityTypeAndIdentifier(
                IdentifierType.EMAIL,
                userRequestDto.getEmail()
        );
        User userIfExsit = userRepository.findByUsername(userRequestDto.getUsername());
        if (userAuthIfExist != null || userIfExsit != null) {
            return PlainResults.error(ErrorCodes.ALREADY_REGISTE, "该用户已经注册过了");
        }

        User user = new User();
        user.setUsername(userRequestDto.getUsername());
        user.setRegisterIp(Utils.getIpAddr());
        user.setStatus("normal");
        UserAuth userAuth = new UserAuth();
        userAuth.setIdentityType(IdentifierType.EMAIL);
        userAuth.setIdentifier(userRequestDto.getEmail());
        userAuth.setCredential(userRequestDto.getPassword());
        userRepository.save(user);
        logger.info("userRepository.save(" + user + ")");

        userAuth.setAuthUser(user);
        userAuthRepository.save(userAuth);
        logger.info("userAuthRepository.save(" + userAuth + ")");

        // TODO: send verified email

        session.setAttribute("user", user);
        return PlainResults.success(mapper.map(user, UserDto.class), "注册用户成功");
    }

    @PutMapping(value = "/u/profile")
    public PlainResult<Boolean> updateProfile(UserDto userDTO) {
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
        return PlainResults.error(ErrorCodes.USER_NOT_EXIST, "用户不存在");
    }

    @PutMapping
    public  PlainResult<Boolean> update(@RequestBody UserDto userDto, HttpSession session) {
        Optional<User> optional = userRepository.findById(userDto.getId());
        if (!optional.isPresent()) {
            return PlainResults.error(ErrorCodes.ILLEGAL_USER_ID, "用户 ID 不正确");
        }
        User user = optional.get();

        logger.info("[var User][before copy properties] " + user);
        BeanUtils.copyProperties(userDto, user, UpdateUtil.getNullPropertyNames(userDto));

        userRepository.save(user);
        session.setAttribute("user", user);
        logger.info("更新 Session" + user);
        return PlainResults.success(true, "更新成功");
    }
}
