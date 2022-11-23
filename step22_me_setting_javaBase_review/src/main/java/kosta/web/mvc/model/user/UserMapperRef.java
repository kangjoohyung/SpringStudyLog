package kosta.web.mvc.model.user;

import org.apache.ibatis.annotations.Select;

import kosta.web.mvc.model.dto.UserDTO;

/**
 * xml��ſ� interface����� Mapper ����� 
 *   : ����Ϸ��� springBean������ Mapper�� ����ؾ��Ѵ�. 
 * */
public interface UserMapperRef {

	@Select("select member_id as user_id, member_password as pwd, member_name as name from member where member_id=#{userId} and member_password=#{pwd}")
	UserDTO loginCheck(UserDTO userDTO);
}
