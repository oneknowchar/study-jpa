package com.workbook.mapper;

import org.springframework.stereotype.Component;

import com.workbook.dto.TodoDto;
import com.workbook.entity.Todo;

/**
 * 
 * 선택 기준 static 메서드 사용 @Component 사용 단순 변환 유틸 클래스 ✅ 적합 ❌ 불필요 의존성 주입이 필요함 ❌ 불가능 ✅
 * 적합 테스트에서 Mocking이 필요함 ❌ 어렵다 ✅ 쉬움 확장 가능성이 있음 ❌ 비효율적 ✅ 적절
 */

/*
 * 현재 Mapper가 단순 변환 로직만 한다면 static 방식이 더 적절합니다. 하지만, 의존성 주입이 필요하거나, 테스트에서
 * Mocking을 자주 해야 한다면 @Component로 등록하는 것이 좋습니다
 */
@Component
public class TodoMapper {

	public TodoDto toDto(Todo todo) {
		return TodoDto
				.builder()
				.tno(todo.getTno())
				.title(todo.getTitle())
				.dueDate(todo.getDueDate())
				.finished(todo.getFinished())
				.writer(todo.getWriter())
				.build();	
		}

	public Todo toVo(TodoDto todoDto) {
		return Todo
				.builder()
				.tno(todoDto.getTno())
				.title(todoDto.getTitle())
				.dueDate(todoDto.getDueDate())
				.finished(todoDto.getFinished())
				.writer(todoDto.getWriter())
				.build();
	}

}
