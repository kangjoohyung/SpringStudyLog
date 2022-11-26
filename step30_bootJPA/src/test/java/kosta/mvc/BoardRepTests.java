package kosta.mvc;

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
@RequiredArgsConstructor//오류남
@Commit
public class BoardRepTests {
	
	@Autowired // 주입 //final 쓰니까 오류남
	private BoardRepository rep;
	
	@Test
	public void aa() {
		System.out.println("rep="+rep);

	}

	/**
	 * 등록
	 */
	@Test
	public void insert(){
		for(int i=1; i<=200; i++) {
			rep.save(new Board(null, "제목"+i, "User"+i, "내용"+i, null, null));
		}
	}
	
	/**
	 * 전체검색
	 */
	@Test
	public void selectAll() {
		List<Board> list=rep.findAll();
//		for(Board b:list) {
//			System.out.println(b);
//		}
		
		//람다식으로 반복문 사용
		list.forEach(b->System.out.println(b));
	}
	
	/**
	 * id로 검색(pk로 검색)
	 */
	@Test
	public void SelectByBno() {
		
		//Optional은 java.util에 추가된 객체로 null 여부를 체크하지 않아도 되도록 
		//관련된 메소드를 제공한다.
		Optional<Board> op=rep.findById(170L);
		Board board=op.orElse(null);
		System.out.println(board);
	}
	
	/**
	 * 수정하기
	 */
	@Test
	public void update() { //서비스 메소드
		Board board=rep.findById(50L).orElse(null);
		board.setContent("50번 게시물 수정");
		board.setWriter("장희정");
	}
		
	/**
	 * 삭제하기
	 */
	@Test
	public void delete() {
		rep.deleteById(30L);
	}
	//////////////////////////////////////
	/**
	 * 1. JPQL(객체중심으로 쿼리 작성) 문법을 이용해서 사용자 정의 쿼리를 작성할 수 있다
	 *    spring-data-jpa에서 JPQL문법을 제공하는 @Query를 사용한다.
	 */
	 
	/**
	 * 인수로 전달된 글번호보다 큰 글번호에 해당하는 레코드를 삭제한다.
	 */
	@Test
	public void deleteGrateThan() {
		rep.deleteGrateThan(200L);
	}
	
	/**
	 * 글번호 또는 제목에 해당하는 레코드 검색
	 */
	@Test
	public void selectByBnoTitle() {
		List<Board> list=rep.selectByBnoTitle(180L, "제목3");
		list.forEach(b->System.out.println(b));
	}
	
	/**
	 * 글번호, 제목, 작성자에 해당하는 레코드 검색
	 */
	@Test
	public void selectParamBoard() {
//		List<Board> list=rep.selectParamBoard(new Board(3L, "제목5", "User20", null, null, null)); //아래에 빌더로 사용
		List<Board> list=rep.selectParamBoard(Board.builder().bno(3L).title("제목5").writer("User20").build());
		list.forEach(b->System.out.println(b));
	}
	
	/////////////findBy쿼리 메소드 TEST/////////////////
	/**
	 * 전달된 글번호보다 작은 숫자의 레코드를 검색
	 */
	@Test
	public void findByBnoLessThan() {
		List<Board> list=rep.findByBnoLessThan(50L);
		list.forEach(b->System.out.println(b));
	}
	
	/**
	 * 페이징처리
	 * 페이지당 게시물 개수, 정렬방식만 지정하면 됨
	 */
	@Test
	public void pagging() {
		//Pageable pageable=PageRequest.of(0, 10); //현재 페이지(0이면 처음일듯), 한페이지당 10개씩
//		Pageable pageable=PageRequest.of(0, 10, Direction.DESC, "bno");//bno기준으로 내림차순, 10개
		Pageable pageable=PageRequest.of(19, 10, Direction.DESC, "bno");//bno기준으로 내림차순, 10개
		
		Page<Board> page=rep.findAll(pageable);
		System.out.println("-----------------------");
		System.out.println("page.getNumber()="+page.getNumber()); //현재 페이지 번호
		System.out.println("page.getSize()="+page.getSize());
		System.out.println("page.getToTalPages()="+page.getTotalPages());
		System.out.println("page.previousPageable()="+page.previousPageable());
		System.out.println("page.previousPageable().getPageNumber()="+page.previousPageable().getPageNumber());
		System.out.println("page.nextPageable()="+page.nextPageable());
		
		System.out.println("page.isFirst()="+page.isFirst()); //처음이냐?
		System.out.println("page.isLast()="+page.isLast()); //마지막페이지냐?
		
		System.out.println("page.hasPrevious()="+page.hasPrevious()); //이전페이지 있느냐?
		System.out.println("page.hasNext()="+page.hasNext()); //다음페이지 있느냐?
		System.out.println("-----------------------------------");
		
		List<Board> list=page.getContent();
		list.forEach(b->System.out.println(b));
		
	}
	///////////////////////////////////////
	/**
	 * JPQL 쿼리를 자동으로 생성해 주는 QueryDSL 사용하기
	 */
	@Test
	public void predicate() {
		BooleanBuilder builder=new BooleanBuilder();
		QBoard board=QBoard.board;
		//위로는 무조건 사용
		
		//사용하는 메소드
//		builder.and(board.bno.eq(130L)); //where bno=130
//		builder.and(board.bno.gt(130L)); //where bno>130
		
//		builder.andNot(board.bno.gt(130L)); //where bno<=130
		
//		builder.and(board.title.like("%12%")); //where title like '%12%'
		
//		LocalDateTime from=LocalDateTime.of(2022, 11, 26, 0, 0);
//		LocalDateTime to=LocalDateTime.of(2022, 11, 28, 0, 0);
		
//		builder.and(board.insertDate.between(from, to)); 
		//between ~~ and ~~(시간이 28일 00 자정이라 27일까지만 나옴)
		
//		builder.and(board.writer.toUpperCase().eq("user171".toUpperCase())); //지금값,DB값 둘다 대문자로 맞춰서 비교하기
		
		builder.and(board.writer.equalsIgnoreCase("user171")); //대소문자 구분 없이 비교(=)
		
		builder.and(board.writer.equalsIgnoreCase("user171")).or(board.bno.eq(186L));
		
		//아래로는 무조건 사용
		Iterable<Board> it=rep.findAll(builder);
		List<Board> list=Lists.newArrayList(it);//Iterable 타입을 List형태로 변환해주는 메소드
		list.forEach(b->System.out.println(b));
	}
}
