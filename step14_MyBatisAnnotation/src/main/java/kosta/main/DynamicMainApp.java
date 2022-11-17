package kosta.main;

import java.util.Arrays;

import config.DynamicDAO;
import kosta.dto.EmpDTO;

public class DynamicMainApp {

	public static void main(String[] args) {
//		System.out.println("1. if문 동적쿼리-----");
//		DynamicDAO.ifTest(null);//전체검색
//		//하나씩 테스트하기
//		DynamicDAO.ifTest(new EmpDTO(0, "KING", null, 0, null)); //ename만 입력
//		DynamicDAO.ifTest(new EmpDTO(0, null, "MANAGER", 0, null)); //job만 입력
//		DynamicDAO.ifTest(new EmpDTO(0, "KING", "MANAGER", 0, null)); //ename + job
		
//		System.out.println("\n2. set 동적쿼리"); //update 수정시 값이 꼭 들어와야한다.
//		DynamicDAO.setTest(new EmpDTO(8000, "삼순이", null, 0, null)); //이름만수정
//		DynamicDAO.setTest(new EmpDTO(8000, "4순이", "백수", 0, null)); //이름+job수정
//		DynamicDAO.setTest(new EmpDTO(8000, "5순이", "백탈", 3000, null)); //이름+job+sal수정
//		DynamicDAO.setTest(new EmpDTO(8000, "6순이", null, 2000, null)); //job+sal
//		DynamicDAO.setTest(new EmpDTO(8000, null, null, 1000, null)); //sal만수정
//		DynamicDAO.setTest(new EmpDTO(8000, null, "윽", 0, null)); //job만수정
		
//		DynamicDAO.ifTest(new EmpDTO(8000, null, null, 0, null)); //empno만 입력(확인용으로 추가)
		
		System.out.println("\n3. foreach 동적쿼리");
//		DynamicDAO.forEachTest(Arrays.asList(7698));
//		DynamicDAO.forEachTest(Arrays.asList(7698,7844,9334,8000,99999999));
		DynamicDAO.forEachTest(Arrays.asList(7698,7844,9334,8000,99999999,0));
	}

}
