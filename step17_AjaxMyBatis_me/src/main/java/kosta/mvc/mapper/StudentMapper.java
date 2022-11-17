package kosta.mvc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import kosta.mvc.dto.Student;

/**
 * xml����� mapper�� interface ��� �ڹٷ� �ۼ�
 *
 */
public interface StudentMapper {

	/**
	 * ��ü�˻�
	 */
	@Select("select * from student order by st_no desc")
	List<Student> selectAll();

	/**
	 * ���̵� �ߺ� üũ
	 */
	@Select("select st_no from student where st_no=#{_parameter}")
	String stNoCheck(String stNo);
	
	/**
	 * ����
	 */
	@Insert("insert into student\r\n"
			+ "values(#{stNo}, #{stName}, #{stAge}, #{stAddr}, #{stPhone})\r\n")
	int insert(Student student);
	
	/**
	 * ����
	 */
	@Delete("delete student where st_no=#{_parameter}")
	int delete(String stNo);
}
