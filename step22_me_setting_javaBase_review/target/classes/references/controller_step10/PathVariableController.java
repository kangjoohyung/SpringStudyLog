package kosta.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PathVariableController {

	@RequestMapping("/{type}/{id}.do")//{~}.do ���� ~.do�� �� ����, ~�ڸ��� id
	public void test(@PathVariable String type, @PathVariable String id) {
		System.out.println("PathVariableController�� test()");
		
		System.out.println("type="+type);
		System.out.println("id="+id);
	}
	
	@RequestMapping("/{id}")//   blog/ ��������(�̹� ���Ͽ� /blog/* �� ������
	public void test2(@PathVariable String id) {
		System.out.println("PathVariableController�� test2()");
		System.out.println("id="+id);
	}
	

	@RequestMapping("/{id1}/{id2}/{id3}")//   blog/���Ŀ� ������ ������ ������ ���� �����ؾ���
	public void test3(@PathVariable String id1, @PathVariable String id2, @PathVariable String id3) {
		System.out.println("PathVariableController�� test3()");
		System.out.println("id1="+id1+"id2="+id2+"id3="+id3);
	}
	

	@RequestMapping("/{url}.do")//
	public void test4(@PathVariable String url) {
		System.out.println("PathVariableController�� test4()");
		System.out.println("url="+url);
	}
}
