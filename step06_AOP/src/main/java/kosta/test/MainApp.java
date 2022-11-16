package kosta.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kosta.test.service.Player;

public class MainApp {

	public static void main(String[] args) {
		ApplicationContext context =
				new ClassPathXmlApplicationContext("kosta/test/applicationContext.xml");
		
		Player p = context.getBean("tv",Player.class);
		p.start(10);
		p.pause();
		p.stop();
		
		System.out.println("-----------------------------");
		
		p = context.getBean("audio",Player.class);
		p.start(10);
		p.pause();
		p.stop();
		
		System.out.println("-----------------------------");
		p = context.getBean("vedio",Player.class);
		p.start(10);
		p.pause();
		p.stop();

	}

}
