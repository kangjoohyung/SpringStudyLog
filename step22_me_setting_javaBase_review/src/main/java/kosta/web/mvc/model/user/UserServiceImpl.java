package kosta.web.mvc.model.user;

import org.springframework.stereotype.Service;

import kosta.web.mvc.controller.MyException;
import kosta.web.mvc.model.dto.UserDTO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

	private final UserDAO userDAO;
	
	@Override
	public UserDTO loginCheck(UserDTO userDTO) throws MyException {
		try {
			userDTO=userDAO.loginCheck(userDTO);
			if(userDTO==null) throw new MyException("정보가 없습니다");
		}catch(Exception e) {
			throw new MyException("오류가 발생했습니다");
		}
		return userDTO;
	}

}
