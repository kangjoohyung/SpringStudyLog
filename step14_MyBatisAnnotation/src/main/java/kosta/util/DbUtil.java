package kosta.util;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder; 

/**
 * MyBatis ORM설정
 *
 */
public class DbUtil {
	private static SqlSessionFactory factory; 
	//한번만 만들고 계속 유지하는 것이 가장 좋다(application살아있는동안)
	//싱글턴 패턴 or static 싱글턴 패턴 사용
	
	static {
		try {
			String resource = "config/SqlMapConfig.xml";
	
			//문자단위로 읽기기능 하는것이 자바IO의 Reader, 즉 classPath기준으로 읽어옴
			Reader reader = Resources.getResourceAsReader(resource); 
			//(인수에 SqlMapConfig.xml을 넣기)
			

			//SqlSessionFactoryBuilder를 이용해서 SqlSessionFactory를 생성한다.
			factory = new SqlSessionFactoryBuilder().build(reader);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}//static끝
	
	
	//try에 인수로 세션을 넣게되면 finally에서 세션닫기를 안써도 try끝날때 자동으로 닫아준다. ->어디메소드?? 세션사용은 DAO
	/**
	 * SqlSession객체를 SqlSessionFactory로부터 생성해서 리턴해주는 메소드 작성
	 * : SqlSession은 CRUD작업을 할 수 있는 메소드를 제공
	 * commit or rollback을 할 수 있는 transaction관련 메소드 제공
	 * 자동 commit이 아니기 때문에 DML(insert, update, delete)작업인 경우는
	 * 반드시 commit or rollback을 해줘야한다
	 * 
	 * SqlSession은 사용후 반드시 닫기해준다
	 */
	public static SqlSession getSession() {
		return factory.openSession(); //DAO에서 호출해서 사용
	}
	
	
	/**
	 * sessionClose 인수 세션만
	 * 닫기기능(select인경우) -> 단일트랜젝션
	 */
	public static void sessionClose(SqlSession session) {
		if(session!=null) session.close();  //session을 사용했다면 닫기(null아니면 사용했다는뜻)
	}
	
	/**
	 * sessionClose 인수 2개짜리
	 * 닫기기능(DML인경우) -> 이어서 사용하는 트랜젝션
	 * : 닫기전에 commit or rollback을 한다.
	 * @param : boolean이 true일대 commit, false일때 rollback
	 */
	public static void sessionClose(SqlSession session, boolean state) {
		
		if(session!=null) {
			if(state) session.commit();
			else session.rollback();
			
			session.close();
		}
	}
	
	public static void main(String[] args) {
		System.out.println("MyBatis start--------------");
		SqlSession session=DbUtil.getSession();
		System.out.println("session="+session);
		
	}
}
