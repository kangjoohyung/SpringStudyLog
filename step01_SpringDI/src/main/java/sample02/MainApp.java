package sample02;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/*
 * DI 예제
 */
public class MainApp {
	public static void main(String[] args) {
		/*
		//1. 기존방식
		Member member=new Member("jang", "1234", 20, "서울");
		MemberDAO dao=new MemberDAO();
		MemberService service=new MemberService(dao, member);
		service.serviceInsert();
		//확인후 주석처리
		*/
		
		ApplicationContext context=new ClassPathXmlApplicationContext("sample02/springDIConstructor.xml");
		//기본생성자 호출됨(configure->)
		
		System.out.println("----------------------------------------------");
		MemberService service=context.getBean("service", MemberService.class);
		service.serviceInsert();
		
		
	}
}
