package com.querydslJpa;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.hibernate.query.criteria.JpaExpression;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydslJpa.entity.Member;
import com.querydslJpa.entity.QMember;
import com.querydslJpa.entity.Team;

import jakarta.persistence.EntityManager;

@SpringBootTest
@Transactional
public class QuerydslBasicTest {
	@Autowired
	EntityManager em;
	
	JPAQueryFactory queryFactory;
	
	@BeforeEach
	public void before() {
		queryFactory = new JPAQueryFactory(em);
		
		Team teamA = new Team("teamA");
		Team teamB = new Team("teamB");
		em.persist(teamA);
		em.persist(teamB);
		
		Member member1 = new Member("member1", 10, teamA);
		Member member2 = new Member("member2", 20, teamA);
		Member member3 = new Member("member3", 30, teamB);
		Member member4 = new Member("member4", 40, teamB);
		
		em.persist(member1);
		em.persist(member2);
		em.persist(member3);
		em.persist(member4);
	}
	
	@Test
	public void startJPQL() {
		//member1을 찾아라
		//직접 쿼리 작성시 오타 문제를 맞음..
		Member findMemberByJPQL = em 
		.createQuery("select m from Member m where m.username = :username", Member.class)
		.setParameter("username", "member1")
		.getSingleResult();
		
		Assertions.assertThat(findMemberByJPQL.getUsername()).isEqualTo("member1");
		
		
	}
	
	@Test
	public void startQuerydsl() {
		//@beforeEach로 전역으로 변수 설정 후 매번 테스트시 생성하게 만듬. 따라서 주석
//		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		
		QMember m = QMember.member;	//파라미터 바인딩을 위한 Q타입 객체 생성, db 쿼리시 alias가 된다.
		//따라서 같은 테이블을 조인할때 alias가 겹치면 안되니 직접 new QMember("m1 or m2"); 선언하여 사용하고
		//그 외에는 자동으로 제공하는 스태틱 객체 사용하면 됨
		
		Member findMemberByQuerydsl = queryFactory
			.select(m)
			.from(m)
			.where(m.username.eq("member1"))
			.fetchOne();
		
		Assertions.assertThat(findMemberByQuerydsl.getUsername()).isEqualTo("member1");
	}
	
	@Test
	public void search() {
		QMember m = QMember.member;
		Member findMember = queryFactory
				.selectFrom(m)
				//where 절 and() 체인으로 하는 방법1
				.where(
						m.username.eq("member1")
						.and(m.age.eq(10))
				)
				//and() 체인이 아닌 콤마(",")로 하는 방법2. 강사는 방법2 콤마를 추천함!!
//				.where(
//						m.username.eq("member1")
//						, (m.age.eq(10))
//				)
				.fetchOne();
		
		Assertions.assertThat(findMember.getUsername()).isEqualTo("member1");
	}
	
	@Test
	public void 서브쿼리() {
		QMember qMember = QMember.member;
		QMember memberSub = new QMember("memberSub");
		
		List<Member> result = queryFactory
				.selectFrom(qMember.member)
				.where(
					qMember.age.goe(
						JPAExpressions
							.select(memberSub.age.avg())
							.from(memberSub)
				))
				.fetch();
		
		Assertions.assertThat(result).extracting("age").containsExactly(30,40);
	}
	
	@Test
	public void basicCase() {
		QMember qMember = QMember.member;
		
		/*
		 * ## casebuilder 결과 ## 
		 * 
		 * case 
		 * 	when member1.age between 0 and 20 then 0~20 
		 * 	when member1.age between 21 and 30 then 21~31 
		 * 	else 기타 
		 * end
		 */
		StringExpression casebuilder = new CaseBuilder()
					.when(qMember.age.between(0, 20)).then("0~20")
					.when(qMember.age.between(21, 30)).then("21~31")
					.otherwise("기타");
		
		List<String> results = queryFactory
			//간단한 경우 직접 사용.
//			.select(QMember.member.age
//						.when(10).then("열살")
//						.when(20).then("스무살")
//						.otherwise("기타"))
			.select(casebuilder)	//복잡한 경우 코드 가독성을 위해 빌더 사용
			.from(qMember)
			.fetch();
		
		for(String s : results) {
			System.out.println("s = " + s);
		}
		
	}
}
