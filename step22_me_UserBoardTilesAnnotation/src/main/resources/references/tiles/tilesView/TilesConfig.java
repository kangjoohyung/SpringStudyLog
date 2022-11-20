package references.tiles.tilesView;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;

//Configurable�� ȥ�� ����
@Configuration //ȯ�漳���� ���� Ŭ������� ��, @Bean�� ����� �޼ҵ尡 ȣ��ǰ� �����ϴ� ��ü�� bean���� ���
public class TilesConfig {

	/**
	 * Tiles ���-viewResolver
	 */
	@Bean
	public UrlBasedViewResolver getViewResolver() {
		UrlBasedViewResolver viewResolver=new UrlBasedViewResolver();
		viewResolver.setOrder(1);
		viewResolver.setViewClass(TilesView.class);
		
		System.out.println("TilesConfiguration�� viewResolver()");
		
		return viewResolver;
	}
	
	
	/**
	 * TilesConfigurer �μ��� �迭(����Ʈ?)
	 */
	@Bean
	public TilesConfigurer getTilesConfigurer() {
		TilesConfigurer configurer=new TilesConfigurer();
		
		configurer.setDefinitions(new String[] {"/WEB-INF/tiles/tiles.xml"});//�μ��� �迭(xml�������� list����)
		System.out.println("TilesConfiguration�� getTilesConfigurer()");
		
		return configurer;
	}
}
