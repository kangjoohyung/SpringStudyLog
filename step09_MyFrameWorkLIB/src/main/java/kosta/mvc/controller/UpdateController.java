package kosta.mvc.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class UpdateController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("UpdateController");
		
		//request.setAttribute("message", "�����Ϸ� ����Դϴ�");
		
		ModelAndView mv=new ModelAndView();
		mv.addObject("message", "�����Ϸ� ����Դϴ�"); //${requestScope.message}
		mv.setViewName("updateResult");
		return mv;
	}

}
