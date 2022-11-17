package kosta.mvc.service;

import java.util.List;

import kosta.mvc.dto.Student;

public interface StudentService {
	/**
	 * 학생정보 전체검색
	 */
	List<Student> selectAll();
	
	/**
	 * 학번 중복체크 -> 컬럼하나만 정보 출력되는지 보기위해 String으로 생성
	 */
	String stNoCheck(String stNo);
	
	
	/**
	 * 학생 등록하기
	 */
	int insert(Student student);
	
	/**
	 * 학번에 해당하는 학생 정보 삭제하기 
	 */
	int delete(String stNo);
}
