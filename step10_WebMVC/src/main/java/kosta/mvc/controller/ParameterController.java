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
	 * return: void는 요청된 주소가 뷰의 이름이 된다.
	 * WEB-INF/view/param/a.jsp이동한다.
	 * 
	 * 인수로 타입별로 받았을때 객체타입은 parameter정보가 없으면
	 * null로 되지만, primitive 타입은 parameter정보가 없으면
	 * null로 바인딩할 수 없어 에러가 발생한다.
	 * 
	 * ----------------------
	 * 리턴이 void이거나 String일때 뷰쪽으로 전달되어야하는 정보가 필요할때
	 * Model이라는, 뷰쪽으로 전달될 객체정보의 인수 받기
	 * 
	 */
	@RequestMapping("/a.do") //리턴이 없을때는 요청값이 뷰이름으로 적용
	public void bb(String name, Integer age, Model model) {
		//인수에 이름을 같게하면 넘어옴 -> int도 자동변환되는듯?
		//이름이 다른인수라던가 없으면 null이 들어옴
		//null일때 int가(원시타입) null이면 에러생김
		//방지하기위해 int대신 Integer로받기
		System.out.println("name="+name);
		System.out.println("age="+age);
		
		//뷰쪽으로 전달해야하는 정보
		model.addAttribute("message", "spring web MVC 재미있");//뷰에서 ${message}
		model.addAttribute("hobbys", Arrays.asList("등산", "수영", "낚시", "수영"));//뷰에서 ${hobbys}
		
	}
	
	/**
	 * 인수로 객체(DTO)를 즉 폼요소의 값들을 바인딩하는 객체를 전달받으면
	 * 그 객체는 뷰쪽에서 그대로 사용할 수 있다
	 * 뷰에서 사용할 때는 객체 이름의 첫글자를 소문자로 만들어서 접근한다.
	 * ex:${member.속성}
	 * 
	 * : 만약 객체의 이름이 길거나 복잡하면 별칭을 만들어서 사용할 수 있다
	 * 	@ModelAttribute
	 */
	@RequestMapping("/c.do") //리턴값(String)이 뷰의 이름이됨
	public String c(@ModelAttribute("m") Member mem) { //한번에 객체로 받기(dto활용하여 인수 한번에 받기)
		//기본생성자+set으로 받아짐(한꺼번에 인수받는 생성자 말고)
		//뷰에서는 여기서 받은 이름과는 관계없이 객체명에서 앞글자 소문자로 인수 들어감(${mem}이아닌 ${member})
		System.out.println("mem="+mem);
		return "paramResult"; // WEB-INF/views/paramResult.jsp로 이동
	}
	
	
	/*
	@RequestMapping("/c.do")
	public String c(Member mem) { //한번에 객체로 받기(dto활용하여 인수 한번에 받기)
		//기본생성자+set으로 받아짐(한꺼번에 인수받는 생성자 말고)
		//뷰에서는 여기서 받은 이름과는 관계없이 객체명에서 앞글자 소문자로 인수 들어감(${mem}이아닌 ${member})
		System.out.println("mem="+mem);
		return "paramResult"; // WEB-INF/views/paramResult.jsp로 이동
	}
	*/
	
	@ModelAttribute("info") //뷰에서${info}, return값이 인수가됨
	public String test() {
		return "오늘은 화요일";
	}
	
	@ModelAttribute("menus") //뷰에서 ${menus}, return값이 인수가 됨
	public List<String> menus(){
		return Arrays.asList("짬뽕", "짜장면", "짬짜면", "탕수육");
	}
	
	////////////////////
	@RequestMapping("/d.do")
	public String d(@RequestParam(defaultValue="Guest")  String id, 
					@RequestParam(value="name", required = false) String userName, 
					@RequestParam(defaultValue="0") int age) {
		//RequestParam에서 값이 없으면 필수요소여서 null일때 에러 발생
		//-> required=false라면 필수요소가 아니라는 뜻-> null이어도 400번에러 발생 안함
		//RequestParam이 int일때 Integer로 안바꿔도 defaultValue 설정을 하여서, null을 우회함
		
		System.out.println("id="+id);
		System.out.println("userName="+userName);
		System.out.println("age="+age);
		return "result"; // WEB-INF/views/result.jsp 이동
	}
}
