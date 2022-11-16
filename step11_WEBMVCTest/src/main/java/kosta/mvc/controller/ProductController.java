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

@Controller //생성
@RequiredArgsConstructor //final필드를 기반으로 생성자 만들어준다.
public class ProductController {
	
	
	private final ProductService productService; //생성자를 통해서 자동주입  

	/**
	 * 전체검색
	 * */
	@RequestMapping("/index.kosta")
	public String index(Model model) {
		List<ProductDTO> productList = productService.select();
		model.addAttribute("productList", productList);//뷰에서 ${productList}
		
		return "productList"; //WEB-INF/views/productList.jsp이동
	}
	
	/**
	 * 등록폼
	 * */
	@RequestMapping("/{url}.kosta")
	public void url() {}
	
	/**
	 *등록하기
	 * */
	@RequestMapping("/insert.kosta")
	public String insert(ProductDTO product)throws MyErrorException {
		productService.insert(product);
		
		//등록이 완료되면 전체검색!!!
		return "redirect:/index.kosta"; //controller--> controller를 실행
		//return "forward:/index.kosta"; 
	}
	
	/**
	 * 삭제
	 * */
	@RequestMapping("/{code}")
	public String delete(@PathVariable String code)throws MyErrorException {
	     productService.delete(code);
	     
		return "redirect:/index.kosta";
	}
	
	/**
	 * 상품코드 상세보기
	 * */
	@RequestMapping("/read.kosta") 
	public void read(String code , Model model) throws MyErrorException {
		ProductDTO product = productService.selectByCode(code);
		model.addAttribute("product", product);//뷰에서 ${product}
	}
	
	
	
	@ExceptionHandler(value = {MyErrorException.class})
	public ModelAndView error(Exception e) {
		return new ModelAndView("error" , "errMessage", e.getMessage());
	}
}





