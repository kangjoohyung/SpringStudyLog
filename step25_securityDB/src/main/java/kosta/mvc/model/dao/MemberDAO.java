package kosta.mvc.model.dao;

import kosta.mvc.model.vo.Member;

public interface MemberDAO {
  /**
   *  ȸ������
   * */
	int insertMember(Member member);
	
	
	/**
	 * ID�� �ش��ϴ� ȸ������ �˻�
	 * */
	Member selectMemberById(String id);
}
