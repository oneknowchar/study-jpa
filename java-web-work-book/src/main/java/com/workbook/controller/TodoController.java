package com.workbook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.workbook.dto.PageRequestDto;
import com.workbook.dto.TodoDto;
import com.workbook.service.TodoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/todo")
@Slf4j
public class TodoController {

    private final TodoService todoService;

    @GetMapping("/index")
    public String todoIndex(Model model, @Valid PageRequestDto pageRequestDto, BindingResult bindingResult) {

        //에러가 난다면, 디폴트 값인 1과 10을 준다. 따라서 에러가 나더라도 화면이 깨질일이 없다.
        if(bindingResult.hasErrors()) {
            pageRequestDto = PageRequestDto.builder().build();
        }

        model.addAttribute("responseDto", todoService.getList(pageRequestDto));

        return "todo/index";
    }

    @GetMapping("/read")
    @ResponseBody
    public TodoDto read(@RequestParam("tno") Long tno) {
        return todoService.findById(tno); // TodoDto.class
    }

    @GetMapping("/register")
    public String register() {
        return "todo/register";
    }

    @PostMapping("/register")
    public String registerPost(@Valid TodoDto todoDto, BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {
        log.info("POST todo Register");

        if (bindingResult.hasErrors()) {
            log.info("has errors.......");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/todo/register";
        }

//		try {
//			todoService.register(todoDto);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

        return "redirect:/todo/index";

    }

    @PostMapping("/remove")
    public String remove(@RequestBody TodoDto todoDto) {
        try {
            todoService.remove(todoDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/todo/index";
    }

    @GetMapping("/ex5")
    public String getMethodName(RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("name", "abc");
        redirectAttributes.addFlashAttribute("result", "success");
        return "redirect:/todo/index";
    }

}
