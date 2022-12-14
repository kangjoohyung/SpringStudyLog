package references.sample;

import javax.annotation.PostConstruct;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kosta.web.mvc.model.dto.UserDTO;
import kosta.web.mvc.model.user.UserDAO;
import lombok.RequiredArgsConstructor;

@Repository //생성
@RequiredArgsConstructor
public class UserDAOImpl implements UserDAO {
	
	private final SqlSession session;
	
	private UserMapper mapper;
	
	@PostConstruct //객체가 생성이 된후에 해야할 일이 있을때 선언하면 자동으로 메소드가 호출된다.
	public void init() {
		System.out.println("UserDAOImpl init.................");
		// mapper = session.getMapper(UserMapper.class);
	}

	@Override
	public UserDTO loginCheck(UserDTO userDTO) {
		mapper = session.getMapper(UserMapper.class);
		return mapper.loginCheck(userDTO);
	}

}
