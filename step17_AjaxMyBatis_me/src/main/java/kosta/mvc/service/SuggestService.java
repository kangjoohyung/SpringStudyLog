package kosta.mvc.service;

import java.util.List;

public interface SuggestService {
	/**
	 * ���޵� word�� �����ϴ� �ܾ�� �˻�
	 */
	List<String> selectByWord(String word);
}
