package kosta.mvc.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller //생성 id="requestMappingController"
@RequestMapping("/rem") //진입점 구분을위해 RequestMapping 사용
public class RequestMappingController {
	
	/**
	 * 
	 * @return : String인 경우는 리턴값이 뷰의 이름이 된다.
	 */
	@RequestMapping("/a.do") //요청이 a.do가 되면 메소드를 실행->메소드이름은 중요하지 않음
	public String aa() {
		System.out.println("RequestMappingController의 a.do요청되었습니다");
		return "result";
	}
	
	/**
	 * return : void인 경우는 요청된 주소가 뷰의 이름이 된다.
	 * ex: rem/b.do라고 요청이되면
	 * 		prefix + rem/b + suffix
	 * 		결론은 /WEB-INF/views/rem/b.jsp 이동
	 */
	@RequestMapping(value= {"/b.do","/c.do"}) // 여러개 요청을 한꺼번에 처리할때
	public void bb() {
		System.out.println("rem/b.do or rem/c.do 요청되었습니다");
	}
	
	/*
	@RequestMapping("/test.do")
	public ModelAndView cc(HttpServletRequest request) {
		String name=request.getParameter("name");
		System.out.println("이름 : "+name);
		
		//WEB-INF/views/remResult.jsp이동하고 뷰에서 ${message}를 사용한다.
		return new ModelAndView("remResult", "message", "폼요소에 대한 test 결과입니다.\n"+name);
	}*/
	
	/**
	 * get방식일때
	 * @param request
	 * @return
	 */
	@GetMapping("/test.do")
	public ModelAndView dd(HttpServletRequest request) {
		String name=request.getParameter("name");
		System.out.println("get방식 이름 : "+name);
		
		//WEB-INF/views/remResult.jsp이동하고 뷰에서 ${message}를 사용한다.
		return new ModelAndView("remResult", "message", "폼요소에 대한 get-test 결과입니다.\n"+name);
	}
	
	/**
	 * post방식일때
	 * @param request
	 * @return
	 */
	@PostMapping("/test.do")
	public ModelAndView ee(HttpServletRequest request) {
		String name=request.getParameter("name");
		System.out.println("post방식 이름 : "+name);
		
		//WEB-INF/views/remResult.jsp이동하고 뷰에서 ${message}를 사용한다.
		return new ModelAndView("remResult", "message", "폼요소에 대한 post-test 결과입니다.\n\n"+name);
	}
	
	/**
	 * parameter정보에 따라 호출되어지는 메소드를 다르게 하기
	 */
	//@RequestMapping(value="/a.do", params = {"id"}) //주소 같으면 충돌나서 에러생김
	@RequestMapping(value="/a.do", params = {"id=hee"})
	//@RequestMapping(value="/a.do", params = {"id!=hee"})
	//@RequestMapping(value="/a.do", params = {"id", "age"})
	public String ccc() {
		System.out.println("parameter정보에 따라 호출되는 메소드");
		return "result"; // WEB-INF/views/remResult.jsp이동
	}
}
