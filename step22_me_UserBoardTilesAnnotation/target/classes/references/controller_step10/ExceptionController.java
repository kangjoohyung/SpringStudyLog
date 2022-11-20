package kosta.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExceptionController {
	
	@RequestMapping("/exception.do")
	public String aa(String no) {
		int convertNo=Integer.parseInt(no);
		System.out.println("convertNo="+convertNo);
		return "result";//WEB-INF/views/result.jsp이동
	}
	
	/**
	 * 현재 컨트롤러에서 발생하는 예외처리
	 * 작동되는 정상 경로에서 예외 발생시 가로채서 예외처리
	 */
	@ExceptionHandler(value= {NumberFormatException.class, ArithmeticException.class}) //Exception을 처리해주는 메소드, value에 해당하는 예외
	public String ex(Exception e, Model model) {
		System.out.println("@ExceptionHandler(value= {NumberFormatException.class})");
		
		model.addAttribute("errClass",e.getClass());//뷰에서 ${errClass}
		model.addAttribute("errMsg", e.getMessage());//뷰에서 ${errMsg}
		
		return "error/errorView"; //WEB-INF/views/error.jsp 이동
	}
}
