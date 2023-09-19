package com.eliteprogramming.noticationservice.dto;

import com.eliteprogramming.noticationservice.enums.ErrorCodeEnum;
import com.eliteprogramming.noticationservice.enums.ResponseMessage;
import org.springframework.http.HttpStatus;

public class Exception extends RuntimeException {
    private ErrorMessage error;

    public Exception(ErrorMessage error) {
        super(error.getMessage());
        this.error = error;
    }

    public Exception(String message, HttpStatus httpStatus, ErrorCodeEnum errorCode) {
        this(new ErrorMessage(message, httpStatus, errorCode));
    }


}