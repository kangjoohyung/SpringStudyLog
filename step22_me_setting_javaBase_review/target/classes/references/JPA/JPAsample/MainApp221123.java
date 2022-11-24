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
		System.out.println("JPA ?��?��");
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("JPAProject");
		EntityManager em=emf.createEntityManager();
		
		EntityTransaction tx=em.getTransaction();
		tx.begin(); //begin�??�� ?��?��?��?�� ?��?��, commit();?���? 커밋?��?��(?��?�� ?��?��->?��?��?? ?��?��)
		//1.?���?
		//builder().~~~.build() ->lombok 기능
//		em.persist(Customer.builder().addr("?��?��5").age(20).birthDay(new Date()).userName("?��?��5").build());
//		em.persist(Customer.builder().addr("?��?��6").age(20).birthDay(new Date()).userName("?��?��6").build());
//		em.persist(Customer.builder().addr("?��?��7").age(20).birthDay(new Date()).userName("?��?��7").build());
//		em.persist(Customer.builder().addr("?��?��8").age(20).birthDay(new Date()).userName("?��?��8").build());
		
		//2.�??��(id-pk???��-?�� ?��?��?��?�� ?���? �??��) ->?��?��?�� ?��?��?��?��, NoArg, AllArg ?��?���? ?���?
//		Customer cu=em.find(Customer.class, 3L); // ->id�? 3?�� Long???��
//		System.out.println(cu);
		
		//3. ?��?��?���? ->?���? update?�� ?���? 찾�?것을 �?�?�? set?���? 롬복?���? ?��?��?�� 커밋?��?�� ?��?��?��?��기능 ?��?��
//		CU.SETADDR("별나?��");
//		CU.SETAGE(24);
//		cu.setUserName("?��?��");
		
		//4. ?��?��?���? -> �??��?�� 기�??���? remove?���? ?��
//		em.remove(cu);
		
		////////////////////////////////////////////////
		//JPQL문법 : 객체중심?���? 쿼리�? ?��?��?��?�� �?->???��문자�? �?리게 ?��
		//5. ?��름이 ?��?��2?�� ?��코드�? �??��
//		String sql="select c from Customer c where c.userName='?��?��'";//Entity ?��름으�? ?��?��, allias ?��?��, ???��문자 구분, 컬럼,?��?���? ?��?�� ?���? ?��름으�? ?���? 
//		List<Customer> list=em.createQuery(sql, Customer.class).getResultList(); //?��?��?��?��?�� 쿼리 ?��?�� : createQuery
//		System.out.println(list);
		
		//6.?��름과 주소�? ?��?��받아 �??��?���? 2�?�? - 주소?�� like?��?��?�� -> :�??�� (컬럼�? ?��?�� �??��) / ?1 (?���??��)
//		String sql="select c from Customer c where c.userName=:name or c.addr like :addr";
//		List<Customer> list=
//				em.createQuery(sql, Customer.class)
//				.setParameter("name", "?��?��")
//				.setParameter("addr", "%?��%")
//				.getResultList(); //?��?��?��?��?�� 쿼리 ?��?�� : createQuery
//		System.out.println(list);
		
		String sql="select c from Customer c where c.userName=?1 or c.addr like ?2";
		List<Customer> list=
				em.createQuery(sql, Customer.class)
				.setParameter(1, "?��?��")
				.setParameter(2, "%?���?%")
				.getResultList(); //?��?��?��?��?�� 쿼리 ?��?�� : createQuery
		System.out.println(list);
		
		tx.commit();
	}
}
