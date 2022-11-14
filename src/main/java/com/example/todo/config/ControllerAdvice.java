package com.example.todo.config;

import com.example.todo.exception.EntityNotFoundException;
import com.example.todo.model.RestErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(value = EntityNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public RestErrorResponse handle404Error(Exception e) {
        RestErrorResponse response = new RestErrorResponse();
        response.setDescription(e.getMessage());
        return response;
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public RestErrorResponse handeError(Exception e) {
        RestErrorResponse response = new RestErrorResponse();
        response.setDescription(e.getMessage());
        return response;
    }

}
