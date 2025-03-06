package com.workbook.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class BoardListReplyCountDto {
    private Long bno;
    private String title;
    private String writer;
    private LocalDateTime regDate;
    private Long replyCount;
}
