package dev.yidafu.auncel.web.controller;

import com.github.dozermapper.core.Mapper;
import dev.yidafu.auncel.web.common.ErrorCodes;
import dev.yidafu.auncel.web.common.response.PlainResult;
import dev.yidafu.auncel.web.common.response.PlainResults;
import dev.yidafu.auncel.web.common.utils.UpdateUtil;
import dev.yidafu.auncel.web.dal.UserAuthRepository;
import dev.yidafu.auncel.web.dal.UserRepository;
import dev.yidafu.auncel.web.domain.IdentifierType;
import dev.yidafu.auncel.web.domain.User;
import dev.yidafu.auncel.web.domain.UserAuth;
import dev.yidafu.auncel.web.domain.dto.UserAuthDto;
import dev.yidafu.auncel.web.domain.dto.UserDto;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.sql.Update;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/userauth")
public class UserAuthController {

    public static Log logger = LogFactory.getLog(UserAuthController.class);

    @Autowired
    Mapper mapper;

    @Autowired
    UserAuthRepository userAuthRepository;

    @Autowired
    UserRepository userRepository;

    @PutMapping
    public PlainResult<Boolean> update(@RequestBody UserAuthDto userAuthDto, HttpSession session) {
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return PlainResults.error(ErrorCodes.USER_NOT_EXIST, "用户不存在");
        }
        logger.info("[Session User]" + currentUser);

        User user = userRepository.findById(currentUser.getId()).get();

        UserAuth userAuth = userAuthRepository.findByAuthUserAndIdentityType(user, IdentifierType.EMAIL);
        logger.info("[var UserAuth]" + userAuth);
        // https://blog.csdn.net/lijw_csdn/article/details/80528636
        List<String> ignorePrperties = new ArrayList<>(Arrays.asList(UpdateUtil.getNullPropertyNames(userAuthDto)));
        ignorePrperties.add("id");
        String[] ignorePrpertiesArr = new String[ignorePrperties.size()];
        ignorePrperties.toArray(ignorePrpertiesArr);
        logger.info("[var ignorePrperties]" + ignorePrpertiesArr);
        BeanUtils.copyProperties(userAuthDto, userAuth, ignorePrpertiesArr);
        logger.info("[var UserAuth][Before Update]" + userAuth);
        userAuthRepository.save(userAuth);

        return PlainResults.success(true, "更新成功");
    }

    @GetMapping
    public PlainResult<UserAuthDto> getUserAuthInfo(HttpSession session) {
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return PlainResults.error(ErrorCodes.USER_NOT_EXIST, "用户不存在");
        }
        logger.info("[Session User]" + currentUser);
        User user = userRepository.findById(currentUser.getId()).get();

        UserAuth userAuth = userAuthRepository.findByAuthUserAndIdentityType(user, IdentifierType.EMAIL);
        logger.info("[var UserAuth]" + userAuth);

        UserAuthDto userAuthDto = new UserAuthDto();
        userAuthDto.setId(userAuth.getId());
        userAuthDto.setIdentifier(userAuth.getIdentifier());
        userAuthDto.setIdentityType(userAuth.getIdentityType());
        return PlainResults.success(userAuthDto, "获取认证信息");
    }
}
