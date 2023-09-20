package com.eliteprogramming.noticationservice.dto;

import com.eliteprogramming.noticationservice.enums.ResponseMessage;
import org.springframework.http.HttpStatus;

public class Response {

    private String message;
    private Object data;
    private int status = HttpStatus.OK.value();

    public Response(String message) {
        this.message = message;
    }

    private Response(String message, Object data) {
        this.message = message;
        this.data = data;
    }

    public Response(ResponseMessage responseMessage) {
    }

    public Response(String message, String data, ResponseMessage responseMessage) {
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
