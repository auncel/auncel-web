package dev.yidafu.auncel.user.center.biz;

import dev.yidafu.auncel.user.center.api.PasswordService;
import dev.yidafu.auncel.user.center.common.ErrorCodes;
import dev.yidafu.auncel.user.center.common.response.PlainResult;
import dev.yidafu.auncel.user.center.common.response.PlainResults;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Service
public class PasswordServiceImpl implements PasswordService {

    @Autowired(required = true)
    PasswordBox passwordBox;

    @Override
    public PlainResult<String> encode(String rawPassword) {
        Optional<String> opt = this.passwordBox.encode(rawPassword);
        if (opt.isPresent()) {
            return PlainResults.success(opt.get(), "加密成功");
        }
        return PlainResults.error(ErrorCodes.ENCODE_FAILED, "加密失败");
    }

    @Override
    public PlainResult<String> decode(String decodePassword) {
        Optional<String> opt = this.passwordBox.decode(decodePassword);
        if (opt.isPresent()) {
            return (PlainResult<String>) PlainResults.success(opt.get(), "解密成功");
        }
        return (PlainResult<String>) PlainResults.error(ErrorCodes.DECODE_FAILED, "解密失败");
    }

    @Override
    public PlainResult<Boolean> mathes(String actualPassword, String expectPassword) {
        if (passwordBox.matchs(actualPassword, expectPassword)) {
            return (PlainResult<Boolean>) PlainResults.success(true, "密码相同");
        }
        return (PlainResult<Boolean>) PlainResults.error(ErrorCodes.PASSWORD_INEQUAL, "密码不相同");
    }
}
