package sample10tea;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

//@Component  //id="boadMyBatisDAOImpl"
@Repository
public class BoadMyBatisDAOImpl implements BoardDAO {

	public BoadMyBatisDAOImpl() {
		System.out.println("BoadMyBatisDAOImpl() 생성자 호출...");
	}
	
	@Override
	public void select() {
		System.out.println("BoadMyBatisDAOImpl의 select() 호출됨.");

	}

}
