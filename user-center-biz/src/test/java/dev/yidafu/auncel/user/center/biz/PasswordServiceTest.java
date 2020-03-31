package dev.yidafu.auncel.user.center.biz;

import dev.yidafu.auncel.user.center.api.PasswordService;
import dev.yidafu.auncel.user.center.api.common.PlainResult;
import org.apache.dubbo.config.annotation.Reference;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ContextConfiguration
@SpringBootTest
public class PasswordServiceTest {
    @Reference(version = "0.0.1")
    PasswordService passwordService;

    @Test
    public void test1() {
        System.out.println("=============================================");
        PlainResult<String> result = passwordService.encode("123456");
        System.out.println(result.getData());
        assertNotNull(result.getData());
    }
}
