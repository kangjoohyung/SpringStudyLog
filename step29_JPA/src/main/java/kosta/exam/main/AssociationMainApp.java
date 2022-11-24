package kosta.exam.main;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import kosta.exam.domain.Member;
import kosta.exam.domain.Team;

public class AssociationMainApp {

	public static void main(String[] args) {
		System.out.println("PK와 FK설정");
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("JPAProject");
		EntityManager em=emf.createEntityManager();
		EntityTransaction tx=em.getTransaction();
		tx.begin();
		//1. 등록
//		Team t1=new Team(null, "team01");
//		Team t2=new Team(null, "team02");
//		Team t3=new Team(null, "team03");
//		
//		em.persist(t1);
//		em.persist(t2);
//		em.persist(t3);
//		
//		em.persist(new Member(null, "1희정1", 20, t1));
//		em.persist(new Member(null, "1희정2", 20, t1));
//		em.persist(new Member(null, "1희정3", 20, t1));
//
//		em.persist(new Member(null, "2희정1", 20, t2));
//		em.persist(new Member(null, "2희정2", 20, t2));
//
//		em.persist(new Member(null, "3희정1", 20, t3));
		
		//2. 회원 검색
//		Member m=em.find(Member.class, 3L);
//		System.out.println(m);
//		System.out.println("--------------------------");
//		System.out.println(m.getTeam());
		
		//3. 팀에 소속된 사원의 정보를 검색하고 싶다.
		Team team=em.find(Team.class, 1L);
		System.out.println(team);
		
		tx.commit();
	}
}
