package kosta.mvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import kosta.mvc.domain.FreeBoard;

public interface FreeBoardRepository extends JpaRepository<FreeBoard, Long>, QuerydslPredicateExecutor<FreeBoard>{
	/**
	 * 글번호에 해당하는 조회수 증가
	 */
	@Query("update FreeBoard b set b.readnum=b.readnum+1 where b.bno=?1")
	@Modifying //dml, ddl에선 필수
	void updateReadnum(Long bno);
	
}
