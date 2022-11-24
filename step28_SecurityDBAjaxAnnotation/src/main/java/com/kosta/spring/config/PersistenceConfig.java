package com.kosta.spring.config;

import java.io.IOException;

import org.apache.commons.dbcp.BasicDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:config/jdbc.properties")
public class PersistenceConfig implements TransactionManagementConfigurer{
    
	@Autowired
	private Environment env; //@PropertySoruce 선언된 파일에 있는 모든 key와 value정보가 저장(주입된다.)
	
	@Value("${JDBC.Username}")
	private String username;
	
	@Value("${JDBC.Password}")
	private String password;
	
	/**
	 * PropertySourcesPlaceholderConfigurer 등록
	 *     :static메소드로 선언해서 다른 빈보다 먼저 등록될 수 있도록 한다.
	 *     :@Configuration를 선언한 객체보다도 먼저 bean으로 등록될 수 있다.
	 *     : static을 붙여주면 해당 빈들은 스프링컨테이너의 라이프사이클 매우 초기단계에
	 *      다른 빈들보다 먼저 등록된다.
	 * */
	@Bean
	public static PropertySourcesPlaceholderConfigurer getPropertyConfigurer() {
		PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
				
		return configurer;
	}
	
	/**
	 * BasicDataSource 등록
	 * */
    @Bean
    public BasicDataSource getDataSoruce() {
    	BasicDataSource dataSource = new BasicDataSource();
    	dataSource.setDriverClassName(env.getProperty("JDBC.Driver"));
    	dataSource.setUrl(env.getProperty("JDBC.ConnectionURL"));
    	
    	dataSource.setUsername(username);
    	dataSource.setPassword(password);
    	dataSource.setMaxActive(10);
    	
    	return dataSource;
    }
	
    
    /**
     * SqlSessionFactoryBean등록
     * PathMatchingResourcePatternResolver 클래스는 디렉토리, 파일이름 등의
     * 경로를 동적으로 설정할 수 있도록 돕는 클래스이다.
     *   - Ant-style Pattern- 와일드카드를 사용할 수 있다.
     * */
    @Bean
    public SqlSessionFactoryBean getSqlSessionFactory() throws IOException {
    	SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
    	sqlSessionFactory.setDataSource(getDataSoruce());
    	
    	PathMatchingResourcePatternResolver pathMatching = new PathMatchingResourcePatternResolver();
    	
    	sqlSessionFactory.setMapperLocations(pathMatching.getResources("classpath:sql/*Mapper.xml"));
    	//sqlSessionFactory.setTypeAliasesPackage("com.kosta.spring.model"); // 클래스이름의  첫글자만 대문자
    	
    	//MapperLocations와 마찬가지로 resource를 인수로 받는다.
    	sqlSessionFactory.setConfigLocation(new PathMatchingResourcePatternResolver().getResource("classpath:config/SqlMapConfig.xml"));
    	
    	return sqlSessionFactory;
    }
    
    
    /**
     * SqlSessionTemplate 등록
     * */
    @Bean
    public SqlSessionTemplate getSqlSession() throws Exception{
    	
    	//getSqlSessionFactory는 spring.factory이고 인수 타입은 ibatis.session이므로 getObject를 통해 타입을 맞춰준다.
    	SqlSessionTemplate sqlSession = new SqlSessionTemplate(getSqlSessionFactory().getObject());
    	
    	return sqlSession;
    }
    

	/**
	 *  TransactionManager 객체를 등록
	 * */
	@Bean
	public DataSourceTransactionManager getTransactionManager() {
		DataSourceTransactionManager transactionManager = 
				new DataSourceTransactionManager();
		
		transactionManager.setDataSource(getDataSoruce()); //DataSource전달
		return transactionManager;
	}
    
	@Override
	public TransactionManager annotationDrivenTransactionManager() {
		// TODO Auto-generated method stub
		return getTransactionManager();
	}
    
	
	
}
