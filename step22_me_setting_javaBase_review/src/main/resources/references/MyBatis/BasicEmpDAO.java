package references.MyBatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import kosta.dto.EmpDTO;
import kosta.dto.StudentDTO;
import kosta.mapper.EmpMapper;
import kosta.util.DbUtil;

public class BasicEmpDAO {

	/**
	 * emp���̺��� ����̸� �˻� (������̼� ���)
	 */
	public void selectName() {
		SqlSession session=null;
		try {
			session=DbUtil.getSession();
			//java����� ������ ȣ���Ҷ��� Mapper interface�� ���Ѵ�
			EmpMapper mapper=session.getMapper(EmpMapper.class);
			List<String> enameList=mapper.selectEname();
			for(String s : enameList) {
				System.out.println(s);
			}
			
		}finally {
			DbUtil.sessionClose(session);
		}
		
	}//selectName() end ->������̼�
	
	/**
	 * ��ü�˻�(����Ʈ,annotation)
	 */
	public void selectAll() {
		SqlSession session=null;
		try {
			session=DbUtil.getSession();
			
			EmpMapper mapper=session.getMapper(EmpMapper.class);
			List<EmpDTO> empList=mapper.selectAll();
			for(EmpDTO e : empList) {
				System.out.println(e);
			}
		}finally {
			DbUtil.sessionClose(session);
		}
	}//selectAll()
	
	/**6.
	 * �����ȣ�� ������� �˻�(���ų� �Ѹ��� �����ϱ� selectOne)
	 * @param empno
	 */
	public void selectByEmpno(int empno) {
		SqlSession session=null;
		try {
			session=DbUtil.getSession();
			EmpMapper mapper=session.getMapper(EmpMapper.class);
			
			//����� 0�Ǵ� 1�� �ΰ��� selectOne ���
			EmpDTO emp= mapper.selectByEmpno(empno);//Ÿ���� �������� �ڵ����� �Ҵ�
			System.out.println("emp="+emp);
		}finally {
			DbUtil.sessionClose(session);
		}
	}//selectByEmpno(int empno)
	
	/**
	 * �������ϱ�
	 */
	public void insert(EmpDTO empDTO) {
		SqlSession session=null;
		boolean state=false;
		try {
			session=DbUtil.getSession();
			EmpMapper mapper=session.getMapper(EmpMapper.class);
			state=mapper.insert(empDTO) > 0 ? true : false ; 
			System.out.println("state="+state);
		}finally {
			DbUtil.sessionClose(session, state);
		} 
	}//insert(EmpDTO empDTO) end
	
	/**
	 * �����ȣ�� �����ϱ�
	 * updateByno
	 */
	public void update(EmpDTO empDTO) {
		SqlSession session=null;
		boolean state=false;
		try {
			session=DbUtil.getSession();
			EmpMapper mapper=session.getMapper(EmpMapper.class);
			state=mapper.insert(empDTO) > 0 ? true : false;
			System.out.println("state="+state);
		}finally {
			DbUtil.sessionClose(session, state);
		}
	}//update��
	
	/**
	 * �����ϱ�
	 */
	public void delete(int empno) {
		SqlSession session=null;
		boolean state=false;
		try {
			session=DbUtil.getSession();
			EmpMapper mapper=session.getMapper(EmpMapper.class);
			state=mapper.delete(empno) > 0 ? true : false;
			System.out.println("state="+state);
		}finally {
			DbUtil.sessionClose(session, state);
		}
	}
	
	/**
	 * ��������(if, where, trim)
	 */
	public static void ifTest(EmpDTO empDTO) {
		SqlSession session=null;
		try {
			session=DbUtil.getSession();
			EmpMapper mapper=session.getMapper(EmpMapper.class);
			List<EmpDTO> empList=mapper.ifSelect(empDTO);
			for(EmpDTO e:empList) {
				System.out.println(e);
			}
		}finally {
			DbUtil.sessionClose(session);
		}
	}
	
	
	/////////////////////////////////////////////////////////////////////
	//���� select ��� ���� �޼ҵ�
	
	
	
	/**7.
	 * �޿��������� ������� �˻�
	 * : �Է��� ���ڷ� ���ǰɱ�(�ε�ȣ ��� : xml�������� ������ &gt; &lt; ���)
	 * @param sal
	 */
	public void selectLessThanSal(int sal) {
		SqlSession session=null;
		try {
			session=DbUtil.getSession();
			//����� �󸶳� ���� �𸦶��� ����Ʈ ���
			
			List<EmpDTO> empList=session.selectList("empSelectMapper.selectLessThanSal", sal);
			for(EmpDTO e : empList) {
				System.out.println(e);
			}
		}finally {
			DbUtil.sessionClose(session);
		}
	}//selectLessThanSal(int sal)
	
