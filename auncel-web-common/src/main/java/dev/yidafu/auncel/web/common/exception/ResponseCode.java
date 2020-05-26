package dev.yidafu.auncel.web.common.exception;

import dev.yidafu.auncel.web.common.ErrorCodes;

public enum  ResponseCode {
    SESSION_ERROR(ErrorCodes.SESSION_ERROR, "session异常"),
    REQUEST_FAILED(ErrorCodes.REQUEST_FAILED, "请求失败"),

    PASSWORD_INEQUAL(ErrorCodes.PASSWORD_INEQUAL, "用户名或密码错误"),

    ALREADY_REGISTE(ErrorCodes.ALREADY_REGISTE, "该用户已经注册了"),
    NOT_REGISTER(ErrorCodes.NOT_REGISTER, "该用户还未注册"),

    USER_NOT_EXIST(ErrorCodes.USER_NOT_EXIST, "该用户不存在"),
    PROBLOEM_NOT_EXIST(ErrorCodes.PROBLOEM_NOT_EXIST, "该问题不存在"),
    SUBMISSION_NOT_EXIST(ErrorCodes.SUBMISSION_NOT_EXIST, "该提交不存在"),
    CONTEST_NOT_EXIST(ErrorCodes.CONTEST_NOT_EXIST, "该竞赛不存在"),
    ILLEGAL_USER_ID(ErrorCodes.ILLEGAL_USER_ID, "非法的用户ID"),

    PERMISSION_ERROR(ErrorCodes.PERMISSION_ERROR, "您没有权限访问"),

    UNKNOWN_FAILED(ErrorCodes.UNKNOWN_FAILED, "未知错误");
    private int code;
    private String msg;

    private ResponseCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
