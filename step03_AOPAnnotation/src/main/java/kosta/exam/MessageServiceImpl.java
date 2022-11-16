package kosta.exam;

import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;

@Service("target") //생성
public class MessageServiceImpl implements MessageService {

	@Override
	public void korHello() {
		System.out.println("MessageServiceImpl의 korHello()메소드 핵심기능");
		
		System.out.println("------------------------------------------");
		//this.engHello(); //aop proxy server가 호출하는 것이 아니기 때문에에 advice 적용이 안된다.
		
		//그래서, advice가 적용되게 하려면 engHello()를 AopContext가 직접 호출할 수 있도록 해야한다.
		MessageService se=(MessageService)AopContext.currentProxy();
		se.engHello();
		System.out.println("------------------------------------------");
		
		try {
			Thread.sleep(1500);//1.5초(m.sec)
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void engHello() {
		System.out.println("MessageServiceImpl의 engHello()메소드 핵심기능");
		
		try {
			Thread.sleep(1000);//1.0초(m.sec)
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public String hello() {
		System.out.println("MessageServiceImpl의 hello()메소드 핵심기능");
		
		try {
			Thread.sleep(2000);//2.0초(m.sec)
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		return "String리턴";
	}

	@Override
	public int hello(String name) {
		System.out.println("MessageServiceImpl의 hello(String name)메소드 핵심기능");
		
		try {
			Thread.sleep(2500);//2.5초(m.sec)
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		return 50;
	}

}
