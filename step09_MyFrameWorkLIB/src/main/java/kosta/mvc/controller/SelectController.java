package kosta.mvc.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class SelectController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("SelectController");
		
		//뷰쪽에 전달될 정보
		//request.setAttribute("message", "select결과입니다"); ->굳이 필요 없어져서 안씀(써도 되긴하는듯)
		
		ModelAndView mv=new ModelAndView();
		mv.addObject("message","select결과입니다"); //뷰에서 ${message}
		mv.setViewName("selectResult");//forward방식으로 이동
		
		return mv;
	}

}
