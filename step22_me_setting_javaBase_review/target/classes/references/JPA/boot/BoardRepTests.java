package references.JPA.boot;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.annotation.Commit;

import com.querydsl.core.BooleanBuilder;

import kosta.mvc.domain.Board;
import kosta.mvc.domain.QBoard;
import kosta.mvc.repository.BoardRepository;
import lombok.RequiredArgsConstructor;

@SpringBootTest
@Transactional
@RequiredArgsConstructor//?˜¤ë¥˜ë‚¨
@Commit
public class BoardRepTests {
	
	@Autowired // ì£¼ì… //final ?“°?‹ˆê¹? ?˜¤ë¥˜ë‚¨
	private BoardRepository rep;
	
	@Test
	public void aa() {
		System.out.println("rep="+rep);

	}

	/**
	 * ?“±ë¡?
	 */
	@Test
	public void insert(){
		for(int i=1; i<=200; i++) {
			rep.save(new Board(null, "? œëª?"+i, "User"+i, "?‚´?š©"+i, null, null));
		}
	}
	
	/**
	 * ? „ì²´ê??ƒ‰
	 */
	@Test
	public void selectAll() {
		List<Board> list=rep.findAll();
//		for(Board b:list) {
//			System.out.println(b);
//		}
		
		//?Œ?‹¤?‹?œ¼ë¡? ë°˜ë³µë¬? ?‚¬?š©
		list.forEach(b->System.out.println(b));
	}
	
	/**
	 * idë¡? ê²??ƒ‰(pkë¡? ê²??ƒ‰)
	 */
	@Test
	public void SelectByBno() {
		
		//Optional?? java.util?— ì¶”ê??œ ê°ì²´ë¡? null ?—¬ë¶?ë¥? ì²´í¬?•˜ì§? ?•Š?•„?„ ?˜?„ë¡? 
		//ê´?? ¨?œ ë©”ì†Œ?“œë¥? ? œê³µí•œ?‹¤.
		Optional<Board> op=rep.findById(170L);
		Board board=op.orElse(null);
		System.out.println(board);
	}
	
	/**
	 * ?ˆ˜? •?•˜ê¸?
	 */
	@Test
	public void update() { //?„œë¹„ìŠ¤ ë©”ì†Œ?“œ
		Board board=rep.findById(50L).orElse(null);
		board.setContent("50ë²? ê²Œì‹œë¬? ?ˆ˜? •");
		board.setWriter("?¥?¬? •");
	}
		
	/**
	 * ?‚­? œ?•˜ê¸?
	 */
	@Test
	public void delete() {
		rep.deleteById(30L);
	}
	//////////////////////////////////////
	/**
	 * 1. JPQL(ê°ì²´ì¤‘ì‹¬?œ¼ë¡? ì¿¼ë¦¬ ?‘?„±) ë¬¸ë²•?„ ?´?š©?•´?„œ ?‚¬?š©? ? •?˜ ì¿¼ë¦¬ë¥? ?‘?„±?•  ?ˆ˜ ?ˆ?‹¤
	 *    spring-data-jpa?—?„œ JPQLë¬¸ë²•?„ ? œê³µí•˜?Š” @Queryë¥? ?‚¬?š©?•œ?‹¤.
	 */
	 
	/**
	 * ?¸?ˆ˜ë¡? ? „?‹¬?œ ê¸?ë²ˆí˜¸ë³´ë‹¤ ?° ê¸?ë²ˆí˜¸?— ?•´?‹¹?•˜?Š” ? ˆì½”ë“œë¥? ?‚­? œ?•œ?‹¤.
	 */
	@Test
	public void deleteGrateThan() {
		rep.deleteGrateThan(200L);
	}
	
	/**
	 * ê¸?ë²ˆí˜¸ ?˜?Š” ? œëª©ì— ?•´?‹¹?•˜?Š” ? ˆì½”ë“œ ê²??ƒ‰
	 */
	@Test
	public void selectByBnoTitle() {
		List<Board> list=rep.selectByBnoTitle(180L, "? œëª?3");
		list.forEach(b->System.out.println(b));
	}
	
	/**
	 * ê¸?ë²ˆí˜¸, ? œëª?, ?‘?„±??— ?•´?‹¹?•˜?Š” ? ˆì½”ë“œ ê²??ƒ‰
	 */
	@Test
	public void selectParamBoard() {
//		List<Board> list=rep.selectParamBoard(new Board(3L, "? œëª?5", "User20", null, null, null)); //?•„?˜?— ë¹Œë”ë¡? ?‚¬?š©
		List<Board> list=rep.selectParamBoard(Board.builder().bno(3L).title("? œëª?5").writer("User20").build());
		list.forEach(b->System.out.println(b));
	}
	
