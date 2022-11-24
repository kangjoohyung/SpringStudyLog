package kosta.exam.main;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import kosta.exam.domain.Customer;

public class MainApp {
	public static void main(String[] args) {
		System.out.println("JPA 시작");
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("JPAProject");
		EntityManager em=emf.createEntityManager();
		
		EntityTransaction tx=em.getTransaction();
		tx.begin(); //begin부터 트랜젝션 시작, commit();으로 커밋한다(자동 안됨->설정은 있나)
		//1.등록
		//builder().~~~.build() ->lombok 기능
//		em.persist(Customer.builder().addr("서울5").age(20).birthDay(new Date()).userName("희정5").build());
//		em.persist(Customer.builder().addr("서울6").age(20).birthDay(new Date()).userName("희정6").build());
//		em.persist(Customer.builder().addr("서울7").age(20).birthDay(new Date()).userName("희정7").build());
//		em.persist(Customer.builder().addr("서울8").age(20).birthDay(new Date()).userName("희정8").build());
		
		//2.검색(id-pk대상-에 해당하는 정보 검색) ->생성자 있어야함, NoArg, AllArg 셋트로 놓기
//		Customer cu=em.find(Customer.class, 3L); // ->id가 3인 Long타입
//		System.out.println(cu);
		
		//3. 수정하기 ->따로 update는 없고 찾은것을 가지고 set으로 롬복으로 사용후 커밋하여 업데이트기능 사용
//		CU.SETADDR("별나라");
//		CU.SETAGE(24);
//		cu.setUserName("예압");
		
		//4. 삭제하기 -> 검색을 기준으로 remove하면 됨
//		em.remove(cu);
		
		////////////////////////////////////////////////
		//JPQL문법 : 객체중심으로 쿼리를 작성하는 것->대소문자를 가리게 됨
		//5. 이름이 희정2인 레코드를 검색
//		String sql="select c from Customer c where c.userName='희정'";//Entity 이름으로 사용, allias 필수, 대소문자 구분, 컬럼,테이블 등에 자바 이름으로 넣기 
//		List<Customer> list=em.createQuery(sql, Customer.class).getResultList(); //사용자정의 쿼리 사용 : createQuery
//		System.out.println(list);
		
		//6.이름과 주소를 전달받아 검색하기 2가지 - 주소는 like연산자 -> :변수 (컬럼명 해당 변수) / ?1 (포지션)
//		String sql="select c from Customer c where c.userName=:name or c.addr like :addr";
//		List<Customer> list=
//				em.createQuery(sql, Customer.class)
//				.setParameter("name", "희정")
//				.setParameter("addr", "%울%")
//				.getResultList(); //사용자정의 쿼리 사용 : createQuery
//		System.out.println(list);
		
		String sql="select c from Customer c where c.userName=?1 or c.addr like ?2";
		List<Customer> list=
				em.createQuery(sql, Customer.class)
				.setParameter(1, "희정")
				.setParameter(2, "%제주%")
				.getResultList(); //사용자정의 쿼리 사용 : createQuery
		System.out.println(list);
		
		tx.commit();
	}
}
