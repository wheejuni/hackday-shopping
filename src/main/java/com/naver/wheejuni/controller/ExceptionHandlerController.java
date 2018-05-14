package com.naver.wheejuni.controller;

import com.naver.wheejuni.exceptions.file.NotSupportedFiletypeException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class ExceptionHandlerController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = NotSupportedFiletypeException.class)
    public String fileTypeException(NotSupportedFiletypeException e) {
        return e.getMessage();
    }
}
