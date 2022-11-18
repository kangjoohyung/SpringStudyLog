package kosta.web.mvc.config;

import java.io.IOException;

import org.apache.commons.dbcp.BasicDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import kosta.web.mvc.user.repository.UserMapper;

@Configuration
@EnableTransactionManagement //TransactionManagementConfigurer를 구현한 클래스에 필수선언
@PropertySource("/WEB-INF/spring/appServlet/dbInfo.properties")//placeholder가 내부에 있어야 사용가능
public class PersistenceConfig  implements TransactionManagementConfigurer{//<tx:annotation-driven

	//MyBatis말고 JPA를 쓰더라도 이 환경설정의 틀을 그대로 사용할 수 있다. ->dbInfo, mapper정보들, alias 정도만 거기에맞게 수정
	//경로 설정 : 위의 @Property Sorce 주소, mapper(xml이나 interface기반), aliaspackage(DTO)
	//boot에서는 이 환경설정마저도 알아서 해주고, 경로만 설정하게됨
	//우선 로드해야하는건 static
	
	@Autowired
	private Environment env; //@PropertySource선언된 파일에 있는 모든 key,value의 정보가 저장
	
	@Value("${db.userName}")
	private String username;
	
	@Value("${db.userPass}")
	private String userpass;
	
	/**
	 * PropertySourcesPlaceholderConfigurer를 등록할때 static을 선언해서 다른 빈들보다 더 먼저
	 * 실행될수 있도록 static으로 선언한다. - @Configuration 선언된 객체들보다 먼저 실행된다.
	 * Spring Container가 static으로 선언된 메소드를 가장먼저 bean으로 등록한다.
	 * */
	@Bean
	public static PropertySourcesPlaceholderConfigurer getPlaceholder() {//이것 생성후에 위에 propertysource 사용가능
		System.out.println("getPlaceholder() call........");
		PropertySourcesPlaceholderConfigurer placeHolder = new PropertySourcesPlaceholderConfigurer();
		return placeHolder;
	}
	
	/**
	 * database Connection pool 설정
	 */
	@Bean
	public BasicDataSource getBasicDataSource() {
		System.out.println("getBasicDataSource() call................");
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(env.getProperty("driverName"));
		dataSource.setUrl(env.getProperty("url"));
		dataSource.setUsername(username); //인수에 "c##scott" 식으로 넣어도 됨(유연성 차원에서 따로 설정)
		dataSource.setPassword(userpass);
		dataSource.setMaxActive(10);
		
		return dataSource;
	}
	
	/**
	 * PathMatchingResourcePatternResolver는 디렉토리나 파일들의 경로를 고정하는 것이 아니고
	 * 동적으로 다이나믹하게 설정하기위해서 필요하다.(Ant-style)
	 * */
	@Bean
	public SqlSessionFactoryBean getSqlSessionFactoryBean() throws IOException {
		System.out.println("getSqlSessionFactoryBean() call...........");
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(getBasicDataSource());
		
		PathMatchingResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();
//		Resource [] resource = patternResolver.getResources("classpath:mapper/*Mapper.xml"); //mapper있을때만 사용(없으면 에러남)
//		factoryBean.setMapperLocations(resource);
		factoryBean.setTypeAliasesPackage("kosta.web.mvc.*.dto");
		
		
		PathMatchingResourcePatternResolver patternResolver2 = new PathMatchingResourcePatternResolver();
//		factoryBean.setConfigLocation(patternResolver2.getResource("classpath:SqlMapConfig.xml"));//mapper있을때만 사용(없으면 에러남)
		
		return factoryBean;
		
	}
	
	@Bean
	public SqlSessionTemplate getSqlSessionTemplate()throws Exception { 
		//sqlSession만드는 bean->factory가 있어야 가능->그래서 생성자없이 아래 sqlSession의 인수로 무조건 factory가 필요
		
		System.out.println("getSqlSessionTemplate() call......");
		SqlSessionTemplate sqlSession = new SqlSessionTemplate(getSqlSessionFactoryBean().getObject());
		return sqlSession;
	}
	
	/**
	 * interface기반의 Mapper 등록
	 * */
	@Bean
	public MapperFactoryBean<UserMapper> getUserMapper() throws Exception{
		System.out.println("getUserMapper()......................");
		MapperFactoryBean<UserMapper> userMapper = new MapperFactoryBean<UserMapper>();
		userMapper.setMapperInterface(UserMapper.class);
        userMapper.setSqlSessionFactory(getSqlSessionFactoryBean().getObject());
		return userMapper;
	}
	
	/**
	 * Spring Transacion 설정
	 * */
	@Bean
	public DataSourceTransactionManager getTransactionManager() {
		System.out.println("getTransactionManager() call....................");
		DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
		transactionManager.setDataSource(getBasicDataSource());
		return transactionManager;
	}
	
	
	@Override
	public TransactionManager annotationDrivenTransactionManager() {
		System.out.println("annotationDrivenTransactionManager() call...........");
		return getTransactionManager();
	}

}




