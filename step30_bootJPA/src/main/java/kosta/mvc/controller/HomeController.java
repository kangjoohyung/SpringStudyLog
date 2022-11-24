package kosta.mvc.controller;

import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String index() {
		System.out.println("HomeController - index()");
		return "index";
	}
	
	@RequestMapping("/test")
	public ModelAndView test() {
		System.out.println("HomeController - test()");
		return new ModelAndView("result", "hobbys", Arrays.asList("등산", "수영", "낚시", "골프"));
	}
	
	@RequestMapping("{url}")
	public void url() {
		
	}
}
