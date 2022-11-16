package sample01;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class MainApp {

	public static void main(String[] args) {
		/*
		// 1. 기존방식
		MessageBean bean=new MessageBeanKo();
		bean.sayHello("희정");
		
		bean=new MessageBeanEn();
		bean.sayHello("Heejung");
		*/

		//2.SpringContainer가 bean을 관리할 수 있도록 bean설정문서를 만들고 로딩한다.
		
		/*
		 * SpringContainer의 종류
		 * 	1) BeanFactory : 객체 생성 + 소멸 등의 기본 라이플 사이클 관리
		 * 	2) ApplicationContext : BeanFactory의 확장개념으로 기본기능+다국어지원, 메시지처리
		 * 	3) WebApplicationContext : Spring Web MVC에서 사용
		 * 
		 * 	-특징
		 * 	: Container가 만들어질 때 bean설정 문서를 읽어서 bean설정에 
		 * 		있는 객체를 사전 초기화(미리 생성)한다. / scope="singleton"이다.
		 * 		
		 */
		
		//ApplicationContext는 interface라 생성함
		//경로는 클래스 기준이기때문에 string인수 경로를 입력할 자리에 src/~~를 적지 않는다(java말고 클래스를 인식)
		ApplicationContext context=
				new ClassPathXmlApplicationContext("sample01/applicationContext.xml");
		//실행하면 생성자가 호출된다.(applicationContext.xml에 생성한 생성자로 사전초기화가 이루어짐)
		
		
				//new FileSystemXmlApplicationContext("src/main/java/sample01/applicationContext.xml");
		//프로젝트가 기준이어서 전체 경로(src)를 적는다->이것보단 위의 classpath많이씀
		
		System.out.println("---------------------------------");
		
		//id를 입력하여 대상 지정하고, 대상의 타입을 지정한다(클래스타입은 .class)
		MessageBean bean = context.getBean("ko", MessageBean.class);
		bean.sayHello("희정");
		
		bean=context.getBean("en", MessageBean.class);//1
		bean.sayHello("Heejung");
		System.out.println("bean="+bean);
		
		MessageBean bean2=context.getBean("en", MessageBean.class);//2
		System.out.println("bean2="+bean2);//1,2의 주소값이 같음(설정에서 하나인걸 그대로 불러오니까)-prototype 이전
		//prototype설정후 1,2 주소값 달라짐
		
		//설정에서 en2를 만든후 비교해본다
		MessageBean bean3=context.getBean("en2", MessageBean.class);//3
		System.out.println("bean3="+bean3);//1=2 / 1,2 != 3 (객체주소가 다름)-prototype 이전
		//기본값이 싱글톤인것은 가장 많이 쓰여서임
		
		//bean configure에서 scope="prototype"설정하면, 생성자가 호출할때마다 그때그때 생성됨(미리 생성하지않음)
		//->이후부터 주소값 달라짐
		
		//context 가져오는 방식 FileSystem으로 변경하여 불러보기 (동일 효과, ClassPath를 주로 씀)
		
	}

}
