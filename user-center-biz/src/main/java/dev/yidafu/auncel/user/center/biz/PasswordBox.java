package dev.yidafu.auncel.user.center.biz;

import dev.yidafu.auncel.user.center.api.common.ErrorCodes;
import dev.yidafu.auncel.user.center.api.common.PlainResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PasswordBox {
    @Value("${auncel.salt}")
    String salt;

    @Value("${auncel.password}")
    String password;

    public Optional<String> encode(String rawPassword) {

        TextEncryptor textEncrypter = Encryptors.text(this.password, this.salt);

        try {
            String encryptText = textEncrypter.encrypt(rawPassword);
            return Optional.of(encryptText);
        } catch (Exception err){
            System.out.println(err);
        }
        return Optional.empty();
//        return (PlainResult<String>) PlainResult.error(ErrorCodes.REQUEST_FAILED, "加密失败");
    }

    public Optional<String> decode(String decodePassword) {
        TextEncryptor textEncrypter = Encryptors.text(this.password, this.salt);
        try {
            String encryptText = textEncrypter.decrypt(decodePassword);
            return Optional.of(encryptText);
        } catch (Exception err){
            System.out.println(err);
        }
        return Optional.empty();
//        return (PlainResult<String>) PlainResult.error(ErrorCodes.REQUEST_FAILED, "解密失败");
    }
}
