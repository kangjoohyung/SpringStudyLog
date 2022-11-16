package kosta.exam;

import org.springframework.stereotype.Service;

@Service("target2")
public class UserService {
	public String testHello() {
		System.out.println("UserService의 testHell()의 핵심기능");
		
		try {
			Thread.sleep(1000);
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "String리턴";
	}
	
	public void insertHello(int i, int j) {
		System.out.println("UserService의 insertHello(int i, int j)의 핵심기능");
		
		try {
			Thread.sleep(1500);
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
