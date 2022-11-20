package kosta.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//@Controller
@RestController //->ȭ���� �ٲ��� ���� @Controller and @ResponseBody
public class ResponseBodyController {
	
	@RequestMapping(value="/responseBody.do", produces="text/html;charset=UTF-8")
	@ResponseBody
	public String aa() {
		
		return "aa()";
	}
	
	@RequestMapping(value="/responseBody2.do")
	public String bb() {
		
		return "bb()";
	}
	
	
}
