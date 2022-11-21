package kosta.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExceptionController {
	
	@RequestMapping("/exception.do")
	public String aa(String no) {
		int convertNo=Integer.parseInt(no);
		System.out.println("convertNo="+convertNo);
		return "result";//WEB-INF/views/result.jsp�̵�
	}
	
	/**
	 * ���� ��Ʈ�ѷ����� �߻��ϴ� ����ó��
	 * �۵��Ǵ� ���� ��ο��� ���� �߻��� ����ä�� ����ó��
	 */
	@ExceptionHandler(value= {NumberFormatException.class, ArithmeticException.class}) //Exception�� ó�����ִ� �޼ҵ�, value�� �ش��ϴ� ����
	public String ex(Exception e, Model model) {
		System.out.println("@ExceptionHandler(value= {NumberFormatException.class})");
		
		model.addAttribute("errClass",e.getClass());//�信�� ${errClass}
		model.addAttribute("errMsg", e.getMessage());//�信�� ${errMsg}
		
		return "error/errorView"; //WEB-INF/views/error.jsp �̵�
	}
}
