package com.workbook.controller;

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
    public Page<BoardListReplyCountDto> getBoardList(@RequestBody PageRequestDto pageRequestDto) {
        return boardService.searchWithReplyCount(
                pageRequestDto.getTypes()
                , pageRequestDto.getKeyword()
                , pageRequestDto.getPageable("bno")
                );
    }
}
