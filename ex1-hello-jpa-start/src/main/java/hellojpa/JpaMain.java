package hellojpa;

import jakarta.persistence.*;

public class JpaMain {
	public static void main(String[] args) {

		// 애플리케이션 실행시 최초 1회만
		//hello 설정 정보를 읽어서 실행!
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		// 매니저 인스턴스를 트랜잭션마다 호출 (1회 성)
		EntityManager em = emf.createEntityManager();
		// 꼭! 트랜젝션 생성
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		try {
			// 객체 생성, 저장
			Member member = new Member(1L, "user00");
			em.persist(member);
			
			// 객체 검색
//			Member member = em.find(Member.class, 1L);
//			System.out.println("member = " + member.toString());

			// 객체 업데이트(함수 없음, set 자체가 업데이트!)
//			Member member = findMember(1L, em);
//			member.setName("user01");
			
			// 객체 삭제
//			Member member = em.find(Member.class, 1L);
//			em.remove(member);
			
			tx.commit(); // tx 커밋!
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
		}
		emf.close();
	}
}