	/**8.
	 * �޿� �ּ�~�ִ� ������ ������� �˻�(parameterType="map"���)
	 */
	public void selectMinAndMax(int minSal, int maxSal) {
		SqlSession session=null;
		try {
			session=DbUtil.getSession();

			//DTO�� ���� �μ��� ����Ҷ� map�� �����ؼ� ���(���ۿ��� map���� �־����)
			Map<String, Integer> map=new HashMap();
			map.put("min", minSal);
			map.put("max", maxSal);
			
			List<EmpDTO> empList=session.selectList("empSelectMapper.selectBetweenSal", map);
			for(EmpDTO e : empList) {
				System.out.println(e);
			}
		}finally {
			DbUtil.sessionClose(session);
		}
	}//selectMinAndMax(int minSal, int maxSal)
	
	/**9.
	 * ���޵� �÷������� �����ϱ�(�⺻ asc)
	 * ->'~'���� �νĵǾ� preparedStatement������ ��� �Ұ����ϴ� (#{~}�Ұ���)
	 * @param colName
	 */
	public void selectOrder(String colName) {
		SqlSession session=null;
		try {
			session=DbUtil.getSession();

			List<EmpDTO> empList=session.selectList("empSelectMapper.selectOrder", colName);
			for(EmpDTO e : empList) {
				System.out.println(e);
			}
		}finally {
			DbUtil.sessionClose(session);
		}
	}//selectOrder(String colName)
	
	/**10.
	 * ���� ������ �Է¹޾� �˻��ϱ�
	 * : ���� ������ ����+�μ��� ����DTO�� Ȱ���ϴ� �� ���(parameterType="map"���)
	 * where upper (#{keyField}) like '%'||#{keyword}||'%' or empno=#{emp.empno} or sal> #{emp.sal}
	 */
	//where upper (#{keyField}) like '%'||#{keyword}||'%' or empno=#{emp.empno} or sal> #{emp.sal}
	public void selectWhereMix(String keyField, String keyword, int empno, int sal) {
		SqlSession session=null;
		try {
			session=DbUtil.getSession();

			//DTO�� ���� �μ��� ����Ҷ� map�� �����ؼ� ���(���ۿ��� map���� �־����)
			Map<String, Object> map=new HashMap();
			map.put("keyField", keyField);
			map.put("keyword", keyword);
			map.put("emp", new EmpDTO(empno, null, null, sal, null));
			
			List<EmpDTO> empList=session.selectList("empSelectMapper.selectWhereMix", map);
			for(EmpDTO e : empList) {
				System.out.println(e);
			}
		}finally {
			DbUtil.sessionClose(session);
		}
	}//selectWhereMix(String keyField, String keyword, int empno, int sal)
	
	/**11.
	 * job�� �˻��Ͽ� �ش��ϴ� ������� �˻�
	 */
	public void selectByJob(String job) {
		SqlSession session=null;
		try {
			session=DbUtil.getSession();
			List<EmpDTO> empList=session.selectList("empMapper.selectByJob", job);
			for(EmpDTO e:empList) {
				System.out.println(e);
			}
		}finally {
			DbUtil.sessionClose(session);
		}
	}
	////////////////////////////////////////////////
	/**
	 * Student ���̺� ��ü �˻�
	 */
	public void studentSelectAll() {
		SqlSession session=null;
		try {
			session=DbUtil.getSession();
			List<StudentDTO> studentList=session.selectList("studentMapper.selectAll");
			
			for(StudentDTO e : studentList) {
				System.out.println(e);
			}
		}finally {
			DbUtil.sessionClose(session);
		}
	}//studentSelectAll()
	
	/**
	 * �л� ����ϱ� -> Mapper���� null��� ������� null��밡��(#{~}������)
	 */
	public void studentInsert(StudentDTO studentDTO) {
		SqlSession session=null;
		boolean state=false;
		try {
			session=DbUtil.getSession();
			state=session.insert("studentMapper.insert", studentDTO) > 0 ? true : false ; 
			//����id, �μ� �ְ�, ���������ڿ����� state(�������) 0���� ũ��, �� 1�̸� true
			//���� ���������� insert�� ���ο��� �Է��ϰԲ� ����
			
			
			System.out.println("state="+state);
		}finally {
			DbUtil.sessionClose(session, state);
		} 
	}//studentInsert(StudentDTO studentDTO) end
}
