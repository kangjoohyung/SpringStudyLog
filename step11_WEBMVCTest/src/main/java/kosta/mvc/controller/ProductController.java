package kosta.mvc.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kosta.mvc.dto.ProductDTO;
import kosta.mvc.exception.MyErrorException;
import kosta.mvc.service.ProductService;
import lombok.RequiredArgsConstructor;

@Controller //����
@RequiredArgsConstructor //final�ʵ带 ������� ������ ������ش�.
public class ProductController {
	
	
	private final ProductService productService; //�����ڸ� ���ؼ� �ڵ�����  

	/**
	 * ��ü�˻�
	 * */
	@RequestMapping("/index.kosta")
	public String index(Model model) {
		List<ProductDTO> productList = productService.select();
		model.addAttribute("productList", productList);//�信�� ${productList}
		
		return "productList"; //WEB-INF/views/productList.jsp�̵�
	}
	
	/**
	 * �����
	 * */
	@RequestMapping("/{url}.kosta")
	public void url() {}
	
	/**
	 *����ϱ�
	 * */
	@RequestMapping("/insert.kosta")
	public String insert(ProductDTO product)throws MyErrorException {
		productService.insert(product);
		
		//����� �Ϸ�Ǹ� ��ü�˻�!!!
		return "redirect:/index.kosta"; //controller--> controller�� ����
		//return "forward:/index.kosta"; 
	}
	
	/**
	 * ����
	 * */
	@RequestMapping("/{code}")
	public String delete(@PathVariable String code)throws MyErrorException {
	     productService.delete(code);
	     
		return "redirect:/index.kosta";
	}
	
	/**
	 * ��ǰ�ڵ� �󼼺���
	 * */
	@RequestMapping("/read.kosta") 
	public void read(String code , Model model) throws MyErrorException {
		ProductDTO product = productService.selectByCode(code);
		model.addAttribute("product", product);//�信�� ${product}
	}
	
	
	
	@ExceptionHandler(value = {MyErrorException.class})
	public ModelAndView error(Exception e) {
		return new ModelAndView("error" , "errMessage", e.getMessage());
	}
}





