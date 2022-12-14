package references.JPA.boot;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import kosta.mvc.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long> , QuerydslPredicateExecutor<Board>{ 
	//Entity ?ด๋ฆ? : Board, ID ???:Long
	
	/**
	 * ?ธ?๋ก? ? ?ฌ? ๊ธ? ๋ฒํธ๊ฐ? ?ฐ ? ์ฝ๋๋ฅ? ?ญ? ??ค.
	 *  : JPQL ๋ฌธ๋ฒ ? ?ฉ
	 *  : select?ผ?? ๊ด์ฐฎ???ฐ, DML?ผ?(Insert, update, delete), DDL?ผ?? 
	 *  ๋ฐ๋? @Modifying ?ฌ?ฉ ?? (??  ?ฌ?ญ ๋ฐ์??ผ? ?ป)
	 */
	@Query("delete from Board b where b.bno > ?1") //2๊ฐ?์ง?๋ฐฉ๋ฒ-?ฌ์ง??- ?1 / ?ด๋ฆ?- :addr
	@Modifying //select ? ?ธ?๊ณ? ?๋จธ์? 3๊ฐ?์ง? DML๋ฐ? DDL?? ??
	void deleteGrateThan(Long bno);
	
	/**
	 * ๊ธ?๋ฒํธ ?? ? ๋ชฉ์ ?ด?น?? ? ์ฝ๋ ๊ฒ??
	 */
//	@Query("select b from Board b where b.bno>?1 or b.title= ?2 order by bno desc")//๊ฐ์ฒด์ค์ฌ//allias ?์ค๋ ???ฏ->?ด๊ฐ???๊น? ??จ ๊ฑ? ์ฃผ๊ธฐ
	@Query(nativeQuery = true, value="select * from board where bno>?1 or title= ?2 order by bno desc")//Entity์ค์ฌ?ด ??, DB์ค์ฌ? ์ฟผ๋ฆฌ ??ฑ
	List<Board> selectByBnoTitle(Long bno, String title);
	
	/**
	 * ๊ธ?๋ฒํธ, ? ๋ช?, ??ฑ?? ?ด?น?? ? ์ฝ๋ ๊ฒ??
	 */
	@Query("select b from Board b where b.bno=:#{#bo.bno} or b.title=:#{#bo.title} or b.writer=:#{#bo.writer}") //parameter๊ฐ? ๊ฐ์ฒด ?๊ฐ๋ฟ?ด?ผ ?ด๋ฆ์ ์ง?? ?ด?ผ?จ
	List<Board> selectParamBoard(@Param("bo")Board board);
	////////////////////////////////////////////////////
	/**
	 * select ? ?ฉ findBy๋ก? ???? ๋ฉ์? ๊ธฐ๋ฐ? ์ฟผ๋ฆฌ ??ฑ - Query Method
	 * https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods
	 */
	
	/**
	 * ? ?ฌ? ๊ธ?๋ฒํธ๋ณด๋ค ??? ?ซ?? ? ์ฝ๋๋ฅ? ๊ฒ??
	 */
	List<Board> findByBnoLessThan(Long bno);
	
	
}
