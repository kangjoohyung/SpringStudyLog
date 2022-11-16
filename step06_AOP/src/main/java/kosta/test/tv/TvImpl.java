package kosta.test.tv;

import kosta.test.service.Player;

public class TvImpl implements Player {

	public void start(int i) {
		System.out.println(super.getClass().getName()+"의 start(int i) 핵심기능");

	}

	public String pause() {
		System.out.println(super.getClass().getName()+"의 pause() 핵심기능");
		return "월요일";
	}

	public void stop() {
		System.out.println(super.getClass().getName()+"의 stop 핵심기능");

	}

}
