package references.JPA.JPAsample;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import kosta.exam.domain.Member;
import kosta.exam.domain.Team;

public class AssociationMainApp221124 {

	public static void main(String[] args) {
		System.out.println("PK?? FK?��?��");
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("JPAProject");
		EntityManager em=emf.createEntityManager();
		EntityTransaction tx=em.getTransaction();
		tx.begin();
		//1. ?���?
//		Team t1=new Team(null, "team01", null);
//		Team t2=new Team(null, "team02");
//		Team t3=new Team(null, "team03");
//		
//		em.persist(t1);
//		em.persist(t2);
//		em.persist(t3);
//		
//		em.persist(new Member(null, "1?��?��1", 20, t1));
//		em.persist(new Member(null, "1?��?��2", 20, t1));
//		em.persist(new Member(null, "1?��?��3", 20, t1));
//
//		em.persist(new Member(null, "2?��?��1", 20, t2));
//		em.persist(new Member(null, "2?��?��2", 20, t2));
//
//		em.persist(new Member(null, "3?��?��1", 20, t3));
		
		//2. ?��?�� �??��
//		Member m=em.find(Member.class, 3L);
//		System.out.println(m);
//		System.out.println("--------------------------");
//		System.out.println(m.getTeam());
		
		//3. ???�� ?��?��?�� ?��?��?�� ?��보�?? �??��?���? ?��?��.
		Team221124 team=em.find(Team221124.class, 1L);
		System.out.println(team);
		
		tx.commit();
	}
}
