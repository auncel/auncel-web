package dev.yidafu.auncel.web.common.uuid;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

@Component
public class SessionIdGenerator {

    private String PREFIX = "AUNCEL";
    SnowFlake snowFlake;
    public SessionIdGenerator() {
        snowFlake = new SnowFlake(2, 3);
    }

    public String getSessionId() {
        long snowId = snowFlake.nextId();
        String hexStr = Long.toHexString(snowId);
        String hash = DigestUtils.sha1Hex(hexStr).substring(10, 30);
        return this.PREFIX + hash;
    }

    public static void main(String[] args) {
        System.out.println(new SessionIdGenerator().getSessionId());
    }
}
