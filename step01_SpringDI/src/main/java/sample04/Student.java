package sample04;

public class Student {
	String name;
	int no;
	String phone;
	String addr;
	
	public Student() {
		super();
		System.out.println("student()생성자");
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
		System.out.println("Student의 setName() name="+name);
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
		System.out.println("Student의 setName() name="+name);
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
		System.out.println("Student의 setName() name="+name);
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
		System.out.println("Student의 setAddr() addr="+addr);
	}
	@Override
	public String toString() {
		return "Student [name=" + name + ", no=" + no + ", phone=" + phone + ", addr=" + addr + "]";
	}
	
	
}
