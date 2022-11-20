package references.MyBatis.동적쿼리;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kosta.dto.EmpDTO;

/**
 * xml기반의 mapper를 대신 해주는 interface
 * : 모든 필드는 public static final
 * : 모든 메소드는 public abstract
 */
public interface EmpMapper {
	
	String empSelectSQL="select empno, ename as empname, job, sal, hiredate from emp";
	//자주사용하는 쿼리를 하나의 상수처럼 만들어 사용
	//(괄호안에 empSelectSQL+" ~~")

	/**
	 * emp테이블에 사원의 이름 검색
	 * ->이름 여러개 사용해서 list로
	 */
	@Select("select ename from emp")
	List<String> selectEname();
	
	/**
	 * 전체검색하기
	 */
	@Select(empSelectSQL+" order by empno desc")
	List<EmpDTO> selectAll();
	
	/**
	 * 사원번호에 해당하는 사원정보 검색
	 */
	@Select(empSelectSQL+" where empno=#{_parameter}")
	EmpDTO selectByEmpno(int empno);
	
	/**
	 * 등록하기
	 */
	@Insert("insert into emp(empno, ename, job, sal, hiredate) "
			+ "values(#{empno}, #{empName}, #{job}, #{sal}, sysdate)")
	int insert(EmpDTO empDTO);
	
	/**
	 * 수정하기 : 사원번호 입력
	 */
	@Update("update emp set ename=#{empName}, job=#{job}, sal=#{sal} where empno=#{empno}")
	int update(EmpDTO empDTO);
	
	/**
	 * 삭제하기 : 사원번호 입력
	 */
	@Delete("delete from emp where empno=#{_parameter}")
	int delete(int empno);
	
	/**
	 * 동적쿼리를 작성할 때는 전체 구문을 <script></script>로 묶는다.
	 * 1.emp테이블에서 조건에 따라 검색하기
	 * ename이 들어오면 ename에 해당하는 레코드 검색 or
	 * job이 들어오면 job에 해당하는 레코드 검색
	 * 둘다 들어오면 ename과 job을 조건으로 사용
	 */
	@Select("<script>"+empSelectSQL+"<trim prefix=\"where\" prefixOverrides=\"and|or\">\n"
			+ "		<if test=\"empno!=0\">\n"
			+ "			empno=#{empno}\n"
			+ "		</if>\n"
			+ "		<if test=\"empName!=null\">\n"
			+ "			and ename=#{empName}\n"
			+ "		</if>\n"
			+ "		<if test=\"job!=null\">\n"
			+ "			and job=#{job}\n"
			+ "		</if>\n"
			+ "	</trim></script>")//int자리에 null쓰니까 인식안됨, 0으로 바꾸기
	List<EmpDTO> ifSelect(EmpDTO empDTO);
	
}
