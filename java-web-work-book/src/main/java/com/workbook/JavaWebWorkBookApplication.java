package com.workbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
@ServletComponentScan
@SpringBootApplication
public class JavaWebWorkBookApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaWebWorkBookApplication.class, args);
	}

}
