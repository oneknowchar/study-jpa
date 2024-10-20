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
			Team findTeam = em.find(Team.class, 2);
			
			Member findMember = findTeam.getMembers().get(0);
			
			em.flush();
			em.clear();
			
			//JPQL_ example_1
			List<Member> result1 = em
									.createQuery("select m From Member m where m.id like 'user%'", Member.class)
									.getResultList();
			
			//JPQL_ example_2
			List<Member> result2 = em
									.createQuery("select m From Member m where m.id > 10", Member.class)
									.getResultList();
			
			System.out.println(result1);
			System.out.println(result2);
			
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
