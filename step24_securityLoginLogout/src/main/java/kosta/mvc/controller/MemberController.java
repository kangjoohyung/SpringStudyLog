package kosta.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberController {

	@RequestMapping("/myPage")
	public void myPage() {
	}
	
	@RequestMapping("/main")
	public void main() {}
	
}
