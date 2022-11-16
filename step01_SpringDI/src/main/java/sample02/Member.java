package sample02;

/*
 * DI=Dependency Injection
 * 1) constructor
 * 
 * 
 */

public class Member {
	private String id;
	private String pwd;
	private int age;
	private String addr;
	
	public Member() {
		System.out.println("Member 생성자");
	}
	
	public Member(String id) {
		System.out.println("Member(String id)/ id="+id);
		this.id=id;
	}
	public Member(String id, String pwd) {
		System.out.println("Member(String id, String pwd)/ id="+id+",pwd="+pwd);
		this.id=id;
		this.pwd=pwd;
	}
	public Member(String id, int age) {
		System.out.println("Member(String id, int age)/ id="+id+",age="+age);
		this.id=id;
		this.age=age;
	}
	public Member(String id, int age, String addr) {
		System.out.println("Member(String id, int age, String addr)/ id="+id+",age="+age+",addr="+addr);
		this.id=id;
		this.age=age;
		this.addr=addr;
	}

	public Member(String id, String pwd, int age, String addr) {
		System.out.println("Member(String id, String pwd, int age, String addr) \n id="+id+",pwd="+pwd+",age="+age+",addr="+addr);
		this.id = id;
		this.pwd = pwd;
		this.age = age;
		this.addr = addr;
	}
	
	@Override
	public String toString() {
		return id+"|"+pwd+"|"+age+"|"+addr;
	}
}
