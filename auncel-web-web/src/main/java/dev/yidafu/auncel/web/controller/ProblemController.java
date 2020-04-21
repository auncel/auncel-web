package dev.yidafu.auncel.web.controller;

import dev.yidafu.auncel.web.common.ErrorCodes;
import dev.yidafu.auncel.web.common.response.PlainResult;
import dev.yidafu.auncel.web.common.response.PlainResults;
import dev.yidafu.auncel.web.dal.UserRepository;
import dev.yidafu.auncel.web.domain.Problem;
import dev.yidafu.auncel.web.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/problems")
public class ProblemController {

    @Autowired
    UserRepository userRepository;

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
}
