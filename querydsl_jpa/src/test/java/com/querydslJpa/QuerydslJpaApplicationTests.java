package com.querydslJpa;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydslJpa.entity.Hello;
import com.querydslJpa.entity.QHello;

import jakarta.persistence.EntityManager;

@SpringBootTest
@Transactional
class QuerydslJpaApplicationTests {
	@Autowired
	private EntityManager em;

	@Test
	void contextLoads() {
		Hello hello = new Hello();
		em.persist(hello);
		
		JPAQueryFactory query = new JPAQueryFactory(em);
		QHello qHello = QHello.hello;
		
		Hello result = query.selectFrom(qHello).fetchOne();
		
		assertThat(result).isEqualTo(hello);
		assertThat(result.getId()).isEqualTo(hello.getId());
	}

}
