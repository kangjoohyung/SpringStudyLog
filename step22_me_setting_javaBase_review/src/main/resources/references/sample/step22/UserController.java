package references.sample.step22;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kosta.web.mvc.user.dto.UserDTO;
import kosta.web.mvc.user.service.UserService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
	
	private final UserService userService;

	/**
	 * �α��� ��
	 * */
	@RequestMapping("/login")
	public void login() {}
	
	/**
	 * �α����ϱ�
	 * */
	@RequestMapping("/loginCheck")
	public String loginCheck(UserDTO userDTO ,HttpSession session) {
		//����ȣ�� �ϰ� �����ϸ� ������ UserDTO�� �޾Ƽ�  
		UserDTO dbDTO = userService.loginCheck(userDTO);
		
		//HttpSession�� ������ �����Ѵ�. - �信�� ����ϰ� ���� ${loginUser} / ${loginName}
		session.setAttribute("loginUser", dbDTO.getUserId());
		session.setAttribute("loginName", dbDTO.getName());
		
		return "redirect:/";
	}
	
	/**
	 * �α׾ƿ�
	 * */
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		//��� ������ ������ �����Ѵ�.
		session.invalidate();
		
		return "redirect:/";
	}
	
}










