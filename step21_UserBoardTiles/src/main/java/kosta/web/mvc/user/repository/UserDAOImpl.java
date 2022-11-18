package kosta.web.mvc.user.repository;

import javax.annotation.PostConstruct;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kosta.web.mvc.user.dto.UserDTO;
import lombok.RequiredArgsConstructor;

@Repository //����
@RequiredArgsConstructor
public class UserDAOImpl implements UserDAO {
	
	private final SqlSession session;
	
	private UserMapper mapper;
	
	@PostConstruct //��ü�� ������ ���Ŀ� �ؾ��� ���� ������ �����ϸ� �ڵ����� �޼ҵ尡 ȣ��ȴ�.
	public void init() {
		 mapper = session.getMapper(UserMapper.class);
	}

	@Override
	public UserDTO loginCheck(UserDTO userDTO) {
		return mapper.loginCheck(userDTO);
	}

}
