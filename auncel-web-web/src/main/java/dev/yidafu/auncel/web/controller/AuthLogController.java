package dev.yidafu.auncel.web.controller;

import com.github.dozermapper.core.Mapper;
import dev.yidafu.auncel.web.common.ErrorCodes;
import dev.yidafu.auncel.web.common.response.PlainResult;
import dev.yidafu.auncel.web.common.response.PlainResults;
import dev.yidafu.auncel.web.dal.AuthLogRepository;
import dev.yidafu.auncel.web.domain.AuthLog;
import dev.yidafu.auncel.web.domain.User;
import dev.yidafu.auncel.web.domain.dto.AuthLogDto;
import dev.yidafu.auncel.web.domain.dto.UserDto;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/authlog")
public class AuthLogController {
    public  static Log logger = LogFactory.getLog(AuthLogController.class);

    @Autowired
    AuthLogRepository authLogRepository;

    @Autowired
    Mapper mapper;

    @GetMapping
    public PlainResult<List<AuthLogDto>> getAllLogs(HttpSession session) {
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return PlainResults.error(ErrorCodes.USER_NOT_EXIST, "用户不存在");
        }
        UserDto userDto = mapper.map(currentUser, UserDto.class);
        List<AuthLog> authLogs = authLogRepository.findByLogUserIs(currentUser);
        List<AuthLogDto> authLogDtos = authLogs.stream().map(item -> {
            AuthLogDto authLogDto = mapper.map(item, AuthLogDto.class);
            authLogDto.setLogUser(userDto);
            return authLogDto;
        }).collect(Collectors.toList());

        return PlainResults.success(authLogDtos, "获取日志成功");
    }
}
