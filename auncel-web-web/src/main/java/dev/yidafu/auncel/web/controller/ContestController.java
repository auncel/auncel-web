package dev.yidafu.auncel.web.controller;

import com.github.dozermapper.core.Mapper;
import dev.yidafu.auncel.web.common.ErrorCodes;
import dev.yidafu.auncel.web.common.exception.AuncelBaseException;
import dev.yidafu.auncel.web.common.exception.ResponseCode;
import dev.yidafu.auncel.web.common.response.PlainResult;
import dev.yidafu.auncel.web.common.response.PlainResults;
import dev.yidafu.auncel.web.common.utils.UpdateUtil;
import dev.yidafu.auncel.web.dal.*;
import dev.yidafu.auncel.web.domain.*;
import dev.yidafu.auncel.web.domain.dto.*;
import dev.yidafu.auncel.web.snippet.CommonSnippet;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    ContestProblemRepo contestProblemRepo;

    @Autowired
    ProblemRepository problemRepository;

    @Autowired
    UserContestRepo userContestRepo;

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
                .map(contest -> {
                    ContestDto contestDto = mapper.map(contest, ContestDto.class);
                    List<ProblemDto> problemDtoList = contest.getContestProblems().stream()
                            .map(contestProblem -> {
                                Problem problem = contestProblem.getProblem();
                                 ProblemDto problemDto = mapper.map(problem, ProblemDto.class);
                               return problemDto;
                            }).collect(Collectors.toList());
                    contestDto.setProblems(problemDtoList);
                    return contestDto;
                })
                .collect(Collectors.toList());
        return PlainResults.success(contestDtoList, "获取竞赛数据成功");
    }

    @GetMapping("/getById")
    PlainResult<ContestDto> getById(@RequestParam("id") long id) {
        Optional<Contest> optionalContest = contestRepository.findById(id);

        if (!optionalContest.isPresent()) {
            throw new AuncelBaseException(ResponseCode.CONTEST_NOT_EXIST);
        }

        Contest contest = optionalContest.get();

        ContestDto contestDto = mapper.map(contest, ContestDto.class);
        List<ProblemDto> problemDtoList = contest.getContestProblems().stream()
                .map(contestProblem -> mapper.map(contestProblem.getProblem(), ProblemDto.class))
                .collect(Collectors.toList());
        contestDto.setProblems(problemDtoList);

        return PlainResults.success(contestDto, "获取竞数据成功");
    }

    @GetMapping("/join")
    public PlainResult<Boolean> join(@RequestParam("code") String code, HttpSession session) {
        User currentUser = snippet.getCurrentUser(session);
        Contest contest =  contestRepository.findOneByInvitaionCode(code);

        UserContest userContest = new UserContest();
        userContest.setContest(contest);
        userContest.setUser(currentUser);

        UserContest savedUserContest = userContestRepo.save(userContest);
        return PlainResults.success(true, "加入竞赛成功");
    }

    @PostMapping
    public PlainResult<Boolean> createContest(@RequestBody ContestRequestDto contestRequestDto, HttpSession session) {
        Contest contest = new Contest();

        logger.info("[var Contest][before copy properties]" + contest);
        BeanUtils.copyProperties(contestRequestDto, contest, UpdateUtil.getNullPropertyNames(contestRequestDto));
        logger.info("[var Contest][after copy properties]" + contest);
        User currentUser = snippet.getCurrentUser(session);
        contest.setMaker(currentUser);
        Contest savedContest = contestRepository.save(contest);
        long[] problemIds = contestRequestDto.getProblems();
        for (long id : problemIds) {
            ContestProblem contestProblem = new ContestProblem();
            contestProblem.setProblem(problemRepository.findById(id).get());
            contestProblem.setContest(savedContest);
            contestProblemRepo.save(contestProblem);
        }

        return PlainResults.success(true, "创建竞赛成功");
    }

    @PutMapping
    public PlainResult<Boolean> updateProblem(@RequestBody ContestRequestDto contestDto) {
        Optional<Contest> optionalContest = contestRepository.findById(contestDto.getId());
        if (!optionalContest.isPresent()) {
            throw new AuncelBaseException(ResponseCode.PROBLOEM_NOT_EXIST);
        }

        Contest problem = optionalContest.get();

        logger.info("[var Contest][before copy properties]" + problem);
        BeanUtils.copyProperties(contestDto, problem, UpdateUtil.getNullPropertyNames(contestDto));
        logger.info("[var Contest][after copy properties]" + problem);

        contestRepository.save(problem);
        return PlainResults.success(true, "更新问题成功");
    }

}
