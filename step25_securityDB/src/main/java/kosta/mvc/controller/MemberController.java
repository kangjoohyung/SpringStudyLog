package kosta.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kosta.mvc.model.service.MemberService;
import kosta.mvc.model.vo.Member;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;
	
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	/**
	 * 가입폼
	 */
	@RequestMapping("/member/joinForm")
	public void joinForm() {
		
	}
	
	/**
	 * 가입하기
	 */
	@RequestMapping("/member/join")
	public String join(Member member) {
		
		memberService.joinMember(member);
		
		return "member/joinSuccess";
	}
	
	@RequestMapping("/member/main")
	public void main(){}
	
	@RequestMapping("/admin/main")
	public void adminMain(){}
	
	/**
		로그인 폼
	*/
	@RequestMapping("/member/loginForm")
	public void loginForm(){}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView error(Exception e) {
		return new ModelAndView("error/errorMessage", "errorMsg", e.getMessage());
	}
}
