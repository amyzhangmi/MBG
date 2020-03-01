package com.amy.mbg.gen.common.enums;

/**
 * 返回结果枚举
 */
public enum ResultEnum {
    SUCCESS(0,"SUCCESS"),
    FAIL(-1,"FAIL");

    public int code;

    public String msg;

    ResultEnum() {
    }

    ResultEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
