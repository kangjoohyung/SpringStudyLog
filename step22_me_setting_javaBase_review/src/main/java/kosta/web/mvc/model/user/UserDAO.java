package kosta.web.mvc.model.user;

import kosta.web.mvc.model.dto.UserDTO;

public interface UserDAO {
  /**
   * �α��� ���
   * */
	UserDTO loginCheck(UserDTO userDTO);
}
