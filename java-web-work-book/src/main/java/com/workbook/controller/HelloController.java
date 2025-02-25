package com.workbook.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Hello API", description = "Swagger 예제 API")
public class HelloController {

    @Operation(summary = "Hello 메시지 반환", description = "이 API는 'Hello, {name}!' 메시지를 반환합니다.")
    @GetMapping("/hello")
    public String hello(@RequestParam(name = "name") String name) {
        return "Hello, " + name + "!";
    }
}