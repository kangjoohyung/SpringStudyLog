package kosta.web.mvc.model.user;

import kosta.web.mvc.controller.MyException;
import kosta.web.mvc.model.dto.UserDTO;

public interface UserService {
	/**
	 * 로그인 체크
	 * @throws MyException 
	 * */
   UserDTO loginCheck(UserDTO userDTO) throws MyException;
}
