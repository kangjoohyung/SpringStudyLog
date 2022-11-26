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
@RequiredArgsConstructor//?��류남
@Commit
public class BoardRepTests {
	
	@Autowired // 주입 //final ?��?���? ?��류남
	private BoardRepository rep;
	
	@Test
	public void aa() {
		System.out.println("rep="+rep);

	}

	/**
	 * ?���?
	 */
	@Test
	public void insert(){
		for(int i=1; i<=200; i++) {
			rep.save(new Board(null, "?���?"+i, "User"+i, "?��?��"+i, null, null));
		}
	}
	
	/**
	 * ?��체�??��
	 */
	@Test
	public void selectAll() {
		List<Board> list=rep.findAll();
//		for(Board b:list) {
//			System.out.println(b);
//		}
		
		//?��?��?��?���? 반복�? ?��?��
		list.forEach(b->System.out.println(b));
	}
	
	/**
	 * id�? �??��(pk�? �??��)
	 */
	@Test
	public void SelectByBno() {
		
		//Optional?? java.util?�� 추�??�� 객체�? null ?���?�? 체크?���? ?��?��?�� ?��?���? 
		//�??��?�� 메소?���? ?��공한?��.
		Optional<Board> op=rep.findById(170L);
		Board board=op.orElse(null);
		System.out.println(board);
	}
	
	/**
	 * ?��?��?���?
	 */
	@Test
	public void update() { //?��비스 메소?��
		Board board=rep.findById(50L).orElse(null);
		board.setContent("50�? 게시�? ?��?��");
		board.setWriter("?��?��?��");
	}
		
	/**
	 * ?��?��?���?
	 */
	@Test
	public void delete() {
		rep.deleteById(30L);
	}
	//////////////////////////////////////
	/**
	 * 1. JPQL(객체중심?���? 쿼리 ?��?��) 문법?�� ?��?��?��?�� ?��?��?�� ?��?�� 쿼리�? ?��?��?�� ?�� ?��?��
	 *    spring-data-jpa?��?�� JPQL문법?�� ?��공하?�� @Query�? ?��?��?��?��.
	 */
	 
	/**
	 * ?��?���? ?��?��?�� �?번호보다 ?�� �?번호?�� ?��?��?��?�� ?��코드�? ?��?��?��?��.
	 */
	@Test
	public void deleteGrateThan() {
		rep.deleteGrateThan(200L);
	}
	
	/**
	 * �?번호 ?��?�� ?��목에 ?��?��?��?�� ?��코드 �??��
	 */
	@Test
	public void selectByBnoTitle() {
		List<Board> list=rep.selectByBnoTitle(180L, "?���?3");
		list.forEach(b->System.out.println(b));
	}
	
	/**
	 * �?번호, ?���?, ?��?��?��?�� ?��?��?��?�� ?��코드 �??��
	 */
	@Test
	public void selectParamBoard() {
//		List<Board> list=rep.selectParamBoard(new Board(3L, "?���?5", "User20", null, null, null)); //?��?��?�� 빌더�? ?��?��
		List<Board> list=rep.selectParamBoard(Board.builder().bno(3L).title("?���?5").writer("User20").build());
		list.forEach(b->System.out.println(b));
	}
	
	/////////////findBy쿼리 메소?�� TEST/////////////////
	/**
	 * ?��?��?�� �?번호보다 ?��?? ?��?��?�� ?��코드�? �??��
	 */
	@Test
	public void findByBnoLessThan() {
		List<Board> list=rep.findByBnoLessThan(50L);
		list.forEach(b->System.out.println(b));
	}
	
	/**
	 * ?��?��징처�?
	 * ?��?���??�� 게시�? 개수, ?��?��방식�? �??��?���? ?��
	 */
	@Test
	public void pagging() {
		//Pageable pageable=PageRequest.of(0, 10); //?��?�� ?��?���?(0?���? 처음?��?��), ?��?��?���??�� 10개씩
//		Pageable pageable=PageRequest.of(0, 10, Direction.DESC, "bno");//bno기�??���? ?��림차?��, 10�?
		Pageable pageable=PageRequest.of(19, 10, Direction.DESC, "bno");//bno기�??���? ?��림차?��, 10�?
		
		Page<Board> page=rep.findAll(pageable);
		System.out.println("-----------------------");
		System.out.println("page.getNumber()="+page.getNumber()); //?��?�� ?��?���? 번호
		System.out.println("page.getSize()="+page.getSize());
		System.out.println("page.getToTalPages()="+page.getTotalPages());
		System.out.println("page.previousPageable()="+page.previousPageable());
		System.out.println("page.previousPageable().getPageNumber()="+page.previousPageable().getPageNumber());
		System.out.println("page.nextPageable()="+page.nextPageable());
		
		System.out.println("page.isFirst()="+page.isFirst()); //처음?��?��?
		System.out.println("page.isLast()="+page.isLast()); //마�?막페?���??��?
		
		System.out.println("page.hasPrevious()="+page.hasPrevious()); //?��?��?��?���? ?��?��?��?
		System.out.println("page.hasNext()="+page.hasNext()); //?��?��?��?���? ?��?��?��?
		System.out.println("-----------------------------------");
		
		List<Board> list=page.getContent();
		list.forEach(b->System.out.println(b));
		
	}
	///////////////////////////////////////
	/**
	 * JPQL 쿼리�? ?��?��?���? ?��?��?�� 주는 QueryDSL ?��?��?���?
	 */
	@Test
	public void predicate() {
		BooleanBuilder builder=new BooleanBuilder();
		QBoard board=QBoard.board;
		//?��로는 무조�? ?��?��
		
		//?��?��?��?�� 메소?��
//		builder.and(board.bno.eq(130L)); //where bno=130
//		builder.and(board.bno.gt(130L)); //where bno>130
		
//		builder.andNot(board.bno.gt(130L)); //where bno<=130
		
//		builder.and(board.title.like("%12%")); //where title like '%12%'
		
//		LocalDateTime from=LocalDateTime.of(2022, 11, 26, 0, 0);
//		LocalDateTime to=LocalDateTime.of(2022, 11, 28, 0, 0);
		
//		builder.and(board.insertDate.between(from, to)); 
		//between ~~ and ~~(?��간이 28?�� 00 ?��?��?��?�� 27?��까�?�? ?��?��)
		
//		builder.and(board.writer.toUpperCase().eq("user171".toUpperCase())); //�?금값,DB�? ?��?�� ??문자�? 맞춰?�� 비교?���?
		
		builder.and(board.writer.equalsIgnoreCase("user171")); //???��문자 구분 ?��?�� 비교(=)
		
		builder.and(board.writer.equalsIgnoreCase("user171")).or(board.bno.eq(186L));
		
		//?��?��로는 무조�? ?��?��
		Iterable<Board> it=rep.findAll(builder);
		List<Board> list=Lists.newArrayList(it);//Iterable ???��?�� List?��?���? �??��?��주는 메소?��
		list.forEach(b->System.out.println(b));
	}
}
