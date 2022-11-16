package sample02;

public class MemberService {
	private MemberDAO memberDAO;
	private Member member;
	
	public MemberService() {
		System.out.println("MemberService()생성자");
	}
	

	//생성자를 이용한 주입 ->null exception이 없음(?)
	public MemberService(MemberDAO memberDAO, Member member) {
		System.out.println("MemberService(MemberDAO memberDAO, Member member)");
		System.out.println("memberDAO="+memberDAO);
		System.out.println("member="+member);
		
		this.memberDAO = memberDAO;
		this.member = member;
	}

	public void serviceInsert() {
		System.out.println("MemberService의 serviceInsert()");
		memberDAO.insert(member);
	}
}
