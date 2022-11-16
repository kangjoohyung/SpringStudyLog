package kosta.mvc.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SelectController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("SelectController");
		
		//뷰쪽에 전달될 정보
		request.setAttribute("message", "select결과입니다");
		
		ModelAndView mv=new ModelAndView();
		mv.setViewName("selectResult.jsp");//forward방식으로 이동
		
		return mv;
	}

}
