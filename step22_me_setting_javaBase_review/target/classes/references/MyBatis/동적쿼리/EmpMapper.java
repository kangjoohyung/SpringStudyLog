package references.MyBatis.��������;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kosta.dto.EmpDTO;

/**
 * xml����� mapper�� ��� ���ִ� interface
 * : ��� �ʵ�� public static final
 * : ��� �޼ҵ�� public abstract
 */
public interface EmpMapper {
	
	String empSelectSQL="select empno, ename as empname, job, sal, hiredate from emp";
	//���ֻ���ϴ� ������ �ϳ��� ���ó�� ����� ���
	//(��ȣ�ȿ� empSelectSQL+" ~~")

	/**
	 * emp���̺� ����� �̸� �˻�
	 * ->�̸� ������ ����ؼ� list��
	 */
	@Select("select ename from emp")
	List<String> selectEname();
	
	/**
	 * ��ü�˻��ϱ�
	 */
	@Select(empSelectSQL+" order by empno desc")
	List<EmpDTO> selectAll();
	
	/**
	 * �����ȣ�� �ش��ϴ� ������� �˻�
	 */
	@Select(empSelectSQL+" where empno=#{_parameter}")
	EmpDTO selectByEmpno(int empno);
	
	/**
	 * ����ϱ�
	 */
	@Insert("insert into emp(empno, ename, job, sal, hiredate) "
			+ "values(#{empno}, #{empName}, #{job}, #{sal}, sysdate)")
	int insert(EmpDTO empDTO);
	
	/**
	 * �����ϱ� : �����ȣ �Է�
	 */
	@Update("update emp set ename=#{empName}, job=#{job}, sal=#{sal} where empno=#{empno}")
	int update(EmpDTO empDTO);
	
	/**
	 * �����ϱ� : �����ȣ �Է�
	 */
	@Delete("delete from emp where empno=#{_parameter}")
	int delete(int empno);
	
	/**
	 * ���������� �ۼ��� ���� ��ü ������ <script></script>�� ���´�.
	 * 1.emp���̺��� ���ǿ� ���� �˻��ϱ�
	 * ename�� ������ ename�� �ش��ϴ� ���ڵ� �˻� or
	 * job�� ������ job�� �ش��ϴ� ���ڵ� �˻�
	 * �Ѵ� ������ ename�� job�� �������� ���
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
			+ "	</trim></script>")//int�ڸ��� null���ϱ� �νľȵ�, 0���� �ٲٱ�
	List<EmpDTO> ifSelect(EmpDTO empDTO);
	
}
