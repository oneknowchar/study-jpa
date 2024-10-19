package hellojpa;

import java.util.List;

import hellojpa.entity.Member;
import hellojpa.entity.Team;
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
			Team team = new Team();
			team.setTeamName("powerTeam");
			
			Member member = new Member();
			member.setUserName("user00");
			member.setTeam(team);
			
			em.persist(team);
			em.persist(member);
			
			em.flush();
			em.clear();
			
			
			Member findMember = em.find(Member.class, member.getId());
			//
			System.out.println("findMember name = " + findMember.getUserName());
			System.out.println("team name = " + findMember.getTeam().getTeamName());
			
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
		}
		emf.close();
	}
}
