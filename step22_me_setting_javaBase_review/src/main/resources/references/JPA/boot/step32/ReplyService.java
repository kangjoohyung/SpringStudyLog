package references.JPA.boot.step32;

import kosta.mvc.domain.Reply;

public interface ReplyService {
  /**
   * 등록하기
   * */
	void insert(Reply reply);
	
	/**
	 * 삭제
	 * */
	void delete(Long id);
}
