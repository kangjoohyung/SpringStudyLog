package sample04tea;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {

	public static void main(String[] args) {
		//기존방식
		/*StudentController controller = new StudentController(); //<bean
		
		StudentDAO studentDao = new StudentDAOImpl();//<bean
		
		StudentService service = new StudentServiceImpl();//<bean
		StudentServiceImpl s2 = (StudentServiceImpl)service;
		s2.setStudentDAO(studentDao);
		
		controller.setService(service);
		
		Student student = new Student();//<bean
		student.setName("희정");
		student.setNo(10);
		student.setPhone("123-7894");
		student.setAddr("제주도");
		
		controller.setStudent(student);
		
		controller.insert();*/
		///////////////////////////////////////////////////////////////
		
		ApplicationContext context = new ClassPathXmlApplicationContext("sample04/springDISetter.xml");
        System.out.println("=====================");
        StudentController controller= context.getBean("controller", StudentController.class);
        controller.insert();
	}

}
















