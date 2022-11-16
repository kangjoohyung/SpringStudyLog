package kosta.mvc.dto;

public class Member { //lombok���� ����ȣ���ߴ��� �˱� ������� �Ϻη� �̹��� �Ⱦ�
	private String id;
	private String name;
	private int age;
	private String addr;
	
	private boolean state; //<input type="checkbox"
	
	public Member() {
		super();
		System.out.println("Member()");
	}
	public Member(String id, String name, int age, String addr) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.addr = addr;
		System.out.println("Member(String id, String name, int age, String addr)");
	}
	
	public Member(String id, String name, int age, String addr, boolean state) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.addr = addr;
		this.state = state;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		System.out.println("Member setId(String id) id="+id);
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		System.out.println("Member setName(String name) name="+name);
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		System.out.println("Member setAge(int age) age="+age);
		this.age = age;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		System.out.println("Member setAddr(String addr) addr="+addr);
		this.addr = addr;
	}
	
	public boolean isState() {
		System.out.println("Member�� isState()");
		return state;
	}
	public void setState(boolean state) {
		System.out.println("Member�� setState(boolean state)");
		this.state = state;
	}
	@Override
	public String toString() {
		return "Member [id=" + id + ", name=" + name + ", age=" + age + ", addr=" + addr + ", state=" + state
				+ ", getId()=" + getId() + ", getName()=" + getName() + ", getAge()=" + getAge() + ", getAddr()="
				+ getAddr() + ", isState()=" + isState() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	
	
}
