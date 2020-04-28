package dev.yidafu.auncel.web.snippet;

import dev.yidafu.auncel.web.common.exception.AuncelBaseException;
import dev.yidafu.auncel.web.common.exception.ResponseCode;
import dev.yidafu.auncel.web.dal.ProblemRepository;
import dev.yidafu.auncel.web.dal.UserRepository;
import dev.yidafu.auncel.web.domain.Problem;
import dev.yidafu.auncel.web.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CommonSnippet {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProblemRepository problemRepository;

    public User getUser(Long userId) {
        Optional<User> optUser = userRepository.findById(userId);
        if (optUser.isPresent()) {
            return optUser.get();
        }
        throw new AuncelBaseException(ResponseCode.USER_NOT_EXIST);
    }

    public Problem getProblem(Long problemId) {
        Optional<Problem> optionalProblem = problemRepository.findById(problemId);
        if (optionalProblem.isPresent()) {
            return optionalProblem.get();
        }
        throw new AuncelBaseException(ResponseCode.PROBLOEM_NOT_EXIST);
    }
}
