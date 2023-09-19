package com.eliteprogramming.noticationservice.enums;

public enum ResponseMessage {
    SUCCESS("Success!", ErrorCodeEnum.SUCCESS),
    CREATED("Created!", ErrorCodeEnum.SUCCESS);

    private final String message;
    private final ErrorCodeEnum errorCode;

    ResponseMessage(String message, ErrorCodeEnum errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }

    public ErrorCodeEnum getErrorCode() {
        return errorCode;
    }

    @Override
    public String toString() {
        return message;
    }
}
