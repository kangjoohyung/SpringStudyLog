package kosta.web.mvc.model.user;

import org.apache.ibatis.annotations.Select;

import kosta.web.mvc.model.dto.UserDTO;

/**
 * xml대신에 interface기반의 Mapper 만들기 
 *   : 사용하려면 springBean설정에 Mapper를 등록해야한다. 
 * */
public interface UserMapperRef {

	@Select("select member_id as user_id, member_password as pwd, member_name as name from member where member_id=#{userId} and member_password=#{pwd}")
	UserDTO loginCheck(UserDTO userDTO);
}
