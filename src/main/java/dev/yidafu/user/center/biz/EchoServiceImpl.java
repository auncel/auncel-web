package dev.yidafu.user.center.biz;

import dev.yidafu.user.center.api.EchoService;
import org.apache.dubbo.config.annotation.Service;

@Service
public class EchoServiceImpl implements EchoService {
    @Override
    public String echo(String msg) {
        return "echo: " + msg;
    }
}
