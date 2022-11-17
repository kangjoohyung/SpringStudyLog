package kosta.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import kosta.dto.ProductDTO;

public class ProductDAO {
	
	public static void productInsert(ProductDTO productDTO) {
		SqlSession session = null;
		boolean state = false;
		try {
			session = DbUtil.getSession();
			state = session.insert("dynamicMapper.productInsert", productDTO) > 0 ?true:false;
		}finally {
			DbUtil.sessionClose(session, state);
		}//finally
	}//productInsert
	
	
	
	
	public static void productSearchOrder(String keyField, String keyWord, String orderWord) {
		SqlSession session = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			session = DbUtil.getSession();
			
			map.put("keyField", keyField);
			map.put("keyWord", keyWord);
			map.put("orderWord", orderWord);
			
			map.put("sort", 1);//내림차순
			
			
			List<ProductDTO> list = session.selectList("dynamicMapper.productSearchOrder", map);
			for(ProductDTO dto : list) {
				System.out.println(dto);
				/*System.out.print(dto.getCode() + " | ");
				System.out.print(dto.getName() + " | ");
				System.out.print(dto.getQty() + " | ");
				System.out.print(dto.getPrice() + " | ");
				System.out.println(dto.getDetail());*/
			}//for
		}finally {
			DbUtil.sessionClose(session);
		}//finally
	}//productSearchOrder
	
	
	public static void productUpdate(ProductDTO productDTO) {
		SqlSession session = null;
		boolean state = false;
		int result = 0;
		try {
			session = DbUtil.getSession();
			result = session.update("dynamicMapper.productUpdate", productDTO);
			
			if(result > 0) state = true;
			System.out.println("result = " + result);
			
		}finally {
			DbUtil.sessionClose(session, state);
		}//finally
	}//productUpdate
	
	
	
	public static void productDelete(String code) {
		SqlSession session = null;
		boolean state = false;
		int result = 0;
		try {
			session = DbUtil.getSession();
			result = session.delete("dynamicMapper.productDelete", code);
			if(result > 0) state = true;
			
		}finally {
			DbUtil.sessionClose(session, state);
		}//finally
	}//productDelete
	
	
	public static void selectSum() {
		SqlSession session = null;
		try {
			session = DbUtil.getSession();
			int qty = session.selectOne("dynamicMapper.productSum");
			System.out.println("등록된 총 qty의 개수는 : " + qty + "개 입니다");
		}finally {
			DbUtil.sessionClose(session);
		}//finally
	}//selectSum
	
	
	public static void selectFor(List<String> param) {
		SqlSession session = null;
		List<ProductDTO> list = null;
		try {
			session = DbUtil.getSession();
			list = session.selectList("dynamicMapper.productFor", param);
			for(ProductDTO dto : list) {
				System.out.print(dto.getCode() + " | ");
				System.out.print(dto.getName() + " | ");
				System.out.print(dto.getQty() + " | ");
				System.out.print(dto.getPrice() + " | ");
				System.out.println(dto.getDetail());
			}//for
		}finally {
			DbUtil.sessionClose(session);
		}//finally
		
	}//selectFor
	
	
	
}//ProductDAO