package com.workbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.workbook.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
    @Query(value = "select now()", nativeQuery = true)
    String getTime();
}
