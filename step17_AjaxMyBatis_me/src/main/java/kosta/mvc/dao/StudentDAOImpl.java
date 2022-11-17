package kosta.mvc.dao;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kosta.mvc.dto.Student;
import kosta.mvc.mapper.StudentMapper;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class StudentDAOImpl implements StudentDAO {

	private final SqlSession session;
	private StudentMapper mapper; 
	
	@PostConstruct
	public void init() { //주입후 생성자호출(진입용)
		System.out.println("session="+session);
		mapper=session.getMapper(StudentMapper.class); //interface mapper사용
	}
	
	@Override
	public List<Student> selectAll() {
		
		//return session.selectList("studentMapper.selectAll"); //xml용
		
		//위로 올리기-> 생성자필요한데 session주입 이후 사용해야함->PostConstruct 필요
		//StudentMapper mapper=session.getMapper(StudentMapper.class); //interface mapper사용
		return mapper.selectAll();
	}

	@Override
	public String stNoCheck(String stNo) {
		
		//return session.selectOne("studentMapper.stNoCheck", stNo);
		
		return mapper.stNoCheck(stNo);
	}

	@Override
	public int insert(Student student) {
		
//		return session.insert("studentMapper.insert", student);
		
		return mapper.insert(student);
	}

	@Override
	public int delete(String stNo) {
		
//		return session.delete("studentMapper.delete", stNo);
		
		return mapper.delete(stNo);
	}

}
