package kosta.main;

import kosta.dao.JoinDAO;

public class MainApp {

	public static void main(String[] args) {
//		System.out.println("1. 1:1 - 게시물 + 작성자 정보 검색");
//		JoinDAO.association();
		
//		System.out.println("2. 1:다 - 게시물+리플 전체검색 ");
//		JoinDAO.collection();
		
		System.out.println("3. 입력된 user기준으로 조인하는 정보검색");
		JoinDAO.userCollection("kim");
	}

}
