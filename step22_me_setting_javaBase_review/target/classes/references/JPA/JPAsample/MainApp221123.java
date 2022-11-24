package references.JPA.JPAsample;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import kosta.exam.domain.Customer;

public class MainApp221123 {
	public static void main(String[] args) {
		System.out.println("JPA ?‹œ?‘");
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("JPAProject");
		EntityManager em=emf.createEntityManager();
		
		EntityTransaction tx=em.getTransaction();
		tx.begin(); //beginë¶??„° ?Š¸?œ? ?…˜ ?‹œ?‘, commit();?œ¼ë¡? ì»¤ë°‹?•œ?‹¤(??™ ?•ˆ?¨->?„¤? •?? ?ˆ?‚˜)
		//1.?“±ë¡?
		//builder().~~~.build() ->lombok ê¸°ëŠ¥
//		em.persist(Customer.builder().addr("?„œ?š¸5").age(20).birthDay(new Date()).userName("?¬? •5").build());
//		em.persist(Customer.builder().addr("?„œ?š¸6").age(20).birthDay(new Date()).userName("?¬? •6").build());
//		em.persist(Customer.builder().addr("?„œ?š¸7").age(20).birthDay(new Date()).userName("?¬? •7").build());
//		em.persist(Customer.builder().addr("?„œ?š¸8").age(20).birthDay(new Date()).userName("?¬? •8").build());
		
		//2.ê²??ƒ‰(id-pk???ƒ-?— ?•´?‹¹?•˜?Š” ? •ë³? ê²??ƒ‰) ->?ƒ?„±? ?ˆ?–´?•¼?•¨, NoArg, AllArg ?…‹?Š¸ë¡? ?†“ê¸?
//		Customer cu=em.find(Customer.class, 3L); // ->idê°? 3?¸ Long???…
//		System.out.println(cu);
		
		//3. ?ˆ˜? •?•˜ê¸? ->?”°ë¡? update?Š” ?—†ê³? ì°¾ì?ê²ƒì„ ê°?ì§?ê³? set?œ¼ë¡? ë¡¬ë³µ?œ¼ë¡? ?‚¬?š©?›„ ì»¤ë°‹?•˜?—¬ ?—…?°?´?Š¸ê¸°ëŠ¥ ?‚¬?š©
//		CU.SETADDR("ë³„ë‚˜?¼");
//		CU.SETAGE(24);
//		cu.setUserName("?˜ˆ?••");
		
		//4. ?‚­? œ?•˜ê¸? -> ê²??ƒ‰?„ ê¸°ì??œ¼ë¡? remove?•˜ë©? ?¨
//		em.remove(cu);
		
		////////////////////////////////////////////////
		//JPQLë¬¸ë²• : ê°ì²´ì¤‘ì‹¬?œ¼ë¡? ì¿¼ë¦¬ë¥? ?‘?„±?•˜?Š” ê²?->???†Œë¬¸ìë¥? ê°?ë¦¬ê²Œ ?¨
		//5. ?´ë¦„ì´ ?¬? •2?¸ ? ˆì½”ë“œë¥? ê²??ƒ‰
//		String sql="select c from Customer c where c.userName='?¬? •'";//Entity ?´ë¦„ìœ¼ë¡? ?‚¬?š©, allias ?•„?ˆ˜, ???†Œë¬¸ì êµ¬ë¶„, ì»¬ëŸ¼,?…Œ?´ë¸? ?“±?— ?ë°? ?´ë¦„ìœ¼ë¡? ?„£ê¸? 
//		List<Customer> list=em.createQuery(sql, Customer.class).getResultList(); //?‚¬?š©?? •?˜ ì¿¼ë¦¬ ?‚¬?š© : createQuery
//		System.out.println(list);
		
		//6.?´ë¦„ê³¼ ì£¼ì†Œë¥? ? „?‹¬ë°›ì•„ ê²??ƒ‰?•˜ê¸? 2ê°?ì§? - ì£¼ì†Œ?Š” like?—°?‚°? -> :ë³??ˆ˜ (ì»¬ëŸ¼ëª? ?•´?‹¹ ë³??ˆ˜) / ?1 (?¬ì§??…˜)
//		String sql="select c from Customer c where c.userName=:name or c.addr like :addr";
//		List<Customer> list=
//				em.createQuery(sql, Customer.class)
//				.setParameter("name", "?¬? •")
//				.setParameter("addr", "%?š¸%")
//				.getResultList(); //?‚¬?š©?? •?˜ ì¿¼ë¦¬ ?‚¬?š© : createQuery
//		System.out.println(list);
		
		String sql="select c from Customer c where c.userName=?1 or c.addr like ?2";
		List<Customer> list=
				em.createQuery(sql, Customer.class)
				.setParameter(1, "?¬? •")
				.setParameter(2, "%? œì£?%")
				.getResultList(); //?‚¬?š©?? •?˜ ì¿¼ë¦¬ ?‚¬?š© : createQuery
		System.out.println(list);
		
		tx.commit();
	}
}
