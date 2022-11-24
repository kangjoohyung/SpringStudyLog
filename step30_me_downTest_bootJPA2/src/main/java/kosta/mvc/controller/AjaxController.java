package kosta.mvc.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AjaxController {
	@RequestMapping("/ajax")
	public List<String> ajax(String name) {
		System.out.println("AjaxController의 ajax/ name="+name);
		return Arrays.asList("사과", "딸기", "포도", "바나나");
	}
}
