package sample02;

public class MemberDAO {
	public MemberDAO() {
		System.out.println("MemberDAO생성자");
	}
	
	public void insert(Member member) {
		System.out.println("MemberDAO의 insert호출됨");
		System.out.println("member:"+member);
	}
}
