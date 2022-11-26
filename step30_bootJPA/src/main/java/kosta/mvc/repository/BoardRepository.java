package kosta.mvc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import kosta.mvc.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long> , QuerydslPredicateExecutor<Board>{ 
	//Entity 이름 : Board, ID 타입:Long
	
	/**
	 * 인수로 전달된 글 번호가 큰 레코드를 삭제한다.
	 *  : JPQL 문법 적용
	 *  : select일때는 괜찮은데, DML일때(Insert, update, delete), DDL일때는 
	 *  반드시 @Modifying 사용 필요 (수정 사항 반영하라는 뜻)
	 */
	@Query("delete from Board b where b.bno > ?1") //2가지방법-포지션- ?1 / 이름- :addr
	@Modifying //select 제외하고 나머지 3가지 DML및 DDL에서 필요
	void deleteGrateThan(Long bno);
	
	/**
	 * 글번호 또는 제목에 해당하는 레코드 검색
	 */
//	@Query("select b from Board b where b.bno>?1 or b.title= ?2 order by bno desc")//객체중심//allias 안줘도 되는듯->내가하니까 안됨 걍 주기
	@Query(nativeQuery = true, value="select * from board where bno>?1 or title= ?2 order by bno desc")//Entity중심이 아닌, DB중심의 쿼리 작성
	List<Board> selectByBnoTitle(Long bno, String title);
	
	/**
	 * 글번호, 제목, 작성자에 해당하는 레코드 검색
	 */
	@Query("select b from Board b where b.bno=:#{#bo.bno} or b.title=:#{#bo.title} or b.writer=:#{#bo.writer}") //parameter가 객체 한개뿐이라 이름을 지정해야함
	List<Board> selectParamBoard(@Param("bo")Board board);
	////////////////////////////////////////////////////
	/**
	 * select 전용 findBy로 시작하는 메소드 기반의 쿼리 작성 - Query Method
	 * https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods
	 */
	
	/**
	 * 전달된 글번호보다 작은 숫자의 레코드를 검색
	 */
	List<Board> findByBnoLessThan(Long bno);
	
	
}
