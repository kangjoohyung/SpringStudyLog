package kosta.mvc.model.dao;

import java.util.List;

import kosta.mvc.model.vo.Authority;

public interface AuthoritiesDAO {

	/**
	 * ����� ���� ���
	 * (�� USER(���̵�)�� �������� ������ ���� �� �ִ�.
	 * */
	int insertAuthority(Authority authority);
	
	/**
	 * id�� �ش��ϴ� ���� �˻�.
	 * */
	List<Authority> selectAuthorityByUserName(String userName);
}






