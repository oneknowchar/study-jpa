package jpabook.jpashop;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jpabook.jpashop.domain.Member;

@Repository
public class MemberRepository {
	@PersistenceContext
	private EntityManager em;
	
	public Long saveMember(Member member) {
		em.persist(member);
		return member.getId();
	}
	
	public Member findMember(Long id) {
		Member findMember = em.find(Member.class, id);
		return findMember;
	}
}
