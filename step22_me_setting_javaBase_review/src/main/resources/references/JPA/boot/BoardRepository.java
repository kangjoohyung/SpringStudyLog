package references.JPA.boot;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import kosta.mvc.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long> , QuerydslPredicateExecutor<Board>{ 
	//Entity ?���? : Board, ID ???��:Long
	
	/**
	 * ?��?���? ?��?��?�� �? 번호�? ?�� ?��코드�? ?��?��?��?��.
	 *  : JPQL 문법 ?��?��
	 *  : select?��?��?�� 괜찮???��, DML?��?��(Insert, update, delete), DDL?��?��?�� 
	 *  반드?�� @Modifying ?��?�� ?��?�� (?��?�� ?��?�� 반영?��?��?�� ?��)
	 */
	@Query("delete from Board b where b.bno > ?1") //2�?�?방법-?���??��- ?1 / ?���?- :addr
	@Modifying //select ?��?��?���? ?��머�? 3�?�? DML�? DDL?��?�� ?��?��
	void deleteGrateThan(Long bno);
	
	/**
	 * �?번호 ?��?�� ?��목에 ?��?��?��?�� ?��코드 �??��
	 */
//	@Query("select b from Board b where b.bno>?1 or b.title= ?2 order by bno desc")//객체중심//allias ?��줘도 ?��?��?��->?���??��?���? ?��?�� �? 주기
	@Query(nativeQuery = true, value="select * from board where bno>?1 or title= ?2 order by bno desc")//Entity중심?�� ?��?��, DB중심?�� 쿼리 ?��?��
	List<Board> selectByBnoTitle(Long bno, String title);
	
	/**
	 * �?번호, ?���?, ?��?��?��?�� ?��?��?��?�� ?��코드 �??��
	 */
	@Query("select b from Board b where b.bno=:#{#bo.bno} or b.title=:#{#bo.title} or b.writer=:#{#bo.writer}") //parameter�? 객체 ?��개뿐?��?�� ?��름을 �??��?��?��?��
	List<Board> selectParamBoard(@Param("bo")Board board);
	////////////////////////////////////////////////////
	/**
	 * select ?��?�� findBy�? ?��?��?��?�� 메소?�� 기반?�� 쿼리 ?��?�� - Query Method
	 * https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods
	 */
	
	/**
	 * ?��?��?�� �?번호보다 ?��?? ?��?��?�� ?��코드�? �??��
	 */
	List<Board> findByBnoLessThan(Long bno);
	
	
}
