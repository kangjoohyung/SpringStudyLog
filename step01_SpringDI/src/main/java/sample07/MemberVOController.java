package sample07;

public class MemberVOController {
	private MemberVO memberVO;
	private MemberVO memberVO2;
	public MemberVOController() {
		super();
		System.out.println("MemberVOController()");
	}
	public MemberVOController(MemberVO memberVO, MemberVO memberVO2) {
		super();
		this.memberVO = memberVO;
		this.memberVO2 = memberVO2;
		System.out.println("MemberVOController(MemberVO memberVO, MemberVO memberVO2)");
		System.out.println("memberVO"+memberVO+",name="+memberVO.getName());
		System.out.println("memberVO2="+memberVO2+",name="+memberVO2.getName());
	}
	public void setMemberVO(MemberVO memberVO) {
		this.memberVO = memberVO;
		System.out.println("setMemberVO(MemberVO memberVO), name="+memberVO.getName());
	}
	
	public void setMemberVO2(MemberVO memberVO2) {
		this.memberVO2 = memberVO2;
		System.out.println("setMemberVO2(MemberVO2 memberVO2), name="+memberVO2.getName());
	}
	
	
	
}
