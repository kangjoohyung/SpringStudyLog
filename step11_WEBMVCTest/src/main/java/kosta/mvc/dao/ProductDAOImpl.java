package kosta.mvc.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kosta.mvc.controller.ProductController;
import kosta.mvc.dto.ProductDTO;
import kosta.mvc.exception.MyErrorException;

@Repository //���� id="productDAOImpl"  -> �����Ϸ��� �ݵ�� <context:component-scan base-package="" ����
public class ProductDAOImpl implements ProductDAO {
	
	@Autowired
	private List<ProductDTO> productList; //ProductDTO��ü 5���� bean���� �����ؼ� ����(xml�����ۼ�)

	@Override
	public List<ProductDTO> select() {
		return productList;
	}

	@Override
	public int insert(ProductDTO productDTO) throws MyErrorException {
		//��ǰ�ڵ尡 �ߺ����� üũ�Ѵ�.
		if( this.selectByCode(productDTO.getCode()) != null) {
			//�ߺ��̴�.
			throw new MyErrorException(productDTO.getCode()+"�� �ߺ��̹Ƿ� ����Ҽ� �����ϴ�.");
		}
		
		//�߰�
		productList.add(productDTO);
		
		return 1;
	}

	@Override
	public int delete(String code) throws MyErrorException {
		ProductDTO dto = this.selectByCode(code);
		
		if(dto==null)throw new MyErrorException(code+"�� �ش��ϴ� ������ �����Ҽ� �����ϴ�.");
		
		productList.remove(dto);
		return 1;
	}

	@Override
	public ProductDTO selectByCode(String code) {
		for(ProductDTO dto : productList) {
			if(dto.getCode().equals(code)) {
				return dto; //ã�Ҵ�!!
			}
		}
		
		return null; //��ã�Ҵ�.
	}

}




