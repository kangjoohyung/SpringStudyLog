package sample06;

public class Customer {
	private String id;
	private int age;
	private String addr;

	public Customer() {
		super();
		System.out.println("Customer 생성자");
	}

	public Customer(String id) {
		super();
		this.id = id;
		System.out.println("Customer(String id)"+id);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
		System.out.println("setId"+id);
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
		System.out.println("setAddr"+addr);
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", age=" + age + ", addr=" + addr + "]";
	}

	
}
