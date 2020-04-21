package dev.yidafu.auncel.web.controller;

import com.github.dozermapper.core.Mapper;
import dev.yidafu.auncel.web.common.ResponseCodes;
import dev.yidafu.auncel.web.common.response.PlainResult;
import dev.yidafu.auncel.web.common.response.PlainResults;
import dev.yidafu.auncel.web.dal.UserRepository;
import dev.yidafu.auncel.web.domain.Contest;
import dev.yidafu.auncel.web.domain.User;
import dev.yidafu.auncel.web.domain.UserContest;
import dev.yidafu.auncel.web.domain.dto.ContestDto;
import dev.yidafu.auncel.web.domain.dto.UserContestDto;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/contest")
public class ContestController {
    public static Log logger = LogFactory.getLog(ContestController.class);

    @Autowired
    Mapper mapper;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/getByUser")
    public PlainResult<List<UserContestDto>> getContestByUserId(@RequestParam("userId") Long userId) {
        Optional<User> userIfExist = userRepository.findById(userId);
        logger.info("[var userIfExist] " + userIfExist);

        if (!userIfExist.isPresent()) {
            return PlainResults.error(ResponseCodes.USER_NOT_EXIST, "用户不存在");
        }

        User user = userIfExist.get();
        List<UserContest> userContests =  user.getUserContests();
        logger.info("[var List<UserContest>] " + userContests);
        List<UserContestDto> userContestDtos = userContests
                .stream()
                .map(item -> {
                    Contest contest = item.getContest();
                    UserContestDto userContestDto = mapper.map(item, UserContestDto.class);
                    userContestDto.setContest(mapper.map(contest, ContestDto.class));
                    return userContestDto;
                }).collect(Collectors.toList());
        return PlainResults.success(userContestDtos, "获取竞赛列表成功");
    }

}
