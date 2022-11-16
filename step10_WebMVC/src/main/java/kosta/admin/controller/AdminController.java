package kosta.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kosta.mvc.service.TestShareService;

@Controller //����
public class AdminController {

	@Autowired //����
	private TestShareService shareService;
	
	@RequestMapping("/test.admin")
	public ModelAndView test() {
		System.out.println("AdminController�� test()");
		
		//WEB-INF/admin/adminResult.jsp�̵�
		return new ModelAndView("adminResult", "message", "/test.admin-AdminController.test()-������ ������ ������");
	}
	
	@RequestMapping("/share.admin")
	public String share() {
		System.out.println("AdminController�� share.admin��û��");
		System.out.println("shareService = " + shareService);
		
		return "adminResult"; // WEB-INF/admin/adminResult.jsp�̵�
	}
	
	@RequestMapping("/share.do")
	public String shareDo() {
		System.out.println("TestShareCotroller�� share.do");
		System.out.println("shareService="+shareService);
		
		return "result"; //WEB-INF/views/result.jsp�̵�
	}
}
