package kosta.web.mvc.user;

import kosta.web.mvc.user.dto.UserDTO;

public interface UserService {
	/**
	 * �α��� üũ
	 * */
   UserDTO loginCheck(UserDTO userDTO);
}
