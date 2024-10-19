package hellojpa.club;

import java.util.List;

import hellojpa.club.entity.Member;
import hellojpa.club.entity.Team;
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

		System.out.println("[START]===========================================");
		
		try {
			Team teamA = new Team();
			teamA.setName("teamA");
			em.persist(teamA);
			
			Member member = new Member();
			member.setUserName("user00");
			member.chageTeam(teamA);	//멤버를 팀에 세팅하면서, 팀에 멤버리스트에도 add() 진행!
			em.persist(member);
			
//			em.flush();	//영속성 청소
//			em.clear();	//db에서 직접 조회
			
			Member findMember = em.find(Member.class, member.getId());
			List<Member>findMembers = findMember.getTeam().getMembers();	//양방향 연관관계 실행!
			
			for(Member item : findMembers) {
				System.out.println("findMembers = " + item);
			}
			System.out.println("[INFO]===========================================");
//			System.out.println("Member INFO = " + member);
//			System.out.println("Team INFO = " + teamA);
			System.out.println("findTeam INFO = " + findMembers);
			System.out.println("[INFO]===========================================");
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
		}
		emf.close();
		System.out.println("[END]===========================================");
	}
}
