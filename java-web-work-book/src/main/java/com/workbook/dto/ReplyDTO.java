package com.workbook.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReplyDTO {
    private Long rno;

    @NotNull
    private Long bno;

    @NotEmpty
    private String replyText;
    @NotEmpty
    private String replyer;

    private LocalDateTime regDate, modDate;
}
