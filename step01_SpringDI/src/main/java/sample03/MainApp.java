package sample03;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {

	public MainApp() {
		super();
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		ApplicationContext context=new ClassPathXmlApplicationContext("sample03/conss.xml");
		BookController controller=context.getBean("control", BookController.class);
	}

}
