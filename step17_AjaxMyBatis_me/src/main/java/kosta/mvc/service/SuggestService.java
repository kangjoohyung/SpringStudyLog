package kosta.mvc.service;

import java.util.List;

public interface SuggestService {
	/**
	 * 전달된 word로 시작하는 단어들 검색
	 */
	List<String> selectByWord(String word);
}
