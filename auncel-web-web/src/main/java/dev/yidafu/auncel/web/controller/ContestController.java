package dev.yidafu.auncel.web.controller;

import com.github.dozermapper.core.Mapper;
import dev.yidafu.auncel.web.common.ErrorCodes;
import dev.yidafu.auncel.web.common.response.PlainResult;
import dev.yidafu.auncel.web.common.response.PlainResults;
import dev.yidafu.auncel.web.dal.ContestRepository;
import dev.yidafu.auncel.web.dal.UserRepository;
import dev.yidafu.auncel.web.domain.Contest;
import dev.yidafu.auncel.web.domain.Problem;
import dev.yidafu.auncel.web.domain.User;
import dev.yidafu.auncel.web.domain.UserContest;
import dev.yidafu.auncel.web.domain.dto.ContestDto;
import dev.yidafu.auncel.web.domain.dto.UserContestDto;
import dev.yidafu.auncel.web.domain.dto.UserDto;
import dev.yidafu.auncel.web.snippet.CommonSnippet;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/contest")
public class ContestController {
    @Autowired
    ContestRepository contestRepository;

    public static Log logger = LogFactory.getLog(ContestController.class);

    @Autowired
    Mapper mapper;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CommonSnippet snippet;

    /**
     * 个人详情页
     * @param userId
     * @return
     */
    @GetMapping("/getByUser")
    public PlainResult<List<UserContestDto>> getContestByUserId(@RequestParam("userId") Long userId) {
        Optional<User> userIfExist = userRepository.findById(userId);
        logger.info("[var userIfExist] " + userIfExist);

        if (!userIfExist.isPresent()) {
            return PlainResults.error(ErrorCodes.USER_NOT_EXIST, "用户不存在");
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

    /**
     * contest 列表页
     * @return
     */
    @GetMapping
    public PlainResult<List<ContestDto>> getAll() {
        List<Contest> contests = contestRepository.findAll();
        List<ContestDto> contestDtos = contests.stream().map(contest -> {
            ContestDto contestDto = mapper.map(contest, ContestDto.class);
            contestDto.setMaker(mapper.map(contest.getMaker(), UserDto.class));
            return contestDto;
        }).collect(Collectors.toList());
        return PlainResults.success(contestDtos, "获取所有竞赛数据成功");
    }

    @GetMapping("/getByMaker")
    public PlainResult<List<ContestDto>> getAllByMaker(HttpSession session) {
        User maker = snippet.getCurrentUser(session);
        List<Contest> contestList = contestRepository.findAllByMaker(maker);
        List<ContestDto> contestDtoList = contestList.stream()
                .map(contest -> mapper.map(contest, ContestDto.class))
                .collect(Collectors.toList());
        return PlainResults.success(contestDtoList, "获取竞赛数据成功");
    }
}
