package kosta.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kosta.mvc.dao.SuggestDAO;

@Service //����
public class SuggestServiceImpl implements SuggestService {

	@Autowired
	private SuggestDAO suggestDAO; //���Թ������� Autowired�� final
	
	@Override
	public List<String> selectByWord(String word) {
		//daoȣ��
		return suggestDAO.selectByWord(word);
	}

}
