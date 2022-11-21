package kosta.mvc.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kosta.mvc.dto.Member;

@Controller
@RequestMapping("/param")
public class ParameterController {
	
	/*
	@RequestMapping("/a.do")
	public String aa(HttpServletRequest request) {
		String name=request.getParameter("name");
		String age=request.getParameter("age");
		
		System.out.println("name="+name);
		System.out.println("age="+age);
		
		return "result"; // WEB-INF/views/result.jsp
	}
	*/
	
	/**
	 * return: void�� ��û�� �ּҰ� ���� �̸��� �ȴ�.
	 * WEB-INF/view/param/a.jsp�̵��Ѵ�.
	 * 
	 * �μ��� Ÿ�Ժ��� �޾����� ��üŸ���� parameter������ ������
	 * null�� ������, primitive Ÿ���� parameter������ ������
	 * null�� ���ε��� �� ���� ������ �߻��Ѵ�.
	 * 
	 * ----------------------
	 * ������ void�̰ų� String�϶� �������� ���޵Ǿ���ϴ� ������ �ʿ��Ҷ�
	 * Model�̶��, �������� ���޵� ��ü������ �μ� �ޱ�
	 * 
	 */
	@RequestMapping("/a.do") //������ �������� ��û���� ���̸����� ����
	public void bb(String name, Integer age, Model model) {
		//�μ��� �̸��� �����ϸ� �Ѿ�� -> int�� �ڵ���ȯ�Ǵµ�?
		//�̸��� �ٸ��μ������ ������ null�� ����
		//null�϶� int��(����Ÿ��) null�̸� ��������
		//�����ϱ����� int��� Integer�ιޱ�
		System.out.println("name="+name);
		System.out.println("age="+age);
		
		//�������� �����ؾ��ϴ� ����
		model.addAttribute("message", "spring web MVC �����");//�信�� ${message}
		model.addAttribute("hobbys", Arrays.asList("���", "����", "����", "����"));//�信�� ${hobbys}
		
	}
	
	/**
	 * �μ��� ��ü(DTO)�� �� ������� ������ ���ε��ϴ� ��ü�� ���޹�����
	 * �� ��ü�� ���ʿ��� �״�� ����� �� �ִ�
	 * �信�� ����� ���� ��ü �̸��� ù���ڸ� �ҹ��ڷ� ���� �����Ѵ�.
	 * ex:${member.�Ӽ�}
	 * 
	 * : ���� ��ü�� �̸��� ��ų� �����ϸ� ��Ī�� ���� ����� �� �ִ�
	 * 	@ModelAttribute
	 */
	@RequestMapping("/c.do") //���ϰ�(String)�� ���� �̸��̵�
	public String c(@ModelAttribute("m") Member mem) { //�ѹ��� ��ü�� �ޱ�(dtoȰ���Ͽ� �μ� �ѹ��� �ޱ�)
		//�⺻������+set���� �޾���(�Ѳ����� �μ��޴� ������ ����)
		//�信���� ���⼭ ���� �̸����� ������� ��ü���� �ձ��� �ҹ��ڷ� �μ� ��(${mem}�̾ƴ� ${member})
		System.out.println("mem="+mem);
		return "paramResult"; // WEB-INF/views/paramResult.jsp�� �̵�
	}
	
	
	/*
	@RequestMapping("/c.do")
	public String c(Member mem) { //�ѹ��� ��ü�� �ޱ�(dtoȰ���Ͽ� �μ� �ѹ��� �ޱ�)
		//�⺻������+set���� �޾���(�Ѳ����� �μ��޴� ������ ����)
		//�信���� ���⼭ ���� �̸����� ������� ��ü���� �ձ��� �ҹ��ڷ� �μ� ��(${mem}�̾ƴ� ${member})
		System.out.println("mem="+mem);
		return "paramResult"; // WEB-INF/views/paramResult.jsp�� �̵�
	}
	*/
	
	@ModelAttribute("info") //�信��${info}, return���� �μ�����
	public String test() {
		return "������ ȭ����";
	}
	
	@ModelAttribute("menus") //�信�� ${menus}, return���� �μ��� ��
	public List<String> menus(){
		return Arrays.asList("«��", "¥���", "«¥��", "������");
	}
	
	////////////////////
	@RequestMapping("/d.do")
	public String d(@RequestParam(defaultValue="Guest")  String id, 
					@RequestParam(value="name", required = false) String userName, 
					@RequestParam(defaultValue="0") int age) {
		//RequestParam���� ���� ������ �ʼ���ҿ��� null�϶� ���� �߻�
		//-> required=false��� �ʼ���Ұ� �ƴ϶�� ��-> null�̾ 400������ �߻� ����
		//RequestParam�� int�϶� Integer�� �ȹٲ㵵 defaultValue ������ �Ͽ���, null�� ��ȸ��
		
		System.out.println("id="+id);
		System.out.println("userName="+userName);
		System.out.println("age="+age);
		return "result"; // WEB-INF/views/result.jsp �̵�
	}
}
