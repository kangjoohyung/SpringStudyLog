package kosta.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

	@RequestMapping("/board")
	public void board() {
	}
	
	@RequestMapping("/main")
	public void main() {}
	
	@RequestMapping("/login")
	public void login() {
	}
	
	@RequestMapping("/loginForm")
	public void loginForm() {
	}
}
