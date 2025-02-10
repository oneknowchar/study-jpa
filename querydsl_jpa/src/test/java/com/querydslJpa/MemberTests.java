package com.querydslJpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;

@SpringBootTest
@Transactional
public class MemberTests {
	@Autowired
	private EntityManager em;

	@Test
	void contextLoads() {
	
	}
}
