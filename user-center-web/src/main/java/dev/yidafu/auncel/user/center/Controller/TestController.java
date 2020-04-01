package dev.yidafu.auncel.user.center.Controller;

import dev.yidafu.auncel.user.center.dal.UserRepository;
import dev.yidafu.auncel.user.center.domain.AuthLog;
import dev.yidafu.auncel.user.center.domain.User;
import dev.yidafu.auncel.user.center.domain.UserAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertTrue;

@RestController
public class TestController {

    @Autowired
    UserRepository userRepository;
    @RequestMapping(path = "/test")
    List<UserAuth> test() {
//        User user = new User();
//        user.setUsername("username");
//        user.setRealname("realname");
//        user.setAvatar("http://test.com/2.png");
//        this.userRepository.save(user);
        User user =  userRepository.findById(new Long(1)).orElse(new User());
        return user.getUserAuths();
    }
}
