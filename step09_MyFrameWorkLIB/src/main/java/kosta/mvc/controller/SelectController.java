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
		
		//���ʿ� ���޵� ����
		//request.setAttribute("message", "select����Դϴ�"); ->���� �ʿ� �������� �Ⱦ�(�ᵵ �Ǳ��ϴµ�)
		
		ModelAndView mv=new ModelAndView();
		mv.addObject("message","select����Դϴ�"); //�信�� ${message}
		mv.setViewName("selectResult");//forward������� �̵�
		
		return mv;
	}

}
