package sample10tea;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

//@Component //id="boardDAOImpl"  id는 클래스의 이름의 첫글자만 소문자
@Repository
public class BoardDAOImpl implements BoardDAO {//<bean class="BoardDAOImpl" id="boardDAOImpl"

	public BoardDAOImpl() {
		System.out.println("BoardDAOImpl() 생성자 호출됨...");
	}
	
	@Override
	public void select() {
		System.out.println("BoardDAOImpl의 select() 호출됨...");

	}

}
