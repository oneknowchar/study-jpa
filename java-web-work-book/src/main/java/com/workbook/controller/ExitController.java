package com.workbook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;

@Controller
public class ExitController {
	@GetMapping("/wayOut")
	@ResponseBody
	public String wayOut(HttpSession httpSession) {
		
		httpSession.setAttribute("loginInfo", "jiseong");
		return "anyThing";
	}
}
