package kosta.mvc.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository //생성
@RequiredArgsConstructor //생성자를 통해 주입 -> final
public class SuggestDAOImpl implements SuggestDAO {

	private final SqlSession session; //주입(bean설정)->autowired나 final사용
	
	@Override
	public List<String> selectByWord(String word) {
		
		//try, finally, 닫기 등등의 모든 과정 전부 생략됨
		
		System.out.println("session="+session);
		List<String> list=session.selectList("suggestMapper.selectByWord", word);
		
		return list;
	}

}
