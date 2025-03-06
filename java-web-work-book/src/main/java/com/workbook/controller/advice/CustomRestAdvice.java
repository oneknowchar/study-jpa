package com.workbook.controller.advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class CustomRestAdvice {

    @ExceptionHandler(BindException.class)
    @ResponseStatus(value = HttpStatus.EXPECTATION_FAILED)
    public ResponseEntity<Map<String, String>> handleBindException(BindException e){
        System.out.println(e);

        Map<String, String> errorMap = new HashMap<>();
        if(e.hasErrors()) {
            BindingResult bindingResult = e.getBindingResult();
            bindingResult.getFieldErrors().forEach(fieldError ->{
                errorMap.put(fieldError.getField(), fieldError.getCode());
            });

        }
        return ResponseEntity.badRequest().body(errorMap);
    }

}
