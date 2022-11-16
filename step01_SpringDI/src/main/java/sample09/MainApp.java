package sample09;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {

	public static void main(String[] args) {
		ApplicationContext context=new ClassPathXmlApplicationContext("sample09/springAnnotation.xml");
		
		System.out.println("------------------");
		EmpController con=context.getBean("controller", EmpController.class);//안됨-설정 또 해야됨
		con.test();
		
		System.out.println("----------------");
		ResourceBean rb=context.getBean("resource", ResourceBean.class);
		rb.test();
	}
}
