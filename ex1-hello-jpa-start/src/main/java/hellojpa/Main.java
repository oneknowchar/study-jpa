package hellojpa;

import java.util.ArrayList;
import java.util.List;

import hellojpa.entity.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Main {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("helloJpa");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		
		try {
			List<Member> newMembers = makeMember(100, em, tx);	//신규 데이터 생성
			
			//영속성 초기화
			//em.flush();	
			em.clear();
			
			//전체 조회
			//List<Member> saveMembers = em.createQuery("select m from Member as m order by m.id", Member.class).getResultList();
			
			//페이징 조회
			List<Member> saveMembers = em.createQuery("select m from Member as m order by m.id desc", Member.class)
					.setFirstResult(0)
					.setMaxResults(10)
					.getResultList();
			
			System.out.println(saveMembers.size());
			for(Member findMember : saveMembers) {
				System.out.println("findMember Name = " + findMember.getUserName());
			}
			
			
		/* select
	        m 
	    from
	        Member as m 
	    order by
	        m.id desc  select
	            m1_0.id,
	            m1_0.age,
	            m1_0.createdBy,
	            m1_0.createdDate,
	            m1_0.lastModifiedBy,
	            m1_0.lastModifiedDate,
	            m1_0.team_id,
	            m1_0.userName 
	        from
	            Member m1_0 
	        order by
	            m1_0.id desc 
	        offset
	            ? rows 
	        fetch
	            first ? rows only
			*/
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
		}
		emf.close();
	}
	
	/**
	 * 페이징 연습 더미 데이터 생성
	 * 영속성 EntityMananger, DB insert
	 * @param num 디비에 저장하고 싶은 회원의 수
	 * @param em 영속성 관리를 위한 엔티티 메니저
	 * @return result<boolean>
	 */
	public static List<Member> makeMember(int num, EntityManager em, EntityTransaction tx) {
		List<Member> members = new ArrayList<>();
		
		for(int i = 0; i < num; i++) {
			Member member = new Member();
			
			if(i < 10) {
				member.setUserName("user0" + i);
			}else {
				member.setUserName("user" + i);
			}
			
			//영속성 저장
			members.add(member);
			em.persist(member);
		}
		
		tx.commit();	//커밋 //DB 저장
		
		return members;
	}
}
