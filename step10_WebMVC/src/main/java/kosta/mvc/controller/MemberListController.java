package kosta.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kosta.mvc.dto.Member;
import kosta.mvc.dto.MemberListDTO;

@Controller
public class MemberListController {
	
	@RequestMapping("/list/memberMulti.do")
	public String aa(MemberListDTO memberList) {
		//�信�� ${memberListDTO.list}, DTO���� ����� �̸��� list�� ��� �Բ� ����Ѵ�.
		
		System.out.println("-------------------------------");
		for(Member m:memberList.getList()) {
			System.out.println(m);
		}
		System.out.println("-------------------------------");
		
		return "listResult"; //WEB-INF/views/listResult.jsp 
	}
}
