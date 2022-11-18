package kosta.web.mvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;

@Configuration //
public class TilesConfig {
	@Bean
	public UrlBasedViewResolver getViewResolver() {
		UrlBasedViewResolver viewResolover = new UrlBasedViewResolver();
		viewResolover.setOrder(1);
		viewResolover.setViewClass(TilesView.class);
		return viewResolover;
	}
	
	@Bean
	public TilesConfigurer getConfigurer() {
		TilesConfigurer configurer  =new TilesConfigurer();
		configurer.setDefinitions(new String[] {"/WEB-INF/tiles/tiles.xml"});
		return configurer;
	}
}
