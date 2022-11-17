package kosta.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TilesController {
	
	@RequestMapping("/")
	public String index() {
		return "index";//WEB-INF/views/index.jsp¿Ãµø
	}
	
	@RequestMapping("/test1")
	public String test1() {
		return "board/list";
	}
	
	@RequestMapping("/test2")
	public String test2() {
		return "board/read";
	}
	
	@RequestMapping("/test3")
	public String test3() {
		return "user/join";
	}
	
	@RequestMapping("/test4")
	public String test4() {
		return "user/login";
	}
	
	@RequestMapping("/test5")
	public String test5() {
		return "test";
	}
	
	@RequestMapping("test6")
	public String test6() {
		return "board/free/write";
	}
	
	
	@RequestMapping("test7")
	public String test7() {
		return "board/qa/read";
	}
}
