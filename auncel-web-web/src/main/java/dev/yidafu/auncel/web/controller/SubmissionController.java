package dev.yidafu.auncel.web.controller;


import com.github.dozermapper.core.Mapper;
import dev.yidafu.auncel.web.common.ErrorCodes;
import dev.yidafu.auncel.web.common.response.PlainResult;
import dev.yidafu.auncel.web.common.response.PlainResults;
import dev.yidafu.auncel.web.dal.UserRepository;
import dev.yidafu.auncel.web.domain.Problem;
import dev.yidafu.auncel.web.domain.Submission;
import dev.yidafu.auncel.web.domain.User;
import dev.yidafu.auncel.web.domain.dto.ProblemDto;
import dev.yidafu.auncel.web.domain.dto.SubmissionDto;
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
@RequestMapping("/submission")
public class SubmissionController {
    public static Log logger = LogFactory.getLog(SubmissionController.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    Mapper mapper;

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
}
