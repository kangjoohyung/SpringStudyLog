package kosta.mvc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import kosta.mvc.dto.Student;

/**
 * xml기반의 mapper를 interface 기반 자바로 작성
 *
 */
public interface StudentMapper {

	/**
	 * 전체검색
	 */
	@Select("select * from student order by st_no desc")
	List<Student> selectAll();

	/**
	 * 아이디 중복 체크
	 */
	@Select("select st_no from student where st_no=#{_parameter}")
	String stNoCheck(String stNo);
	
	/**
	 * 가입
	 */
	@Insert("insert into student\r\n"
			+ "values(#{stNo}, #{stName}, #{stAge}, #{stAddr}, #{stPhone})\r\n")
	int insert(Student student);
	
	/**
	 * 삭제
	 */
	@Delete("delete student where st_no=#{_parameter}")
	int delete(String stNo);
}
