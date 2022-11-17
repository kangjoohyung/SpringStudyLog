package kosta.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import kosta.dto.DeptDTO;
import kosta.dto.EmpDTO;
import kosta.dto.SalgradeDTO;

public class EmpDAO {

	public static void selectOne(int empno) {
		SqlSession session = null;
		try {
			session = DbUtil.getSession();
			
			List<EmpDTO> list = session.selectList("joinMapper.selectOne", empno);
			
			for (EmpDTO dto : list) {
				
				System.out.println(dto);
				
				/*System.out.print(dto.getEmpNo() + " | ");
				System.out.print(dto.getEname() + " | ");
				System.out.print(dto.getJob() + " | ");
				System.out.print(dto.getHireDate() + " | ");
				System.out.print(dto.getDept().getDname() + " | ");
				System.out.println(dto.getDept().getLoc());*/
			} // for

		} finally {
			DbUtil.sessionClose(session);
		} // finally

	}// selectOne

	
	/**
	 * 부서를 기준으로 사원 정보 검색하기 
	 * */
	public static void selectTwo() {
		SqlSession session = null;
		try {
			session = DbUtil.getSession();
			List<DeptDTO> deptList = session.selectList("joinMapper.selectTwo");
			System.out.println("--총 부서 개수( "+deptList.size()+" )-------");
			for (DeptDTO deptDTO : deptList) {
				List<EmpDTO> empList = deptDTO.getEmpList();
				System.out.println(deptDTO.getDeptNo() +" | " +deptDTO.getDname()+"부서의 사원정보");
				
				for (EmpDTO empDTO : empList) {
					//System.out.print(deptDTO.getDname() + " | ");
					//System.out.print(deptDTO.getLoc() + " | ");
					System.out.print(empDTO.getEmpNo() + " | ");
					System.out.println(empDTO.getEname());
				} // for
				System.out.println();
			} // for

		} finally {
			DbUtil.sessionClose(session);
		} // finally

	}// selectTwo

	
	/**
	 * 사원을 기준으로 급여등급 검색하기 
	 * */
	public static void selectThree() {
		SqlSession session = null;
		try {
			session = DbUtil.getSession();
			List<EmpDTO> list = session.selectList("joinMapper.selectThree");
			for (EmpDTO dto : list) {
				System.out.print(dto.getEmpNo() + " | ");
				System.out.print(dto.getEname() + " | ");
				System.out.print(dto.getJob() + " | ");
				System.out.print(dto.getSal() + " | ");
				
				System.out.print(dto.getSalgrade().getGrade() + " | ");
				System.out.print(dto.getSalgrade().getLosal() + " | ");
				System.out.println(dto.getSalgrade().getHisal());
			} // for

		} finally {
			DbUtil.sessionClose(session);
		} // finally

	}// selectThree

	public static void selectFour() {
		SqlSession session = null;
		try {
			session = DbUtil.getSession();
			List<SalgradeDTO> salList = session.selectList("joinMapper.selectFour");
			System.out.println("salList.size() : " + salList.size());
			
			for (SalgradeDTO salDTO : salList) {
				List<EmpDTO> empList = salDTO.getEmpList();
				System.out.println(salDTO.getGrade() +"(" +salDTO.getLosal()+" ~ "+salDTO.getHisal()+")등급별 사원정보");
				for (EmpDTO empDTO : empList) {
					System.out.print(empDTO.getEmpNo() + " | ");
					System.out.print(empDTO.getEname() + " | ");
					System.out.print(empDTO.getJob() + " | ");
					System.out.println(empDTO.getSal());
				} // for
				System.out.println();
			} // for

		} finally {
			DbUtil.sessionClose(session);
		} // finally

	}// selectFour

}// EmpDAO