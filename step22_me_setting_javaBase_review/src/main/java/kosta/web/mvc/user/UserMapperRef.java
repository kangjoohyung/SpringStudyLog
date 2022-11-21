package kosta.web.mvc.user;

import org.apache.ibatis.annotations.Select;

import kosta.web.mvc.user.dto.UserDTO;

/**
 * xml��ſ� interface����� Mapper ����� 
 *   : ����Ϸ��� springBean������ Mapper�� ����ؾ��Ѵ�. 
 * */
public interface UserMapperRef {

	@Select("select user_id ,pwd, name from member where user_id=#{userId} and pwd=#{pwd}")
	UserDTO loginCheck(UserDTO userDTO);
}
