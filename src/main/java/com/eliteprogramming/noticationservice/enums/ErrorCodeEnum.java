package com.eliteprogramming.noticationservice.enums;

public enum ErrorCodeEnum {
    SUCCESS("EP_1000"),
    FAILURE("EP_1001"),
    OTP_SEND_ISSUE("EP_1002");


    private final String value;

    ErrorCodeEnum(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}