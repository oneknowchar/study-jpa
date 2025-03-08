package com.workbook.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.workbook.dto.BoardListReplyCountDto;
import com.workbook.dto.PageRequestDto;
import com.workbook.entity.Board;

public interface BoardService {
    Page<Board> search1ByQuerydslRepositorySupport(Pageable pageable);

    Page<Board> search1ByJPAQueryFactory(Pageable pageable);

    Page<Board> searchAll(String[] types, String keyword, Pageable pageable);

    Page<BoardListReplyCountDto> searchWithReplyCount(String[] types, String keyword, Pageable pageable);
}
