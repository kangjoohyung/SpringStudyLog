package kosta.web.mvc.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.view.BeanNameViewResolver;

/**
 * 업로드-다운로드설정
 *
 */
@Configuration
public class UpDownAppConfig {
   /**
    * 예외처리 bean등록
    * */
	@Bean
    public SimpleMappingExceptionResolver getSimpleMappingExceptionResolver() {
		SimpleMappingExceptionResolver exceptionResolver = new SimpleMappingExceptionResolver();
		Properties pro = new Properties();
		pro.put("Exception", "error/errorMessage");
		
		exceptionResolver.setExceptionMappings(pro);
		return exceptionResolver;
	}
	
	/**
	 *  
	 * 업로드 컴포넌트 빈 등록
	 *   : 중요  - 메소드이름이 multipartResolver 이다.
	 * */
	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		
		return multipartResolver;
	}
	
	/**
	 * 다운로드를 위한 설정 - 
	 * */
	@Bean
	public BeanNameViewResolver getBeanNameViewResolover() {
		BeanNameViewResolver viewResolover = new BeanNameViewResolver();
		viewResolover.setOrder(0);
		return viewResolover;
	}
}






