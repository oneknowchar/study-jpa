package hellojpa;

import java.util.List;

import hellojpa.entity.Member;
import hellojpa.entity.Team;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;

public class Main {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("helloJpa");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		
		try {
			Member member1 = new Member();
			member1.setUserName("user01");
			
			Member member2 = new Member();
			member2.setUserName("user02");
			
			em.persist(member1);
			em.persist(member2);
			
			em.flush();
			em.clear();
			
			//JPQL_ example_1
			List<Member> result1 = em
									.createQuery("select m From Member m where m.userName like 'user%'", Member.class)
									.getResultList();
			
			//JPQL_ example_2
			List<Member> result2 = em
									.createQuery("select m From Member m where m.id > 10", Member.class)
									.getResultList();
			
			Member result3 = null;
			try {
				//결과가 없으면 Exception
				result3 = em.createQuery("select m From Member m where m.userName = :userName", Member.class)
						.setParameter("userName", "user007")
						.getSingleResult();
			}catch (NoResultException e) {
				e.printStackTrace();
			}
			
			System.out.println(result1);
			System.out.println(result2);
			System.out.println(result3);
			
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
