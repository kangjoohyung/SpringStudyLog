package kosta.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kosta.mvc.dao.ProductDAO;
import kosta.mvc.dto.ProductDTO;
import kosta.mvc.exception.MyErrorException;

@Service //���� id="productServiceImpl"
public class ProductServiceImpl implements ProductService {

	@Autowired //type�� ã�Ƽ� ����
	private ProductDAO productDAO;
	
	private final static int MIN_PIRCE=1000;
	private final static int MAX_PIRCE=10000;
	
	@Override
	public List<ProductDTO> select() {
		return productDAO.select();
	}

	@Override
	public int insert(ProductDTO productDTO) throws MyErrorException {
		//������ ������ üũ
		if(productDTO.getPrice() < MIN_PIRCE || productDTO.getPrice() > MAX_PIRCE) {
			throw new MyErrorException("������ ������ " + MAX_PIRCE +" ~ " + MAX_PIRCE +"���̸� �����մϴ�.");
		}
		
		int result = productDAO.insert(productDTO);
		
		return result;
	}

	@Override
	public int delete(String code) throws MyErrorException {
		int result = productDAO.delete(code);
		return result;
	}

	@Override
	public ProductDTO selectByCode(String code)throws MyErrorException {
		ProductDTO product = productDAO.selectByCode(code);
		if(product==null)throw new MyErrorException(code+"�� �ش��ϴ� ��ǰ�� �����ϴ�.");
		return product;
	}

}












