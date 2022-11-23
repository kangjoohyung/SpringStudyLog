package kosta.web.mvc.model.user;

import kosta.web.mvc.model.dto.UserDTO;

public interface UserDAO {
  /**
   * 로그인 기능
   * */
	UserDTO loginCheck(UserDTO userDTO);
}
