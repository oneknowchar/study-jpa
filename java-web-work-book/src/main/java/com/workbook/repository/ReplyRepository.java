package com.workbook.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.workbook.entity.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long>{

    @Query("select r from Reply r where r.board.bno = :bno")
    //@Param("bno")을 사용하여 쿼리의 :bno와 메서드 매개변수 bno를 매핑해야 합니다.(생략 불가)
    Page<Reply> listOfBoard(@Param("bno") Long bno, Pageable pageable);
}
