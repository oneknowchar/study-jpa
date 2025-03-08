package com.workbook.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.workbook.dto.BoardListReplyCountDto;
import com.workbook.dto.PageRequestDto;
import com.workbook.service.BoardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/")
    public Map<String, Object> getBoardList(@RequestBody PageRequestDto pageRequestDto) {
        Page<BoardListReplyCountDto>  data = boardService.searchWithReplyCount(
                pageRequestDto.getTypes()
                , pageRequestDto.getKeyword()
                , pageRequestDto.getPageable("bno")
                );

        Map<String, Object>outMap = new HashMap<>();
        outMap.put("data", data);
        outMap.put("pageRequestDto", pageRequestDto);
        return outMap;
    }
}
