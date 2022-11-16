package sample07;

public class MemberVO {
	private String name;
	private int age;
	private String addr;
	
	public MemberVO() {
		super();
		System.out.println("MemberVO()");
	}
	
	public MemberVO(String name, int age, String addr) {
		super();
		this.name = name;
		this.age = age;
		this.addr = addr;
		System.out.println("MemberVO(String name, int age, String addr)");
	}



	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
		System.out.println("setName"+name);
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
		System.out.println("setAge"+age);
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
		System.out.println("addr"+addr);
	}
	
	
}
