package com.workbook.repository;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.workbook.entity.Member;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
@Transactional
public class MemberRepository {
	
	private final EntityManager em;
	
	public List<Member> find() {
		//JPA에서 복수개의 데이터를 가져오려면 직접 쿼리를 작성해야 함
		String sql = "select t from Member m order by id desc";
		
		List<Member> members = em.createQuery(sql, Member.class).getResultList();
		
		return members;
	}

	public Member findById(Long id) {
		return em.find(Member.class, id);
	}
	
	public void register(Member member) {
		em.persist(member);
	}

	public void deleteByRemove(Member member) {
		Member findMember = em.find(Member.class, member.getId());
		
		if(findMember == null) {
			throw new RuntimeException("Todo with tno " + findMember.getId() + " not found.");
		}
		
		em.remove(findMember);
	}

}
