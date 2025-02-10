package com.workbook.repository;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.workbook.dto.PageRequestDto;
import com.workbook.dto.TodoDto;
import com.workbook.entity.Todo;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
@Transactional
public class TodoRepository {
	
	private final EntityManager em;
	
	public List<Todo> find() {
		//JPA에서 복수개의 데이터를 가져오려면 직접 쿼리를 작성해야 함
		String sql = "select t from Todo t order by tno desc";	
		
		List<Todo> todos = em.createQuery(sql, Todo.class).getResultList();
		
		return todos;
	}

	public Todo findById(Long tno) {
		return em.find(Todo.class, tno);
	}
	
	public void register(Todo todo) {
		em.persist(todo);
	}

	public void deleteByRemove(Todo todo) {
		Todo findTodo = em.find(Todo.class, todo.getTno());
		
		if(findTodo == null) {
			throw new RuntimeException("Todo with tno " + todo.getTno() + " not found.");
		}
		
		em.remove(findTodo);
	}

	public List<Todo> selectList(PageRequestDto pageRequestDto) {
		//jpql은 Limit 함수지원 X
//		String sql = "select t from Todo t order by tno desc limit =:skip, =:size";
//		List<TodoDto> todos = em.createQuery(sql,TodoDto.class)
//		.setParameter("skip", pageRequestDto.getSkip())
//		.setParameter("size", pageRequestDto.getSize())
		
		//이렇게 해야 함
		String sql = "select t from Todo t order by tno desc";
		
		List<Todo> todos = em.createQuery(sql, Todo.class)
		.setFirstResult(pageRequestDto.getSkip())
		.setMaxResults(pageRequestDto.getSize())
		.getResultList();
		
		return todos;
	}

	public int getCount(PageRequestDto pageRequestDto) {
		//Count 쿼리의 리턴타입은 Long이다. 따라서 Long으로 받고 필요시 형변환.
		String sql = "select count(t) from Todo t";
		return em.createQuery(sql, Long.class).getSingleResult().intValue();
	}

}
