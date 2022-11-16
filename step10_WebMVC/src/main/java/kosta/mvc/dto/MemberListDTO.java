package kosta.mvc.dto;

import java.util.List;

public class MemberListDTO {
	
	private List<Member> list;//뷰에서 name이 list[index].속성 -> 이곳에서 선언된 변수 이름과 동일해야한다.
	
	public MemberListDTO() {
		System.out.println("MemberListDTO()생성자");
	}

	public List<Member> getList() {
		System.out.println("getList()");
		return list;
	}

	public void setList(List<Member> list) {
		System.out.println("setList(List<Member> list)");
		this.list = list;
	}
	
	
}
