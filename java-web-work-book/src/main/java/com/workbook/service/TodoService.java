package com.workbook.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.workbook.dto.PageRequestDto;
import com.workbook.dto.PageResponseDto;
import com.workbook.dto.TodoDto;
import com.workbook.entity.Todo;
import com.workbook.repository.TodoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    private final ModelMapper modelMapper;

    public List<TodoDto> findTodos() {

        return todoRepository.find()
                .stream()
                .map(vo -> modelMapper.map(vo, TodoDto.class))
                .collect(Collectors.toList());
    }

    public TodoDto findById(Long tno) {
        return modelMapper.map(todoRepository.findById(tno), TodoDto.class);
    }

    public void register(TodoDto todoDto) {
        todoRepository.register(modelMapper.map(todoDto, Todo.class));
    }

    public void remove(TodoDto todoDto) {
        todoRepository.deleteByRemove(modelMapper.map(todoDto, Todo.class));
    }

    public List<TodoDto> selectList(PageRequestDto pageRequestDto){
        return todoRepository.selectList(pageRequestDto)
                .stream()
                .map(vo -> modelMapper.map(vo, TodoDto.class))
                .collect(Collectors.toList());
    }

    public PageResponseDto<TodoDto>getList(PageRequestDto pageRequestDto){
        List<TodoDto> todoList = todoRepository.selectList(pageRequestDto)
                .stream()
                .map(vo->modelMapper.map(vo, TodoDto.class))
                .collect(Collectors.toList());
        int count = todoRepository.getCount(pageRequestDto);

        PageResponseDto<TodoDto> pageResponseDto = PageResponseDto.<TodoDto>withAll()
                .dtoList(todoList)
                .total(count)
                .pageRequestDto(pageRequestDto)
                .build();

        return pageResponseDto;
    }

    public int getCount(PageRequestDto pageRequestDto) {
        return todoRepository.getCount(pageRequestDto);
    }

    public void modify(TodoDto todoDto) {
        todoRepository.modify(todoDto);
    }

}
