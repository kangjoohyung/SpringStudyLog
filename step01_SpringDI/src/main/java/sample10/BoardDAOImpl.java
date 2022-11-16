package sample10;

import org.springframework.stereotype.Component;

@Component //id="boardDAOImpl" id는 클래스의 이름의 첫글자만 소문자
public class BoardDAOImpl implements BoardDAO {//<bean class="BoardDAOImpl" id=???

	public BoardDAOImpl() { 
		super();
		System.out.println("BoardDAOImpl()");
	}

	@Override
	public void select() {
		System.out.println("BoardDAOImpl()의 select()");

	}

}
