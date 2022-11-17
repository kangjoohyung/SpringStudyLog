package kosta.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kosta.mvc.dao.StudentDAO;
import kosta.mvc.dto.Student;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentDAO studentDAO;
	
	@Override
	public List<Student> selectAll() {
		
		return studentDAO.selectAll();
	}

	@Override
	public String stNoCheck(String stNo) {
		String dbStNo=studentDAO.stNoCheck(stNo);
		if(dbStNo==null) return "��밡���մϴ�"; //���� �����ϱ� null����
		else return "������Դϴ�."; //���� �����ϱ� null�ƴ�
	}

	@Override
	public int insert(Student student) {

		return studentDAO.insert(student);
	}

	@Override
	public int delete(String stNo) {
		
		return studentDAO.delete(stNo);
	}

}
