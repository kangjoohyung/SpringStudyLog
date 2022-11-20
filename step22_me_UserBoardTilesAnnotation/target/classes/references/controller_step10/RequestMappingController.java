package kosta.mvc.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller //���� id="requestMappingController"
@RequestMapping("/rem") //������ ���������� RequestMapping ���
public class RequestMappingController {
	
	/**
	 * 
	 * @return : String�� ���� ���ϰ��� ���� �̸��� �ȴ�.
	 */
	@RequestMapping("/a.do") //��û�� a.do�� �Ǹ� �޼ҵ带 ����->�޼ҵ��̸��� �߿����� ����
	public String aa() {
		System.out.println("RequestMappingController�� a.do��û�Ǿ����ϴ�");
		return "result";
	}
	
	/**
	 * return : void�� ���� ��û�� �ּҰ� ���� �̸��� �ȴ�.
	 * ex: rem/b.do��� ��û�̵Ǹ�
	 * 		prefix + rem/b + suffix
	 * 		����� /WEB-INF/views/rem/b.jsp �̵�
	 */
	@RequestMapping(value= {"/b.do","/c.do"}) // ������ ��û�� �Ѳ����� ó���Ҷ�
	public void bb() {
		System.out.println("rem/b.do or rem/c.do ��û�Ǿ����ϴ�");
	}
	
	/*
	@RequestMapping("/test.do")
	public ModelAndView cc(HttpServletRequest request) {
		String name=request.getParameter("name");
		System.out.println("�̸� : "+name);
		
		//WEB-INF/views/remResult.jsp�̵��ϰ� �信�� ${message}�� ����Ѵ�.
		return new ModelAndView("remResult", "message", "����ҿ� ���� test ����Դϴ�.\n"+name);
	}*/
	
	/**
	 * get����϶�
	 * @param request
	 * @return
	 */
	@GetMapping("/test.do")
	public ModelAndView dd(HttpServletRequest request) {
		String name=request.getParameter("name");
		System.out.println("get��� �̸� : "+name);
		
		//WEB-INF/views/remResult.jsp�̵��ϰ� �信�� ${message}�� ����Ѵ�.
		return new ModelAndView("remResult", "message", "����ҿ� ���� get-test ����Դϴ�.\n"+name);
	}
	
	/**
	 * post����϶�
	 * @param request
	 * @return
	 */
	@PostMapping("/test.do")
	public ModelAndView ee(HttpServletRequest request) {
		String name=request.getParameter("name");
		System.out.println("post��� �̸� : "+name);
		
		//WEB-INF/views/remResult.jsp�̵��ϰ� �信�� ${message}�� ����Ѵ�.
		return new ModelAndView("remResult", "message", "����ҿ� ���� post-test ����Դϴ�.\n\n"+name);
	}
	
	/**
	 * parameter������ ���� ȣ��Ǿ����� �޼ҵ带 �ٸ��� �ϱ�
	 */
	//@RequestMapping(value="/a.do", params = {"id"}) //�ּ� ������ �浹���� ��������
	@RequestMapping(value="/a.do", params = {"id=hee"})
	//@RequestMapping(value="/a.do", params = {"id!=hee"})
	//@RequestMapping(value="/a.do", params = {"id", "age"})
	public String ccc() {
		System.out.println("parameter������ ���� ȣ��Ǵ� �޼ҵ�");
		return "result"; // WEB-INF/views/remResult.jsp�̵�
	}
}
