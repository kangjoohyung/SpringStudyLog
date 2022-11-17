package kosta.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import kosta.dto.EmpDTO;
import kosta.dto.StudentDTO;
import kosta.mapper.EmpMapper;
import kosta.util.DbUtil;

public class EmpDAO {

	/**
	 * emp테이블에서 사원이름 검색 (어노테이션 사용)
	 */
	public void selectName() {
		SqlSession session=null;
		try {
			session=DbUtil.getSession();
			//java기반의 쿼리를 호출할때는 Mapper interface를 구한다
			EmpMapper mapper=session.getMapper(EmpMapper.class);
			List<String> enameList=mapper.selectEname();
			for(String s : enameList) {
				System.out.println(s);
			}
			
		}finally {
			DbUtil.sessionClose(session);
		}
		
	}//selectName() end ->어노테이션
	
	/**
	 * 전체검색(리스트,annotation)
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
	 * 사원번호로 사원정보 검색(없거나 한명이 나오니까 selectOne)
	 * @param empno
	 */
	public void selectByEmpno(int empno) {
		SqlSession session=null;
		try {
			session=DbUtil.getSession();
			EmpMapper mapper=session.getMapper(EmpMapper.class);
			
			//결과가 0또는 1개 인경우는 selectOne 사용
			EmpDTO emp= mapper.selectByEmpno(empno);//타입은 동적으로 자동으로 할당
			System.out.println("emp="+emp);
		}finally {
			DbUtil.sessionClose(session);
		}
	}//selectByEmpno(int empno)
	
	/**
	 * 사원등록하기
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
	 * 사원번호로 수정하기
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
	}//update끝
	
	/**
	 * 삭제하기
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
	 * 동적쿼리(if, where, trim)
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
	//각종 select 사용 예제 메소드
	
	
	
	/**7.
	 * 급여조건으로 사원정보 검색
	 * : 입력한 숫자로 조건걸기(부등호 사용 : xml문서에서 쿼리상 &gt; &lt; 사용)
	 * @param sal
	 */
	public void selectLessThanSal(int sal) {
		SqlSession session=null;
		try {
			session=DbUtil.getSession();
			//결과가 얼마나 될지 모를때는 리스트 사용
			
			List<EmpDTO> empList=session.selectList("empSelectMapper.selectLessThanSal", sal);
			for(EmpDTO e : empList) {
				System.out.println(e);
			}
		}finally {
			DbUtil.sessionClose(session);
		}
	}//selectLessThanSal(int sal)
	
	/**8.
	 * 급여 최소~최대 사이의 사원정보 검색(parameterType="map"사용)
	 */
	public void selectMinAndMax(int minSal, int maxSal) {
		SqlSession session=null;
		try {
			session=DbUtil.getSession();

			//DTO에 없는 인수를 사용할때 map을 생성해서 사용(매퍼에도 map으로 넣어야함)
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
	 * 전달된 컬럼명으로 정렬하기(기본 asc)
	 * ->'~'으로 인식되어 preparedStatement에서는 사용 불가능하다 (#{~}불가능)
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
	 * 조건 여러개 입력받아 검색하기
	 * : 조건 여러개 예제+인수에 기존DTO를 활용하는 맵 사용(parameterType="map"사용)
	 * where upper (#{keyField}) like '%'||#{keyword}||'%' or empno=#{emp.empno} or sal> #{emp.sal}
	 */
	//where upper (#{keyField}) like '%'||#{keyword}||'%' or empno=#{emp.empno} or sal> #{emp.sal}
	public void selectWhereMix(String keyField, String keyword, int empno, int sal) {
		SqlSession session=null;
		try {
			session=DbUtil.getSession();

			//DTO에 없는 인수를 사용할때 map을 생성해서 사용(매퍼에도 map으로 넣어야함)
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
	 * job을 검색하여 해당하는 사원정보 검색
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
	 * Student 테이블 전체 검색
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
	 * 학생 등록하기 -> Mapper에서 null허용 등록이후 null사용가능(#{~}에서도)
	 */
	public void studentInsert(StudentDTO studentDTO) {
		SqlSession session=null;
		boolean state=false;
		try {
			session=DbUtil.getSession();
			state=session.insert("studentMapper.insert", studentDTO) > 0 ? true : false ; 
			//매퍼id, 인수 넣고, 삼형연산자에서는 state(결과값이) 0보다 크면, 즉 1이면 true
			//현재 예제에서는 insert를 메인에서 입력하게끔 만듦
			
			
			System.out.println("state="+state);
		}finally {
			DbUtil.sessionClose(session, state);
		} 
	}//studentInsert(StudentDTO studentDTO) end
}
