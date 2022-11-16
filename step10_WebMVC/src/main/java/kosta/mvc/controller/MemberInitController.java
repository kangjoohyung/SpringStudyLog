package kosta.mvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kosta.mvc.dto.Member;

@Controller
public class MemberInitController {
	
	@Autowired //자동 type주입 -> 타입안되면 byName주입
	private List<Member> memberList; //null
	
	public MemberInitController() {
		super();
		// TODO Auto-generated constructor stub
	}


	@RequestMapping("/memInit.do")
	public void memInit(Model model) {
		System.out.println("MemberInitController의 memInit()");
		System.out.println("memberList="+memberList);
		model.addAttribute("memberList", memberList); //뷰에서 사용가능
	}
}
