package kosta.mvc.service;

import java.util.List;

import kosta.mvc.dto.ProductDTO;
import kosta.mvc.exception.MyErrorException;

public interface ProductService {
	/**
	    * 검색
	    * */
		List<ProductDTO> select();
		
		/**
		 * 등록
		 * */
		int insert(ProductDTO productDTO)throws MyErrorException;
		
		/**
		 * 삭제
		 * */
		 int delete(String code)throws MyErrorException;

		 /**
	     상세보기
	   */
	    ProductDTO selectByCode(String code) throws MyErrorException;
}
