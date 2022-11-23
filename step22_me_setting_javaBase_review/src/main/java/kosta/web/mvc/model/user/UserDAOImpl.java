package kosta.web.mvc.model.user;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kosta.web.mvc.model.dto.UserDTO;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserDAOImpl implements UserDAO {

	private final SqlSession session;
	private UserMapperRef mapper;
	
	@Override
	public UserDTO loginCheck(UserDTO userDTO) {
		mapper=session.getMapper(UserMapperRef.class);
		return mapper.loginCheck(userDTO);
	}

}
