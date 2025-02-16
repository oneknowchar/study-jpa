package com.workbook.entity;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.workbook.dto.TodoDto;
import com.workbook.mapper.MyTodoMapper;
import com.workbook.service.TodoService;

import jakarta.persistence.EntityManager;

@SpringBootTest
@Transactional
public class TodoTests {
    @Autowired
    private EntityManager em;

    @Autowired
    private TodoService todoService;

    @Autowired
    private MyTodoMapper myTodoMapper;
//
//	@Test
//	@Rollback(value = false)
//	void insert() {
//		Todo todo = new Todo();
//		todo.setTitle("user00000");
//		todo.setFinished(LocalDateTime.now());
//		todo.setDueDate(LocalDateTime.now());
//		em.persist(todo);
//
////		em.flush();
////		em.clear();
//
//		Todo findTodo = em.find(Todo.class, todo.getTno());
//
//		System.out.println(findTodo.getTitle());
//	}

//	@BeforeEach
//	public void beforeEach() {
//		for(int i = 0; i< 100; i++) {
//			Todo todo = new Todo();
//			todo.setTitle("testtest");
//			todo.setFinished(LocalDateTime.now());
////			todo.setDueDate(LocalDateTime.now());
//			em.persist(todo);
//			em.flush();
//			em.clear();
//		}
//	}
//
//	@Test
//	@Rollback(value = false)
//	public void update() {
//		String sql = "select t from Todo t where tno%2 = 0";	//검색 조건 추가.
//		List<Todo> todos = em.createQuery(sql, Todo.class).getResultList();
//
////		for(Todo todo : todos) {
////			if(todo.getTno() % 2 == 0 ) {
////				System.out.println(todo.getTitle());
////				todo.setTitle("updated!!");
////			}
////		}
//		//모든 영속성이 업데이트 되고나서야 실행해야함
//		em.flush();
//		em.clear();
//	}

    @Test
    public void getTime() {
        String str = myTodoMapper.getTime();
        System.out.println(str + "123213");
    }

    @Test
    public void selectTodoList() {
        List<TodoDto> selectTodoList = myTodoMapper.selectTodoList();

        System.out.println(selectTodoList.size());
    }
}
