package kosta.mvc.dao;

import java.util.List;

public interface SuggestDAO {
	/**
	 * 인수로 전달된 word로 시작하는 단어들을 검색
	 */
	List<String> selectByWord(String word);
}
