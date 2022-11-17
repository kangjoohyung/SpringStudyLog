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
	
	@GetMapping("/select") //RequestMapping-post,get���о��� / PostMapping -post�� / GetMapping-get��
	public List<Student> select(){
		//jackson lib�� ������Ÿ�� ��ȯ����
		return studentService.selectAll();
	}
	
	/**
	 * ���̵� �ߺ�üũ
	 * @param stNo
	 * @return String(text)
	 * ->���ڵ� ó�� �ʿ�(jackson���۾���)
	 */
	@RequestMapping(value="/check", produces="text/html;charset=UTF-8")
	public String idCheck(String stNo) {
		//String�� text�� �������� jackson�� �����ʴ´�.
		//���ڵ�ó���� �ȵǾ� �ѱ��� ������ -> produces �Ӽ� �߰�
		
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
