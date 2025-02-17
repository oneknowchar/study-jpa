package com.workbook.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
public class SampleJSONController {

    @PostMapping("/helloArr")
    public List<String> helloArr(Model model) {
        log.debug("==> [start ] API controlle");

//        String [] strArr = new String[] {"AAA", "BBB", "CCC"};

        List<String> list = Arrays.asList("aaa", "bbb", "ccc", "ddd");
        return list;
    }
}
