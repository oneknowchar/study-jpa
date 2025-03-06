package com.workbook.entity;

import com.workbook.dto.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ToString(exclude = "board")//toString()  함수 생성시, board객체 제외(순환참조문제)
@Table(name = "Reply", indexes = {
        //Reply 테이블에는 bno 컬럼이 생성되므로, 인덱스 설정도 bno로 맞춰야 합니다. (board_bno ==> bno)
        @Index(name="idx_reply_board_bno", columnList = "bno")
})
public class Reply extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long rno;

    //private Long bno;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bno")
    private Board board;

    private String replyText;
    private String replyer;
}
