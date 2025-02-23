package com.workbook;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@ServletComponentScan
@SpringBootApplication
@EnableJpaAuditing
@MapperScan("com.workbook.repository.mybatis")
public class JavaWebWorkBookApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaWebWorkBookApplication.class, args);
    }

}
