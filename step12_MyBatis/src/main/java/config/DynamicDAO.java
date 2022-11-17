package config;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import kosta.dto.EmpDTO;
import kosta.util.DbUtil;

public class DynamicDAO {

	/**
	 * 동적쿼리 (if, where, trim)
	 */
	public static void ifTest(EmpDTO empDTO) {
		SqlSession session=null;
		try {
			session=DbUtil.getSession();
			List<EmpDTO> empList= session.selectList("dynamicMapper.ifSelect", empDTO);
			for(EmpDTO e:empList) {
				System.out.println(e);
			}
		}finally {
			DbUtil.sessionClose(session);
		}
	}//ifTest
	
	/**
	 * set, trim 실습
	 * 수정할때는 set,trim을 사용하더라도
	 * 적어도 수정할 데이터가 1개는 꼭 들어와야한다
	 * (에러나나?)
	 */
	public static void setTest(EmpDTO empDTO) {
		SqlSession session=null;
		boolean state=false;
		try {
			session=DbUtil.getSession();
			state= session.update("dynamicMapper.setTest", empDTO) > 0 ? true : false;
			System.out.println("결과:"+state);
		}finally {
			DbUtil.sessionClose(session, state);
		}
	}//setTest(EmpDTO empDTO)
	
	/**
	 * forEach
	 */
	public static void forEachTest(List<Integer> empno) {
		SqlSession session=null;
		try {
			session=DbUtil.getSession();
	
			List<EmpDTO> empList=session.selectList("dynamicMapper.forEachTest",empno);
			

			//컬럼명과 맞추기위해서 DTO수정없이 맞추기 : 매퍼에서 쿼리를 별칭으로 사용 : 예) ename as empname
			for(EmpDTO e : empList) {
				System.out.println(e);
			}
		}finally {
			DbUtil.sessionClose(session);
		}
	}
}
