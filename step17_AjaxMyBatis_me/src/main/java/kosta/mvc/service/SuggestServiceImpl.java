package kosta.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kosta.mvc.dao.SuggestDAO;

@Service //생성
public class SuggestServiceImpl implements SuggestService {

	@Autowired
	private SuggestDAO suggestDAO; //주입받으려면 Autowired나 final
	
	@Override
	public List<String> selectByWord(String word) {
		//dao호출
		return suggestDAO.selectByWord(word);
	}

}
