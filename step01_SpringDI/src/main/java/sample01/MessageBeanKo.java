package sample01;

public class MessageBeanKo implements MessageBean {

	public MessageBeanKo() {
		System.out.println("beanKo생성자");
	}

	@Override
	public void sayHello(String name) {
		System.out.println("안녕! | "+name);

	}

}
