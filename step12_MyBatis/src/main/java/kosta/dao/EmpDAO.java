package kosta.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import kosta.dto.EmpDTO;
import kosta.dto.StudentDTO;
import kosta.util.DbUtil;

public class EmpDAO {

	/**
	 * emp테이블에서 사원이름 검색
	 */
	public void selectName() {
		SqlSession session=null;
		try { //예외처리는 MyBatis에서 알아서 처리함(여기에 catch할필요없음)
			session=DbUtil.getSession();
			List<String> enames= session.selectList("empMapper.selectEname"); 
			// namespace이름.id / 하나들어올땐 one, 여러개면 List
			
			System.out.println("총 개수:"+enames.size());
			System.out.println("enames="+enames);
		}finally {
			DbUtil.sessionClose(session);
		}
		
	}//selectName() end
	
	/**
	 * 사원등록하기
	 */
	public void insert(EmpDTO empDTO) {
		SqlSession session=null;
		boolean state=false;
		try {
			session=DbUtil.getSession();
			state=session.insert("empMapper.insert", empDTO) > 0 ? true : false ; 
			//매퍼id, 인수 넣고, 삼형연산자에서는 state(결과값이) 0보다 크면, 즉 1이면 true
			//현재 예제에서는 insert를 메인에서 입력하게끔 만듦
			
			
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
			state=session.update("empMapper.updateByno", empDTO) > 0 ? true : false;
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
			state=session.delete("empMapper.deleteByno", empno) > 0 ? true : false;
			System.out.println("state="+state);
		}finally {
			DbUtil.sessionClose(session, state);
		}
	}
	
	/////////////////////////////////////////////////////////////////////
	//각종 select 사용 예제 메소드
	/**5.
	 * 전체검색(리스트, rownum처럼 출력개수 지정까지 해보기)
	 */
	public void selectAll() {
		SqlSession session=null;
		try {
			session=DbUtil.getSession();
			
			//결과가 0개 이상인경우(즉 전부) 리스트 사용가능
			//결과가 0또는 1개 인경우는 selectOne 사용
			//List<EmpDTO> empList=session.selectList("empSelectMapper.selectAll");
			//이대로 사용하면 컬럼명과 DTO다른것때문에 null나옴(empName)
			//원래는 다운캐스팅 필요하지만 myBatis에서는 자동으로 해줘서 필요없음
			
			
			RowBounds rb=new RowBounds(2,5); //index 2 부터, 즉 3번째부터 5개까지(3~7번째까지)
			List<EmpDTO> empList=session.selectList("empSelectMapper.selectAll",null , rb);
			

			//컬럼명과 맞추기위해서 DTO수정없이 맞추기 : 매퍼에서 쿼리를 별칭으로 사용 : 예) ename as empname
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
			

			//결과가 0또는 1개 인경우는 selectOne 사용
			EmpDTO emp= session.selectOne("empSelectMapper.selectByEmpno", empno);//타입은 동적으로 자동으로 할당
			System.out.println("emp="+emp);
		}finally {
			DbUtil.sessionClose(session);
		}
	}//selectByEmpno(int empno)
	
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