	/////////////findByì¿¼ë¦¬ ë©”ì†Œ?“œ TEST/////////////////
	/**
	 * ? „?‹¬?œ ê¸?ë²ˆí˜¸ë³´ë‹¤ ?‘?? ?ˆ«??˜ ? ˆì½”ë“œë¥? ê²??ƒ‰
	 */
	@Test
	public void findByBnoLessThan() {
		List<Board> list=rep.findByBnoLessThan(50L);
		list.forEach(b->System.out.println(b));
	}
	
	/**
	 * ?˜?´ì§•ì²˜ë¦?
	 * ?˜?´ì§??‹¹ ê²Œì‹œë¬? ê°œìˆ˜, ? •? ¬ë°©ì‹ë§? ì§?? •?•˜ë©? ?¨
	 */
	@Test
	public void pagging() {
		//Pageable pageable=PageRequest.of(0, 10); //?˜„?¬ ?˜?´ì§?(0?´ë©? ì²˜ìŒ?¼?“¯), ?•œ?˜?´ì§??‹¹ 10ê°œì”©
//		Pageable pageable=PageRequest.of(0, 10, Direction.DESC, "bno");//bnoê¸°ì??œ¼ë¡? ?‚´ë¦¼ì°¨?ˆœ, 10ê°?
		Pageable pageable=PageRequest.of(19, 10, Direction.DESC, "bno");//bnoê¸°ì??œ¼ë¡? ?‚´ë¦¼ì°¨?ˆœ, 10ê°?
		
		Page<Board> page=rep.findAll(pageable);
		System.out.println("-----------------------");
		System.out.println("page.getNumber()="+page.getNumber()); //?˜„?¬ ?˜?´ì§? ë²ˆí˜¸
		System.out.println("page.getSize()="+page.getSize());
		System.out.println("page.getToTalPages()="+page.getTotalPages());
		System.out.println("page.previousPageable()="+page.previousPageable());
		System.out.println("page.previousPageable().getPageNumber()="+page.previousPageable().getPageNumber());
		System.out.println("page.nextPageable()="+page.nextPageable());
		
		System.out.println("page.isFirst()="+page.isFirst()); //ì²˜ìŒ?´?ƒ?
		System.out.println("page.isLast()="+page.isLast()); //ë§ˆì?ë§‰í˜?´ì§??ƒ?
		
		System.out.println("page.hasPrevious()="+page.hasPrevious()); //?´? „?˜?´ì§? ?ˆ?Š?ƒ?
		System.out.println("page.hasNext()="+page.hasNext()); //?‹¤?Œ?˜?´ì§? ?ˆ?Š?ƒ?
		System.out.println("-----------------------------------");
		
		List<Board> list=page.getContent();
		list.forEach(b->System.out.println(b));
		
	}
	///////////////////////////////////////
	/**
	 * JPQL ì¿¼ë¦¬ë¥? ??™?œ¼ë¡? ?ƒ?„±?•´ ì£¼ëŠ” QueryDSL ?‚¬?š©?•˜ê¸?
	 */
	@Test
	public void predicate() {
		BooleanBuilder builder=new BooleanBuilder();
		QBoard board=QBoard.board;
		//?œ„ë¡œëŠ” ë¬´ì¡°ê±? ?‚¬?š©
		
		//?‚¬?š©?•˜?Š” ë©”ì†Œ?“œ
//		builder.and(board.bno.eq(130L)); //where bno=130
//		builder.and(board.bno.gt(130L)); //where bno>130
		
//		builder.andNot(board.bno.gt(130L)); //where bno<=130
		
//		builder.and(board.title.like("%12%")); //where title like '%12%'
		
//		LocalDateTime from=LocalDateTime.of(2022, 11, 26, 0, 0);
//		LocalDateTime to=LocalDateTime.of(2022, 11, 28, 0, 0);
		
//		builder.and(board.insertDate.between(from, to)); 
		//between ~~ and ~~(?‹œê°„ì´ 28?¼ 00 ?? •?´?¼ 27?¼ê¹Œì?ë§? ?‚˜?˜´)
		
//		builder.and(board.writer.toUpperCase().eq("user171".toUpperCase())); //ì§?ê¸ˆê°’,DBê°? ?‘˜?‹¤ ??ë¬¸ìë¡? ë§ì¶°?„œ ë¹„êµ?•˜ê¸?
		
		builder.and(board.writer.equalsIgnoreCase("user171")); //???†Œë¬¸ì êµ¬ë¶„ ?—†?´ ë¹„êµ(=)
		
		builder.and(board.writer.equalsIgnoreCase("user171")).or(board.bno.eq(186L));
		
		//?•„?˜ë¡œëŠ” ë¬´ì¡°ê±? ?‚¬?š©
		Iterable<Board> it=rep.findAll(builder);
		List<Board> list=Lists.newArrayList(it);//Iterable ???…?„ List?˜•?ƒœë¡? ë³??™˜?•´ì£¼ëŠ” ë©”ì†Œ?“œ
		list.forEach(b->System.out.println(b));
	}
}
