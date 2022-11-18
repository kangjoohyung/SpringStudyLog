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
@EnableTransactionManagement //TransactionManagementConfigurer�� ������ Ŭ������ �ʼ�����
@PropertySource("/WEB-INF/spring/appServlet/dbInfo.properties")//placeholder�� ���ο� �־�� ��밡��
public class PersistenceConfig  implements TransactionManagementConfigurer{//<tx:annotation-driven

	//MyBatis���� JPA�� ������ �� ȯ�漳���� Ʋ�� �״�� ����� �� �ִ�. ->dbInfo, mapper������, alias ������ �ű⿡�°� ����
	//��� ���� : ���� @Property Sorce �ּ�, mapper(xml�̳� interface���), aliaspackage(DTO)
	//boot������ �� ȯ�漳�������� �˾Ƽ� ���ְ�, ��θ� �����ϰԵ�
	//�켱 �ε��ؾ��ϴ°� static
	
	@Autowired
	private Environment env; //@PropertySource����� ���Ͽ� �ִ� ��� key,value�� ������ ����
	
	@Value("${db.userName}")
	private String username;
	
	@Value("${db.userPass}")
	private String userpass;
	
	/**
	 * PropertySourcesPlaceholderConfigurer�� ����Ҷ� static�� �����ؼ� �ٸ� ��麸�� �� ����
	 * ����ɼ� �ֵ��� static���� �����Ѵ�. - @Configuration ����� ��ü�麸�� ���� ����ȴ�.
	 * Spring Container�� static���� ����� �޼ҵ带 ������� bean���� ����Ѵ�.
	 * */
	@Bean
	public static PropertySourcesPlaceholderConfigurer getPlaceholder() {//�̰� �����Ŀ� ���� propertysource ��밡��
		System.out.println("getPlaceholder() call........");
		PropertySourcesPlaceholderConfigurer placeHolder = new PropertySourcesPlaceholderConfigurer();
		return placeHolder;
	}
	
	/**
	 * database Connection pool ����
	 */
	@Bean
	public BasicDataSource getBasicDataSource() {
		System.out.println("getBasicDataSource() call................");
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(env.getProperty("driverName"));
		dataSource.setUrl(env.getProperty("url"));
		dataSource.setUsername(username); //�μ��� "c##scott" ������ �־ ��(������ �������� ���� ����)
		dataSource.setPassword(userpass);
		dataSource.setMaxActive(10);
		
		return dataSource;
	}
	
	/**
	 * PathMatchingResourcePatternResolver�� ���丮�� ���ϵ��� ��θ� �����ϴ� ���� �ƴϰ�
	 * �������� ���̳����ϰ� �����ϱ����ؼ� �ʿ��ϴ�.(Ant-style)
	 * */
	@Bean
	public SqlSessionFactoryBean getSqlSessionFactoryBean() throws IOException {
		System.out.println("getSqlSessionFactoryBean() call...........");
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(getBasicDataSource());
		
		PathMatchingResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();
//		Resource [] resource = patternResolver.getResources("classpath:mapper/*Mapper.xml"); //mapper�������� ���(������ ������)
//		factoryBean.setMapperLocations(resource);
		factoryBean.setTypeAliasesPackage("kosta.web.mvc.*.dto");
		
		
		PathMatchingResourcePatternResolver patternResolver2 = new PathMatchingResourcePatternResolver();
//		factoryBean.setConfigLocation(patternResolver2.getResource("classpath:SqlMapConfig.xml"));//mapper�������� ���(������ ������)
		
		return factoryBean;
		
	}
	
	@Bean
	public SqlSessionTemplate getSqlSessionTemplate()throws Exception { 
		//sqlSession����� bean->factory�� �־�� ����->�׷��� �����ھ��� �Ʒ� sqlSession�� �μ��� ������ factory�� �ʿ�
		
		System.out.println("getSqlSessionTemplate() call......");
		SqlSessionTemplate sqlSession = new SqlSessionTemplate(getSqlSessionFactoryBean().getObject());
		return sqlSession;
	}
	
	/**
	 * interface����� Mapper ���
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
	 * Spring Transacion ����
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




