package kosta.mvc.service;

import java.util.List;

import kosta.mvc.dto.Student;

public interface StudentService {
	/**
	 * �л����� ��ü�˻�
	 */
	List<Student> selectAll();
	
	/**
	 * �й� �ߺ�üũ -> �÷��ϳ��� ���� ��µǴ��� �������� String���� ����
	 */
	String stNoCheck(String stNo);
	
	
	/**
	 * �л� ����ϱ�
	 */
	int insert(Student student);
	
	/**
	 * �й��� �ش��ϴ� �л� ���� �����ϱ� 
	 */
	int delete(String stNo);
}
