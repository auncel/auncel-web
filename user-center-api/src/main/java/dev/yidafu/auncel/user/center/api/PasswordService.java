package dev.yidafu.auncel.user.center.api;

import dev.yidafu.auncel.user.center.api.common.PlainResult;

public interface PasswordService {
    public PlainResult<String> encode(String rawPassword);
    public PlainResult<String>  decode(String decodePassword);
    public PlainResult<Boolean> mathes(String actualPassword, String expectPassword);
}
