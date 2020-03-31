package dev.yidafu.auncel.user.center.biz;

import dev.yidafu.auncel.user.center.api.EchoService;
import org.apache.dubbo.config.annotation.Service;

@Service
public class EchoServiceImpl implements EchoService {
    @Override
    public String echo(String msg) {
        System.out.println("=====================" + msg);
        return "echo: " + msg;
    }
}
