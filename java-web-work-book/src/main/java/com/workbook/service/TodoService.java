package com.workbook.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.workbook.dto.PageRequestDto;
import com.workbook.dto.TodoDto;
import com.workbook.mapper.TodoMapper;
import com.workbook.repository.TodoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TodoService {
	
	private final TodoRepository todoRepository;
	private final TodoMapper mapper;
	
	public List<TodoDto> findTodos() {

		return todoRepository.find()
				.stream()
				.map(vo -> mapper.toDto(vo))
				.collect(Collectors.toList());
	}

	public TodoDto findById(Long tno) {
		return mapper.toDto(todoRepository.findById(tno));
	}

	public void register(TodoDto todoDto) {
		todoRepository.register(mapper.toVo(todoDto));
	}

	public void remove(TodoDto todoDto) {
		todoRepository.deleteByRemove(mapper.toVo(todoDto));
	}
	
	public List<TodoDto> selectList(PageRequestDto pageRequestDto){
		return todoRepository.selectList(pageRequestDto)
				.stream()
				.map(vo -> mapper.toDto(vo))
				.collect(Collectors.toList());
	}

	public int getCount(PageRequestDto pageRequestDto) {
		return todoRepository.getCount(pageRequestDto);
	}

}
