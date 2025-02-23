package com.workbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.workbook.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long>{

}
