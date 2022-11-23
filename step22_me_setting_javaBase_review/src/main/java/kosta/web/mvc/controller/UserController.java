package kosta.web.mvc.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kosta.web.mvc.model.dto.UserDTO;
import kosta.web.mvc.model.user.UserService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

	private final UserService userService;
	
	/**
	 * 로그인 폼
	 */
	@RequestMapping("/login")
	public void login() {}
	
	/**
	 * 로그인하기
	 * @throws MyException 
	 */
	@RequestMapping("/loginCheck")
	public String loginCheck(UserDTO userDTO, HttpSession session) throws MyException {
//		UserDTO dbDTO=userService.loginCheck(userDTO);
//		session.setAttribute("loginUser", dbDTO);
	
		userDTO=userService.loginCheck(userDTO);
		return "redirect:/";
	}
	
	/**
	 * 로그아웃
	 * */
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		//모든 세션의 정보를 삭제한다.
		session.invalidate();
		
		return "redirect:/";
	}
	
	@ExceptionHandler(MyException.class)
	public ModelAndView error(Exception e) {
		
		return new ModelAndView("error/errorMessage", "errorMsg", e.getMessage());
	}
}
