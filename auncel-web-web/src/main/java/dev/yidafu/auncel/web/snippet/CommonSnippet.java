package dev.yidafu.auncel.web.snippet;

import dev.yidafu.auncel.web.common.ErrorCodes;
import dev.yidafu.auncel.web.common.exception.AuncelBaseException;
import dev.yidafu.auncel.web.common.exception.ResponseCode;
import dev.yidafu.auncel.web.common.response.PlainResults;
import dev.yidafu.auncel.web.dal.ProblemRepository;
import dev.yidafu.auncel.web.dal.UserRepository;
import dev.yidafu.auncel.web.domain.Problem;
import dev.yidafu.auncel.web.domain.User;
import dev.yidafu.auncel.web.domain.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Component
public class CommonSnippet {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProblemRepository problemRepository;

    public User getCurrentUser(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            return userRepository.findById(user.getId()).get();
        }
        throw new AuncelBaseException(ResponseCode.USER_NOT_EXIST);
    };

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
