package dev.yidafu.auncel.user.center.biz;

import dev.yidafu.auncel.user.center.api.DemoProvider;
import org.apache.dubbo.config.annotation.Service;

@Service
public class DemoServiceImpl implements DemoProvider {
    @Override
    public String test(String msg) {
        return "empty service response" + msg;
    }
}
