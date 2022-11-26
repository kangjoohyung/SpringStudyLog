package references.JPA.boot;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import kosta.mvc.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long> , QuerydslPredicateExecutor<Board>{ 
	//Entity ?´ë¦? : Board, ID ???…:Long
	
	/**
	 * ?¸?ˆ˜ë¡? ? „?‹¬?œ ê¸? ë²ˆí˜¸ê°? ?° ? ˆì½”ë“œë¥? ?‚­? œ?•œ?‹¤.
	 *  : JPQL ë¬¸ë²• ? ?š©
	 *  : select?¼?•Œ?Š” ê´œì°®???°, DML?¼?•Œ(Insert, update, delete), DDL?¼?•Œ?Š” 
	 *  ë°˜ë“œ?‹œ @Modifying ?‚¬?š© ?•„?š” (?ˆ˜? • ?‚¬?•­ ë°˜ì˜?•˜?¼?Š” ?œ»)
	 */
	@Query("delete from Board b where b.bno > ?1") //2ê°?ì§?ë°©ë²•-?¬ì§??…˜- ?1 / ?´ë¦?- :addr
	@Modifying //select ? œ?™¸?•˜ê³? ?‚˜ë¨¸ì? 3ê°?ì§? DMLë°? DDL?—?„œ ?•„?š”
	void deleteGrateThan(Long bno);
	
	/**
	 * ê¸?ë²ˆí˜¸ ?˜?Š” ? œëª©ì— ?•´?‹¹?•˜?Š” ? ˆì½”ë“œ ê²??ƒ‰
	 */
//	@Query("select b from Board b where b.bno>?1 or b.title= ?2 order by bno desc")//ê°ì²´ì¤‘ì‹¬//allias ?•ˆì¤˜ë„ ?˜?Š”?“¯->?‚´ê°??•˜?‹ˆê¹? ?•ˆ?¨ ê±? ì£¼ê¸°
	@Query(nativeQuery = true, value="select * from board where bno>?1 or title= ?2 order by bno desc")//Entityì¤‘ì‹¬?´ ?•„?‹Œ, DBì¤‘ì‹¬?˜ ì¿¼ë¦¬ ?‘?„±
	List<Board> selectByBnoTitle(Long bno, String title);
	
	/**
	 * ê¸?ë²ˆí˜¸, ? œëª?, ?‘?„±??— ?•´?‹¹?•˜?Š” ? ˆì½”ë“œ ê²??ƒ‰
	 */
	@Query("select b from Board b where b.bno=:#{#bo.bno} or b.title=:#{#bo.title} or b.writer=:#{#bo.writer}") //parameterê°? ê°ì²´ ?•œê°œë¿?´?¼ ?´ë¦„ì„ ì§?? •?•´?•¼?•¨
	List<Board> selectParamBoard(@Param("bo")Board board);
	////////////////////////////////////////////////////
	/**
	 * select ? „?š© findByë¡? ?‹œ?‘?•˜?Š” ë©”ì†Œ?“œ ê¸°ë°˜?˜ ì¿¼ë¦¬ ?‘?„± - Query Method
	 * https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods
	 */
	
	/**
	 * ? „?‹¬?œ ê¸?ë²ˆí˜¸ë³´ë‹¤ ?‘?? ?ˆ«??˜ ? ˆì½”ë“œë¥? ê²??ƒ‰
	 */
	List<Board> findByBnoLessThan(Long bno);
	
	
}
