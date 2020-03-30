package dev.yidafu.auncel.user.center.biz;

import dev.yidafu.auncel.user.center.api.DemoProvider;
import org.apache.dubbo.config.annotation.Service;

@Service
public class DemoServiceImpl implements DemoProvider {
//    @Override
//    public String test(String msg) {
//        return "empty service response" + msg;
//    }

    @Override
    public String sayHello(String name) {
        return null;
    }

    @Override
    public String echo() {
        return null;
    }

    @Override
    public void test() {

    }

    @Override
    public String getUserInfo(String request) {
        return null;
    }
}
