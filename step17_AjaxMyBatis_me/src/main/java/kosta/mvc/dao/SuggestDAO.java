package kosta.mvc.dao;

import java.util.List;

public interface SuggestDAO {
	/**
	 * �μ��� ���޵� word�� �����ϴ� �ܾ���� �˻�
	 */
	List<String> selectByWord(String word);
}
