package dev.yidafu.auncel.web.controller;

import com.github.dozermapper.core.Mapper;
import dev.yidafu.auncel.web.common.ErrorCodes;
import dev.yidafu.auncel.web.common.exception.AuncelBaseException;
import dev.yidafu.auncel.web.common.exception.ResponseCode;
import dev.yidafu.auncel.web.common.response.PlainResult;
import dev.yidafu.auncel.web.common.response.PlainResults;
import dev.yidafu.auncel.web.common.utils.UpdateUtil;
import dev.yidafu.auncel.web.dal.ContestProblemRepo;
import dev.yidafu.auncel.web.dal.ProblemRepository;
import dev.yidafu.auncel.web.dal.SubmissionRepository;
import dev.yidafu.auncel.web.dal.UserRepository;
import dev.yidafu.auncel.web.domain.*;
import dev.yidafu.auncel.web.domain.dto.ProblemDto;
import dev.yidafu.auncel.web.domain.dto.ProblemStatusType;
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
@RequestMapping("/problem")
public class ProblemController {
    static Log logger = LogFactory.getLog(ProblemController.class);

    @Autowired
    Mapper mapper;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProblemRepository problemRepository;

    @Autowired
    ContestProblemRepo contestProblemRepo;

    @Autowired
    SubmissionRepository submissionRepository;

    @Autowired
    CommonSnippet snippet;

    @GetMapping
    public PlainResult<List<ProblemDto>> getAll(HttpSession session) {
//        boolean isAmdin = snippet.getCurrentUser(session).getRole() == UserRoleType.ADMIN;
        if (true) {
            List<Problem> problemList =  problemRepository.findAll();
            List<ProblemDto> problemDtoList = problemList.stream()
                    .map(problem ->  mapper.map(problem, ProblemDto.class))
                    .collect(Collectors.toList());
            return PlainResults.success(problemDtoList, "获取所有问题成功");
        }
        return PlainResults.error(ResponseCode.PERMISSION_ERROR);
    }

    @GetMapping("/getById")
    public PlainResult<ProblemDto> getById(@RequestParam("id") Long problemId) {
        Optional<Problem> optionalProblem =  problemRepository.findById(problemId);
        if (optionalProblem.isPresent()) {
            ProblemDto problemDto = mapper.map(optionalProblem.get(), ProblemDto.class);
            return PlainResults.success(problemDto, "获取问题详情成功");
        }
        return PlainResults.error(ErrorCodes.USER_NOT_EXIST, "未知错误");
    }

    @GetMapping("/getByUser")
    public PlainResult<List<Problem>> getByUser(@RequestParam("userId") Long userId) {
        Optional<User> optional = userRepository.findById(userId);
        if (!optional.isPresent()) {
            return PlainResults.error(ErrorCodes.ILLEGAL_USER_ID, "用户 ID 不正确");
        }
        User user = optional.get();
        List<Problem> problems = user.getProblems();

        return PlainResults.success(problems, "获取数据成功");
    }

    @GetMapping("/getByContest")
    public PlainResult<List<ProblemDto>> getAllByContest(@RequestParam(value = "contestId", defaultValue = "1") long contestId, HttpSession session) {
        List<ContestProblem> contestProblems = contestProblemRepo.findAllByContest_Id(contestId);
        logger.info("[List<ContestProblem>]" + contestProblems);

        // FIXME: 性能优化
        User user = (User) session.getAttribute("user");
        List<ProblemDto> problemDtos = contestProblems.stream()
                .map(contestProblem -> {
                    Problem problem = contestProblem.getProblem();
                    submissionRepository.getFirstByProblem(problem);
                    ProblemDto problemDto = mapper.map(problem, ProblemDto.class);
                    if (user != null) {
                        Submission submission = submissionRepository.selectByProblemIdAndUserId(problem.getId(), user.getId());
                        if (submission != null) {
                            if (submission.getStatus() == SubmissionStatus.ACCEPT) {
                                problemDto.setStatus(ProblemStatusType.ACCEPTED);
                            } else if (submission.getStatus() == SubmissionStatus.WRONG_ANWSER
                                    || submission.getStatus() == SubmissionStatus.SYNTAX_ERROR
                            ) {
                                problemDto.setStatus(ProblemStatusType.WRONG_ANSWER);
                            } else {
                                problemDto.setStatus(ProblemStatusType.WORKING_ON);
                            }
                        }
                    } else {
                        problemDto.setStatus(ProblemStatusType.NONE);
                    }
                    logger.info("[var ProblemDto]" + problemDto);
                    return problemDto;
                })
                .collect(Collectors.toList());
        return PlainResults.success(problemDtos, "获取题库成功");
    }

    @PutMapping
    public PlainResult<Boolean> updateProblem(@RequestBody ProblemDto problemDto) {
        Optional<Problem> optionalProblem = problemRepository.findById(problemDto.getId());
        if (!optionalProblem.isPresent()) {
            throw new AuncelBaseException(ResponseCode.PROBLOEM_NOT_EXIST);
        }
        Problem problem = optionalProblem.get();

        logger.info("[var Problem][before copy properties]" + problem);
        BeanUtils.copyProperties(problemDto, problem, UpdateUtil.getNullPropertyNames(problemDto));
        logger.info("[var Problem][after copy properties]" + problem);

        problemRepository.save(problem);
        return PlainResults.success(true, "更新问题成功");
    }

    @PostMapping
    public PlainResult<Boolean> createProblem(@RequestBody ProblemDto problemDto) {
        Problem problem = new Problem();

        logger.info("[var Problem][before copy properties]" + problem);
        BeanUtils.copyProperties(problemDto, problem, UpdateUtil.getNullPropertyNames(problemDto));
        logger.info("[var Problem][after copy properties]" + problem);

        problemRepository.save(problem);
        return PlainResults.success(true, "创建问题成功");
    }

    @DeleteMapping
    public PlainResult<Boolean> removeProblemById(@RequestBody ProblemDto problemDto) {
        logger.info("delete Problem" + problemDto);
        Problem problem = problemRepository.getOne(problemDto.getId());
        problemRepository.delete(problem);
        return PlainResults.success(true, "删除问题成果");
    }
}

