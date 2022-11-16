package kosta.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kosta.mvc.service.TestShareService;

@Controller //생성
public class AdminController {

	@Autowired //주입
	private TestShareService shareService;
	
	@RequestMapping("/test.admin")
	public ModelAndView test() {
		System.out.println("AdminController의 test()");
		
		//WEB-INF/admin/adminResult.jsp이동
		return new ModelAndView("adminResult", "message", "/test.admin-AdminController.test()-관리자 페이지 시작점");
	}
	
	@RequestMapping("/share.admin")
	public String share() {
		System.out.println("AdminController의 share.admin요청됨");
		System.out.println("shareService = " + shareService);
		
		return "adminResult"; // WEB-INF/admin/adminResult.jsp이동
	}
	
	@RequestMapping("/share.do")
	public String shareDo() {
		System.out.println("TestShareCotroller의 share.do");
		System.out.println("shareService="+shareService);
		
		return "result"; //WEB-INF/views/result.jsp이동
	}
}
