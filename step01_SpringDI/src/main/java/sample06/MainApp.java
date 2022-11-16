package sample06;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {

	public static void main(String[] args) {
		ApplicationContext context=new ClassPathXmlApplicationContext("sample06/springDIList02.xml");
		System.out.println("------------------------");
		
		ListBean bean=context.getBean("listBean", ListBean.class);
		
		for(int i:bean.getIntList()) {
			System.out.println(i);
		}
		
		System.out.println("-------------------------");
		
		for(String s : bean.getStrList()) {
			System.out.println(s);
		}
		
		System.out.println("----------------------------");
		
		for(Customer c : bean.getCustomerList()) {
			System.out.println(c);
		}
	}

}
