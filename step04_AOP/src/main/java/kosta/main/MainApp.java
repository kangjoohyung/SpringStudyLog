package kosta.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kosta.exam.MemberService;

public class MainApp {

	public static void main(String[] args) {
		ApplicationContext context=new ClassPathXmlApplicationContext("kosta/main/applicationContext.xml");//package말고 경로로 지정할땐 / 사용

		System.out.println("----------------------------");
		MemberService service=context.getBean("target", MemberService.class);
		int result=service.insert();
		System.out.println("결과 : "+result);
		
		System.out.println("-----------------------------");
		String s=service.select(3);
		System.out.println("결과 : "+s);
		
		System.out.println("-----------------------------");
		service.update("jang", "희정");
	}

}
