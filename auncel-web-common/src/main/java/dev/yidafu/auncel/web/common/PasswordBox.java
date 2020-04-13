package dev.yidafu.auncel.web.common;

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
    }

    public Boolean matchs(String actualPass, String expectPass) {
        String decodeActualPass = decode(actualPass).orElse("/<[]>\\");
        String decodeExpectPass = decode(expectPass).orElse("[</\\>]");
        return decodeActualPass.equals(decodeExpectPass);
    }
}
