package com.example.demo.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class AllNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(AllNotException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String allNotFoundHandler(AllNotException ex){
        return ex.getMessage();
    }
}
