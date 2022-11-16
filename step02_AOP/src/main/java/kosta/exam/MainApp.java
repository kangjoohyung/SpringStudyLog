package kosta.exam;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {

	public static void main(String[] args) {
		ApplicationContext context=new ClassPathXmlApplicationContext("kosta/exam/applicationContext.xml");
		//실행시 아무런 에러가 없이 실행되어야함(현재까진 결과나올것도 없음)
		
		/*
		 * Aop Proxy Server는 생성될때
		 * 	1) J2SE방식 - 기본
		 * 			: 자바의 기본 문법을 따른다
		 * 			 즉, interface가 있다면 advice를 적용할 대상(pointCut)은 
		 * 			*반드시 interface를 통해서 호출해야 한다. 
		 * 
		 * 	2) CGLIB방식
		 * 			: 기본방식을 CGLIB방식으로 변경하면 interface가 있더라도
		 * 			 interface기반 또는 구현 객체를 통해서 호출해도 *상관없다.
		 * 
		 */
		MessageService service=context.getBean("target", MessageService.class);
		//serviceImpl로 선언하면 에러발생함(인터페이스로 선언할것)
		service.korHello();
		
		System.out.println("------------------------------------------");
		service.engHello();
		
		System.out.println("------------------------------------------");
		String re=service.hello();
		System.out.println("리턴결과 : "+re);
		
		System.out.println("------------------------------------------");
		int i=service.hello("희정");
		System.out.println("리턴결과 : "+i);
		//아직까지는 AOP적용 전 상태
		
		System.out.println("-------------UserService호출----------------");
		UserService user=context.getBean("target2", UserService.class);
		re=user.testHello();
		System.out.println("결과 : "+re);
		System.out.println("-------------------------------------------");
		user.insertHello(4, 3);
		
	}
}
