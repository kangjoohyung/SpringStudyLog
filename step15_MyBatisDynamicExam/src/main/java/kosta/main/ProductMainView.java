package kosta.main;

import java.util.Arrays;

import kosta.dto.ProductDTO;
import kosta.repository.ProductDAO;

public class ProductMainView {
	public static void main(String[] args) {
		/**
		 * [1]. 레코드 삽입
		 */
		//System.out.println("********** [1] 상품 등록하기 ******************");
		//ProductDAO.productInsert(new ProductDTO("A06", "허니버터칩", 5, 2300, "달콤해요"));
		//ProductDAO.productInsert(new ProductDTO("A07", null, 5, 2300, "달콤해요"));
		
		/**
		   * [2].   ex) select * from productList
		   *        ex)  select * from productList where 검색필드 like  검색단어
		   *        ex) select *from productList order by 정렬대상
		   *       ex) select * from productList where 검색필드 like  검색단어  order by 정렬대상
		   * */
		System.out.println("********** [2] 모든 게시물 검색 또는 조건에 따른 검색 및 정렬 ******************");
		 // ProductDAO.productSearchOrder(null, null, "detail"); //정렬
		//ProductDAO.productSearchOrder(null, null, null);
		//ProductDAO.productSearchOrder("name", "우", null);
		//ProductDAO.productSearchOrder("name", "우", "price");
		
		 /**
		   * [3].  parameter 정보에 따라 레코드 수정한다.
		   *     상품코드에 해당하는 상품의 
		   *     상품이름, 상품수량, 상품가격, 상품상세정보를 수정하데 parameter 의 정보가 있는 데이터만 수정한다.
		   * 
		   * */
		//System.out.println("********** [3] 상품 수정하기 ******************");
		//ProductDAO.productUpdate(new ProductDTO("A05", "수정", 2, 3500, "수정된 내용"));
		//ProductDAO.productUpdate(new ProductDTO("A03", "수정2", 0, 1000, null));
		//ProductDAO.productUpdate(new ProductDTO("A10", "수정2", 0, 1000, null));
		
		/**
		   * [4].상품코드에 해당하는 레코드 삭제
		   * */
		//System.out.println("********** [4] 상품 삭제하기 ******************");
		//ProductDAO.productDelete("A03");
		
		/**
		  * [5]. 모든 상품의 개수를 출력한다.

			  select sum(p_qty) from product -- 모든상품의 qty의총합계
		  * */
		//System.out.println("********** [5] 등록된 총 상품 개수 ******************");
		//ProductDAO.selectSum();
		
		/**
		   * [6]. parameter의 상품코드에 해당하는 레코드 검색
		   * ex)select * from productList where in 상품코드 in (?,?,...)
		   * */
		System.out.println("********** [6] 상품코드에 해당하는 레코드 검색 ******************");
		ProductDAO.selectFor(Arrays.asList("A01", "A02", "A04","A09","A06","A11"));
		//ProductDAO.selectFor(Arrays.asList("A01", "A02"));
		
	}//main
	
}//ProductMainView


