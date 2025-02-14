/**
 * TFSKR ECONTRACT CHANNEL (TOYOTA FINANCIAL SERVICES KOREA) version 1.0
 * Copyright ⓒ 2023 TOYOTA FINANCIAL SERVICES KOREA CO., LTD. All rights reserved.
 * @Path : com.workbook.controller
 * @FileName : IndexController.java
 * @Creator : pmg
 * @CreateDate : 2025. 2. 13.
 * @Version 1.0
 */
package com.workbook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(RedirectAttributes rttr) {
        return "redirect:/todo/index";
    }
}
