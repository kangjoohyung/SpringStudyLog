package sample05tea;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;



public class MainApp {
	public static void main(String[] args) {
		/*OrderMessage om =  new OrderMessageImpl();
		OrderMessageImpl omImpl = (OrderMessageImpl)om;
		
		omImpl.setOrderId(100); //<property 
		omImpl.setMessage("되니?");
		omImpl.setProudctBean(new ProductBean());
		omImpl.setUserBean(new UserBean());
		
		
		 om.getOrderMessage();*/
		 
		 
		
		ApplicationContext context =
				new ClassPathXmlApplicationContext("sample05/applicationContext.xml");
		   
		OrderMessage order = context.getBean("order", OrderMessage.class);
	     System.out.println("---------------------------------");
		   order.getOrderMessage();	

		
	}
	
	
	

}
