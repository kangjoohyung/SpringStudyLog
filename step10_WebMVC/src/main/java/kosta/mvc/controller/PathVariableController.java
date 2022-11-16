package kosta.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PathVariableController {

	@RequestMapping("/{type}/{id}.do")//{~}.do 쓰면 ~.do는 다 실행, ~자리가 id
	public void test(@PathVariable String type, @PathVariable String id) {
		System.out.println("PathVariableController의 test()");
		
		System.out.println("type="+type);
		System.out.println("id="+id);
	}
	
	@RequestMapping("/{id}")//   blog/ 쓰지말기(이미 패턴에 /blog/* 로 들어가있음
	public void test2(@PathVariable String id) {
		System.out.println("PathVariableController의 test2()");
		System.out.println("id="+id);
	}
	

	@RequestMapping("/{id1}/{id2}/{id3}")//   blog/이후에 값들이 많을때 각각에 맞춰 지정해야함
	public void test3(@PathVariable String id1, @PathVariable String id2, @PathVariable String id3) {
		System.out.println("PathVariableController의 test3()");
		System.out.println("id1="+id1+"id2="+id2+"id3="+id3);
	}
	

	@RequestMapping("/{url}.do")//
	public void test4(@PathVariable String url) {
		System.out.println("PathVariableController의 test4()");
		System.out.println("url="+url);
	}
}
