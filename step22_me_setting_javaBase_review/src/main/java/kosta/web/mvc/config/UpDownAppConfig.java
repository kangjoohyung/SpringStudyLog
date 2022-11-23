package kosta.web.mvc.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.view.BeanNameViewResolver;

/**
 * ���ε�-�ٿ�ε弳��
 *
 */
@Configuration
public class UpDownAppConfig {
   /**
    * ����ó�� bean���
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
	 * ���ε� ������Ʈ �� ���
	 *   : �߿�  - �޼ҵ��̸��� multipartResolver �̴�.
	 * */
	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		
		return multipartResolver;
	}
	
	/**
	 * �ٿ�ε带 ���� ���� - 
	 * */
	@Bean
	public BeanNameViewResolver getBeanNameViewResolover() {
		BeanNameViewResolver viewResolover = new BeanNameViewResolver();
		viewResolover.setOrder(0);
		return viewResolover;
	}
}






