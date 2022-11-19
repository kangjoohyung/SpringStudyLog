package kosta.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@RequestMapping("/board")
	public void board() {
	}
	
	@RequestMapping("/main")
	public void main() {}
	
}
