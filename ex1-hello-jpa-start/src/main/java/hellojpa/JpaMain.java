package hellojpa;

import jakarta.persistence.*;

public class JpaMain {
	public static void main(String[] args) {
		//시퀀스 전략 !
		//시퀀스 값을 50씩 증분하여 한 번의 request에선 50개 내부의 값을 사용하지만,
		//요청이 종료된 이후 새로운 호출이 온 경우 시퀀스에서 50을 증분하여 그 다음 값부터 진행된다.
		//50개를 전부 소비하지 않았더라도 allocationSize 만큼 시퀀스를 증분한 값을 이어서 진행!
		
		// 애플리케이션 실행시 최초 1회만
		// hello 설정 정보를 읽어서 실행!
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		for (int i = 0; i < 3; i++) {
			// 매니저 인스턴스를 트랜잭션마다 호출 (1회 성)
			EntityManager em = emf.createEntityManager();
			// 꼭! 트랜젝션 생성
			EntityTransaction tx = em.getTransaction();
			tx.begin();

			try {
				// 객체 생성, 저장
				Member member1 = new Member();
				member1.setName("user01");
				Member member2 = new Member();
				member2.setName("user02");
				Member member3 = new Member();
				member3.setName("user03");

				em.persist(member1);
				em.persist(member2);
				em.persist(member3);
				tx.commit(); // tx 커밋!
				System.out.println("=================================");
			} catch (Exception e) {
				tx.rollback();
			} finally {
				em.close();
			}
		}
		emf.close();
	}
}
