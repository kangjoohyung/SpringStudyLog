package kosta.mvc.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository //����
@RequiredArgsConstructor //�����ڸ� ���� ���� -> final
public class SuggestDAOImpl implements SuggestDAO {

	private final SqlSession session; //����(bean����)->autowired�� final���
	
	@Override
	public List<String> selectByWord(String word) {
		
		//try, finally, �ݱ� ����� ��� ���� ���� ������
		
		System.out.println("session="+session);
		List<String> list=session.selectList("suggestMapper.selectByWord", word);
		
		return list;
	}

}
