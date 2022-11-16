package kosta.test.audio;

import org.springframework.stereotype.Service;

import kosta.test.service.Player;

@Service("audio")
public class AudioImpl implements Player {

	public void start(int i) {
		
		System.out.println(super.getClass().getName()+"  의 start(int i) 핵심기능");

	}

	public String pause() {
		System.out.println(super.getClass().getName()+"의 pause() 핵심기능");
		return "안녕";
	}

	public void stop() {
		System.out.println(super.getClass().getName()+"의 stop() 핵심기능");

	}

}
