package sample03tea;

import java.awt.print.Book;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {

	public static void main(String[] args) {
		//1.기존방식
		 /* BookDAO bookDao = new BookDAOImpl(); //<bean BookVo bookVo = new BookVo
		  BookVo bookVo = new BookVo("jsp","희정",2500, "2022-4-25");
		  
		  BookController controller = new BookController(bookDao, bookVo);
		  
		  controller.bookInsert();*/
		//2. spring DI적용
		ApplicationContext context =
			  new ClassPathXmlApplicationContext("sample03/springDIConstructor.xml");
		
		System.out.println("---------------------");
		BookController controller= context.getBean("controller", BookController.class);
		controller.bookInsert();
		 
		
		
	}

}









