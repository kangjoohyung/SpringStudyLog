package kosta.mvc.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller //����� ��� ��Ʈ�ѷ�
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/")
	public String home(Locale locale, Model model) {
		
		return "index"; //WEB-INF/view/index.jsp
	}
	
	/*
	@RequestMapping("AjaxStart")
	public ModelAndView ajaxStart() {
		
		return new ModelAndView("AjaxStart");
	}
	
	@RequestMapping("suggestClient")
	public ModelAndView suggestClient() {
		
		return new ModelAndView("suggestClient");
	}
	
	@RequestMapping("student")
	public ModelAndView student() {
		
		return new ModelAndView("student");
	}
	*/
	
	/* ���� �޼ҵ带 ����ȭ */
	
	/**
	 * url���� (/{url}����)
	 */
	@RequestMapping("/{url}")
	public void url() {
		
	}
}
