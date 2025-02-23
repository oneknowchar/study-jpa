package com.workbook.repository.mybatis;

import java.util.List;

import com.workbook.dto.TodoDto;

public interface MyTodoMapper {

    public String getTime();

    public List<TodoDto> selectTodoList();
}
