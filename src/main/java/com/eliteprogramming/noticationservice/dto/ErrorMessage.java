package com.eliteprogramming.noticationservice.dto;

import com.eliteprogramming.noticationservice.enums.ErrorCodeEnum;
import com.eliteprogramming.noticationservice.enums.ResponseMessage;
import org.springframework.http.HttpStatus;

import java.util.Date;

public class ErrorMessage {
    private String message;
    private String title;
    private HttpStatus error;
    private int status;
    private String code;
    private String timestamp;
    private Object data;
    private String requestId;

    public ErrorMessage() {
    }

    public ErrorMessage(String message, HttpStatus error, ErrorCodeEnum code) {
        this.message = message;
        this.error = error;
        this.code = code.toString();
    }

    public ErrorMessage(String requestId, String message, HttpStatus error, ErrorCodeEnum code) {
        this(message, error, code);
        this.requestId = requestId;
    }

    public ErrorMessage(ResponseMessage message, HttpStatus error) {
        this(message.toString(), error, message.getErrorCode());
    }

    public ErrorMessage(String requestId, ResponseMessage message, HttpStatus error) {
        this(message.toString(), error, message.getErrorCode());
        this.requestId = requestId;
    }

    public ErrorMessage(ResponseMessage message, HttpStatus error, Object data) {
        this(message.toString(), error, message.getErrorCode());
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public HttpStatus getError() {
        return error;
    }

    public void setError(HttpStatus httpStatus) {
        this.error = httpStatus;
        this.status = httpStatus.value();
    }

    public int getStatus() {
        return error.value();
    }

    public void setStatus(int status) {
        error = HttpStatus.valueOf(status);
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTimestamp() {
        return new Date().toString();
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    @Override
    public String toString() {
        return "ErrorMessage{" +
                "message='" + message + '\'' +
                ", title='" + title + '\'' +
                ", error=" + error +
                ", status=" + status +
                ", code='" + code + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", data=" + data +
                ", requestId='" + requestId + '\'' +
                '}';
    }
}