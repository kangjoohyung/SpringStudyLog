package kosta.mvc.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kosta.mvc.controller.ProductController;
import kosta.mvc.dto.ProductDTO;
import kosta.mvc.exception.MyErrorException;

@Repository //생성 id="productDAOImpl"  -> 가능하려면 반드시 <context:component-scan base-package="" 포함
public class ProductDAOImpl implements ProductDAO {
	
	@Autowired
	private List<ProductDTO> productList; //ProductDTO객체 5개를 bean으로 생성해서 주입(xml문서작성)

	@Override
	public List<ProductDTO> select() {
		return productList;
	}

	@Override
	public int insert(ProductDTO productDTO) throws MyErrorException {
		//상품코드가 중복인지 체크한다.
		if( this.selectByCode(productDTO.getCode()) != null) {
			//중복이다.
			throw new MyErrorException(productDTO.getCode()+"는 중복이므로 등록할수 없습니다.");
		}
		
		//추가
		productList.add(productDTO);
		
		return 1;
	}

	@Override
	public int delete(String code) throws MyErrorException {
		ProductDTO dto = this.selectByCode(code);
		
		if(dto==null)throw new MyErrorException(code+"에 해당하는 정보는 삭제할수 없습니다.");
		
		productList.remove(dto);
		return 1;
	}

	@Override
	public ProductDTO selectByCode(String code) {
		for(ProductDTO dto : productList) {
			if(dto.getCode().equals(code)) {
				return dto; //찾았다!!
			}
		}
		
		return null; //못찾았다.
	}

}




