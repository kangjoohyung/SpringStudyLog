package kosta.mvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;

//Configurable과 혼동 주의
@Configuration //환경설정을 돕는 클래스라는 뜻, @Bean이 선언된 메소드가 호출되고 리턴하는 객체는 bean으로 등록
public class TilesConfiguration {

	/**
	 * Tiles 등록-viewResolver
	 */
	@Bean
	public UrlBasedViewResolver viewResolver() {
		UrlBasedViewResolver viewResolver=new UrlBasedViewResolver();
		viewResolver.setOrder(1);
		viewResolver.setViewClass(TilesView.class);
		
		System.out.println("TilesConfiguration의 viewResolver()");
		
		return viewResolver;
	}
	
	
	/**
	 * TilesConfigurer 인수는 배열(리스트?)
	 */
	@Bean
	public TilesConfigurer getTilesConfigurer() {
		TilesConfigurer config=new TilesConfigurer();
		
		config.setDefinitions(new String[] {"/WEB-INF/tiles/tiles.xml"});//인수가 배열(xml설정에서 list였음)
		System.out.println("TilesConfiguration의 getTilesConfigurer()");
		
		return config;
	}
}
