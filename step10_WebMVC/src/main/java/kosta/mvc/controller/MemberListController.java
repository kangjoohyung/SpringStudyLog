package kosta.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kosta.mvc.dto.Member;
import kosta.mvc.dto.MemberListDTO;

@Controller
public class MemberListController {
	
	@RequestMapping("/list/memberMulti.do")
	public String aa(MemberListDTO memberList) {
		//뷰에서 ${memberListDTO.list}, DTO에서 선언된 이름인 list로 뷰와 함께 사용한다.
		
		System.out.println("-------------------------------");
		for(Member m:memberList.getList()) {
			System.out.println(m);
		}
		System.out.println("-------------------------------");
		
		return "listResult"; //WEB-INF/views/listResult.jsp 
	}
}
