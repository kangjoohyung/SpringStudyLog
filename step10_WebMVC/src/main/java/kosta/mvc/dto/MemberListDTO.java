package kosta.mvc.dto;

import java.util.List;

public class MemberListDTO {
	
	private List<Member> list;//�信�� name�� list[index].�Ӽ� -> �̰����� ����� ���� �̸��� �����ؾ��Ѵ�.
	
	public MemberListDTO() {
		System.out.println("MemberListDTO()������");
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
