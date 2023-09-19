package com.eliteprogramming.noticationservice.dto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseController {
    protected ResponseEntity<Object> sendResponse(Object object) {
        if (object instanceof ErrorMessage) {
            ErrorMessage err = (ErrorMessage) object;
            return sendResponse(object, err.getError());
        }
        return sendResponse(object, HttpStatus.OK);
    }

    private ResponseEntity<Object> sendResponse(Object object, HttpStatus httpStatus) {
        return ResponseEntity.status(httpStatus)
                .header("Content-Type", "application/json")
                .body(object);
    }
}