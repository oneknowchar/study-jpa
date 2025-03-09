package com.workbook.dto;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

@MappedSuperclass
/*
 * AuditingEntityListener란?
 * 엔티티가 데이터베이스에 추가되거나 변경될 때, 자동으로 시간 값을 지정할 수 있다.
 * 이놈을 활성화 하기 위해선 Main메서드가있는 어플리케이션.java 파일에
 * @EnableJpaAuditing 를 추가한다.
 */
@EntityListeners(value = { AuditingEntityListener.class })
@Getter
public class BaseEntity {

    @CreatedDate
    @Column(name = "regDate", updatable = false)
    private LocalDateTime regDate;

    @LastModifiedDate
    @Column(name = "modDate")
    private LocalDateTime modDate;
}
