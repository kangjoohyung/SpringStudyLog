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
		System.out.println("JPA ??");
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("JPAProject");
		EntityManager em=emf.createEntityManager();
		
		EntityTransaction tx=em.getTransaction();
		tx.begin(); //beginλΆ??° ?Έ?? ? ??, commit();?Όλ‘? μ»€λ°??€(?? ??¨->?€? ?? ??)
		//1.?±λ‘?
		//builder().~~~.build() ->lombok κΈ°λ₯
//		em.persist(Customer.builder().addr("??Έ5").age(20).birthDay(new Date()).userName("?¬? 5").build());
//		em.persist(Customer.builder().addr("??Έ6").age(20).birthDay(new Date()).userName("?¬? 6").build());
//		em.persist(Customer.builder().addr("??Έ7").age(20).birthDay(new Date()).userName("?¬? 7").build());
//		em.persist(Customer.builder().addr("??Έ8").age(20).birthDay(new Date()).userName("?¬? 8").build());
		
		//2.κ²??(id-pk???-? ?΄?Ή?? ? λ³? κ²??) ->??±? ??΄?Ό?¨, NoArg, AllArg ??Έλ‘? ?κΈ?
//		Customer cu=em.find(Customer.class, 3L); // ->idκ°? 3?Έ Long???
//		System.out.println(cu);
		
		//3. ?? ?κΈ? ->?°λ‘? update? ?κ³? μ°Ύμ?κ²μ κ°?μ§?κ³? set?Όλ‘? λ‘¬λ³΅?Όλ‘? ?¬?©? μ»€λ°??¬ ??°?΄?ΈκΈ°λ₯ ?¬?©
//		CU.SETADDR("λ³λ?Ό");
//		CU.SETAGE(24);
//		cu.setUserName("??");
		
		//4. ?­? ?κΈ? -> κ²??? κΈ°μ??Όλ‘? remove?λ©? ?¨
//		em.remove(cu);
		
		////////////////////////////////////////////////
		//JPQLλ¬Έλ² : κ°μ²΄μ€μ¬?Όλ‘? μΏΌλ¦¬λ₯? ??±?? κ²?->???λ¬Έμλ₯? κ°?λ¦¬κ² ?¨
		//5. ?΄λ¦μ΄ ?¬? 2?Έ ? μ½λλ₯? κ²??
//		String sql="select c from Customer c where c.userName='?¬? '";//Entity ?΄λ¦μΌλ‘? ?¬?©, allias ??, ???λ¬Έμ κ΅¬λΆ, μ»¬λΌ,??΄λΈ? ?±? ?λ°? ?΄λ¦μΌλ‘? ?£κΈ? 
//		List<Customer> list=em.createQuery(sql, Customer.class).getResultList(); //?¬?©?? ? μΏΌλ¦¬ ?¬?© : createQuery
//		System.out.println(list);
		
		//6.?΄λ¦κ³Ό μ£Όμλ₯? ? ?¬λ°μ κ²???κΈ? 2κ°?μ§? - μ£Όμ? like?°?°? -> :λ³?? (μ»¬λΌλͺ? ?΄?Ή λ³??) / ?1 (?¬μ§??)
//		String sql="select c from Customer c where c.userName=:name or c.addr like :addr";
//		List<Customer> list=
//				em.createQuery(sql, Customer.class)
//				.setParameter("name", "?¬? ")
//				.setParameter("addr", "%?Έ%")
//				.getResultList(); //?¬?©?? ? μΏΌλ¦¬ ?¬?© : createQuery
//		System.out.println(list);
		
		String sql="select c from Customer c where c.userName=?1 or c.addr like ?2";
		List<Customer> list=
				em.createQuery(sql, Customer.class)
				.setParameter(1, "?¬? ")
				.setParameter(2, "%? μ£?%")
				.getResultList(); //?¬?©?? ? μΏΌλ¦¬ ?¬?© : createQuery
		System.out.println(list);
		
		tx.commit();
	}
}
