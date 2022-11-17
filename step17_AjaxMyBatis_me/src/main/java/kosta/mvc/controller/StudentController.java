package kosta.mvc.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kosta.mvc.dto.Student;
import kosta.mvc.service.StudentService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class StudentController {

	private final StudentService studentService;
	
	@GetMapping("/select") //RequestMapping-post,get구분안함 / PostMapping -post만 / GetMapping-get만
	public List<Student> select(){
		//jackson lib가 데이터타입 변환해줌
		return studentService.selectAll();
	}
	
	/**
	 * 아이디 중복체크
	 * @param stNo
	 * @return String(text)
	 * ->인코딩 처리 필요(jackson동작안함)
	 */
	@RequestMapping(value="/check", produces="text/html;charset=UTF-8")
	public String idCheck(String stNo) {
		//String은 text로 보내져서 jackson을 쓰지않는다.
		//인코딩처리가 안되어 한글이 깨진다 -> produces 속성 추가
		
		return studentService.stNoCheck(stNo);
	}
	
	@PostMapping("/insert")
	public int insert(Student student) {
		return studentService.insert(student);
	}
	
	@RequestMapping("/delete")
	public int delete(String stNo) {
		return studentService.delete(stNo);
	}
}
