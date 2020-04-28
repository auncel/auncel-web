package dev.yidafu.auncel.web.controller;


import com.github.dozermapper.core.Mapper;
import dev.yidafu.auncel.web.common.ErrorCodes;
import dev.yidafu.auncel.web.common.exception.AuncelBaseException;
import dev.yidafu.auncel.web.common.exception.ResponseCode;
import dev.yidafu.auncel.web.common.response.PlainResult;
import dev.yidafu.auncel.web.common.response.PlainResults;
import dev.yidafu.auncel.web.common.utils.UpdateUtil;
import dev.yidafu.auncel.web.dal.ProblemRepository;
import dev.yidafu.auncel.web.dal.SubmissionRepository;
import dev.yidafu.auncel.web.dal.UserRepository;
import dev.yidafu.auncel.web.domain.Problem;
import dev.yidafu.auncel.web.domain.Submission;
import dev.yidafu.auncel.web.domain.User;
import dev.yidafu.auncel.web.domain.dto.ProblemDto;
import dev.yidafu.auncel.web.domain.dto.SubmissionDto;
import dev.yidafu.auncel.web.snippet.CommonSnippet;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/submission")
public class SubmissionController {
    public static Log logger = LogFactory.getLog(SubmissionController.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    SubmissionRepository submissionRepository;

    @Autowired
    ProblemRepository problemRepository;

    @Autowired
    Mapper mapper;

    @Autowired
    CommonSnippet snippet;

    @GetMapping()
    public PlainResult<List<SubmissionDto>> getByUserAndProblem(@RequestParam("userId") long userId, @RequestParam("problemId") long problemId) {
        User submiter = snippet.getUser(userId);
        logger.info("[var submiter]" + submiter);
        Problem problem = snippet.getProblem(problemId);
        logger.info("[var problem]" + problem);
        List<Submission> submissions = submissionRepository.findAllBySubmiterAndProblem(submiter, problem);

        List<SubmissionDto> submissionDtoList = submissions.stream()
                .map((submission -> mapper.map(submission, SubmissionDto.class)))
                .collect(Collectors.toList());

        return PlainResults.success(submissionDtoList, "获取提交数据成功");
    }

    @GetMapping("/getByUser")
    public PlainResult<List<SubmissionDto>> getByUser(@RequestParam("userId") long userId) {
        Optional<User> optional = userRepository.findById(userId);
        if (!optional.isPresent()) {
            logger.warn("user " + userId + " 不存在");
            return PlainResults.error(ErrorCodes.USER_NOT_EXIST, "用户不存在");
        }
        User user = optional.get();
        logger.info("当前用户 [var User] " + user);
        List<Submission> submissions = user.getSubmissions();

        List<SubmissionDto> submissionDtos = submissions.stream().map(item -> {
            Problem problem = item.getProblem();
             SubmissionDto submissionDto = mapper.map(item, SubmissionDto.class);
             submissionDto.setProblem(mapper.map(problem, ProblemDto.class));
            return submissionDto;
        }).collect(Collectors.toList());

        logger.info("[var List<SubmissionDto>] " + submissionDtos);
        return PlainResults.success(submissionDtos, "获取提交数据成功");
    }

    @PostMapping
    public PlainResult<SubmissionDto> createSubmission(@RequestBody SubmissionDto submissionDto) {
        logger.info("[Request SubmissionDto]" + submissionDto);
        Submission submission = mapper.map(submissionDto, Submission.class);
        submission.setSubmiter(userRepository.findById(submissionDto.getSubmiter().getId()).get());
        submission.setProblem(problemRepository.findById(submissionDto.getProblem().getId()).get());
        submissionRepository.save(submission);
        SubmissionDto submissionDto1 = mapper.map(submission, SubmissionDto.class);
        return PlainResults.success(submissionDto1, "提交成功");
    }

    @PutMapping
    public PlainResult<Boolean> updateSubmission(@RequestBody SubmissionDto submissionDto) {
        Optional<Submission> optionalSubmission = submissionRepository.findById(submissionDto.getId());
        if (!optionalSubmission.isPresent()) {
            throw new AuncelBaseException(ResponseCode.SUBMISSION_NOT_EXIST);
        }
        Submission submission = optionalSubmission.get();

        logger.info("[var Submission][before copy properties]" + submission);
        BeanUtils.copyProperties(submissionDto, submission, UpdateUtil.getNullPropertyNames(submissionDto));
        logger.info("[var Submission][after copy properties]" + submission);

        submissionRepository.save(submission);
        return PlainResults.success(true, "更新提交信息成功");
    };
}
