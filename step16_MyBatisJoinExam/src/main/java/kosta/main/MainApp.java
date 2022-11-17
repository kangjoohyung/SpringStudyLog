package kosta.main;

import kosta.repository.EmpDAO;

public class MainApp {

	public static void main(String[] args) {
		//System.out.println("1번 문제");
		EmpDAO.selectOne(0); //전체검색
		//EmpDAO.selectOne(7782); //사원번호에 해당하는 검색
		
		
		//System.out.println("2번 문제");
		//EmpDAO.selectTwo();
		
	   // System.out.println("3번 문제");
		//EmpDAO.selectThree();
		
		//System.out.println("4번 문제");
		//EmpDAO.selectFour();
		
	}//main
	
}//MainApp