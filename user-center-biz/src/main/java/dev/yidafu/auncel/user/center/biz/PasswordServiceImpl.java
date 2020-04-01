package dev.yidafu.auncel.user.center.biz;

import dev.yidafu.auncel.user.center.api.PasswordService;
import dev.yidafu.auncel.user.center.api.common.ErrorCodes;
import dev.yidafu.auncel.user.center.api.common.PlainResult;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Service
public class PasswordServiceImpl implements PasswordService {

    @Autowired
    PasswordBox passwordBox;

    @Override
    public PlainResult<String> encode(String rawPassword) {
        Optional<String> opt = this.passwordBox.encode(rawPassword);
        if (opt.isPresent()) {
            return (PlainResult<String>) PlainResult.make(opt.get(), "加密成功");
        }
        return (PlainResult<String>) PlainResult.error(ErrorCodes.ENCODE_FAILED, "加密失败");
    }

    @Override
    public PlainResult<String> decode(String decodePassword) {
        Optional<String> opt = this.passwordBox.decode(decodePassword);
        if (opt.isPresent()) {
            return (PlainResult<String>) PlainResult.make(opt.get(), "解密成功");
        }
        return (PlainResult<String>) PlainResult.error(ErrorCodes.DECODE_FAILED, "解密失败");
    }

    @Override
    public PlainResult<Boolean> mathes(String actualPassword, String expectPassword) {
        String actualResult = this.passwordBox.decode(actualPassword).orElse("actual");
        String expectResult = this.passwordBox.decode(expectPassword).orElse("expect");
        if (expectResult.equals(actualPassword)) {
            return (PlainResult<Boolean>) PlainResult.make(true, "密码相同");
        }
        return (PlainResult<Boolean>) PlainResult.error(ErrorCodes.PASSWORD_INEQUAL, "密码不相同");
    }
}
