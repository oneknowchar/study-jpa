package com.workbook;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
@MapperScan("com.workbook.repository.mybatis")
public class JavaWebWorkBookApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaWebWorkBookApplication.class, args);
    }

}
