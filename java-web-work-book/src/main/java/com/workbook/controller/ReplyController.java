package com.workbook.controller;

import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.workbook.dto.ReplyDTO;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/replies")
@Slf4j
public class ReplyController {

    @Operation(summary = "댓글 등록", description = "POST 방식으로 댓글 등록")
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Long>> register(@Valid @RequestBody ReplyDTO replyDTO, BindingResult  bindingResult) throws BindException {
        log.debug("replyDTO =++>> {}", replyDTO);

        if(bindingResult.hasErrors()) {
            //에러 발견시 CustomRestAdvice
            throw new BindException(bindingResult);
        }

        Map<String, Long> resultMap = Map.of("rno", replyDTO.getRno());

        return ResponseEntity.ok(resultMap);
    }
}
