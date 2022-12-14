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
@RequiredArgsConstructor//?€λ₯λ¨
@Commit
public class BoardRepTests {
	
	@Autowired // μ£Όμ //final ?°?κΉ? ?€λ₯λ¨
	private BoardRepository rep;
	
	@Test
	public void aa() {
		System.out.println("rep="+rep);

	}

	/**
	 * ?±λ‘?
	 */
	@Test
	public void insert(){
		for(int i=1; i<=200; i++) {
			rep.save(new Board(null, "? λͺ?"+i, "User"+i, "?΄?©"+i, null, null));
		}
	}
	
	/**
	 * ? μ²΄κ??
	 */
	@Test
	public void selectAll() {
		List<Board> list=rep.findAll();
//		for(Board b:list) {
//			System.out.println(b);
//		}
		
		//??€??Όλ‘? λ°λ³΅λ¬? ?¬?©
		list.forEach(b->System.out.println(b));
	}
	
	/**
	 * idλ‘? κ²??(pkλ‘? κ²??)
	 */
	@Test
	public void SelectByBno() {
		
		//Optional?? java.util? μΆκ?? κ°μ²΄λ‘? null ?¬λΆ?λ₯? μ²΄ν¬?μ§? ??? ??λ‘? 
		//κ΄?? ¨? λ©μ?λ₯? ? κ³΅ν?€.
		Optional<Board> op=rep.findById(170L);
		Board board=op.orElse(null);
		System.out.println(board);
	}
	
	/**
	 * ?? ?κΈ?
	 */
	@Test
	public void update() { //?λΉμ€ λ©μ?
		Board board=rep.findById(50L).orElse(null);
		board.setContent("50λ²? κ²μλ¬? ?? ");
		board.setWriter("?₯?¬? ");
	}
		
	/**
	 * ?­? ?κΈ?
	 */
	@Test
	public void delete() {
		rep.deleteById(30L);
	}
	//////////////////////////////////////
	/**
	 * 1. JPQL(κ°μ²΄μ€μ¬?Όλ‘? μΏΌλ¦¬ ??±) λ¬Έλ²? ?΄?©?΄? ?¬?©? ? ? μΏΌλ¦¬λ₯? ??±?  ? ??€
	 *    spring-data-jpa?? JPQLλ¬Έλ²? ? κ³΅ν? @Queryλ₯? ?¬?©??€.
	 */
	 
	/**
	 * ?Έ?λ‘? ? ?¬? κΈ?λ²νΈλ³΄λ€ ?° κΈ?λ²νΈ? ?΄?Ή?? ? μ½λλ₯? ?­? ??€.
	 */
	@Test
	public void deleteGrateThan() {
		rep.deleteGrateThan(200L);
	}
	
	/**
	 * κΈ?λ²νΈ ?? ? λͺ©μ ?΄?Ή?? ? μ½λ κ²??
	 */
	@Test
	public void selectByBnoTitle() {
		List<Board> list=rep.selectByBnoTitle(180L, "? λͺ?3");
		list.forEach(b->System.out.println(b));
	}
	
	/**
	 * κΈ?λ²νΈ, ? λͺ?, ??±?? ?΄?Ή?? ? μ½λ κ²??
	 */
	@Test
	public void selectParamBoard() {
//		List<Board> list=rep.selectParamBoard(new Board(3L, "? λͺ?5", "User20", null, null, null)); //??? λΉλλ‘? ?¬?©
		List<Board> list=rep.selectParamBoard(Board.builder().bno(3L).title("? λͺ?5").writer("User20").build());
		list.forEach(b->System.out.println(b));
	}
	
	/////////////findByμΏΌλ¦¬ λ©μ? TEST/////////////////
	/**
	 * ? ?¬? κΈ?λ²νΈλ³΄λ€ ??? ?«?? ? μ½λλ₯? κ²??
	 */
	@Test
	public void findByBnoLessThan() {
		List<Board> list=rep.findByBnoLessThan(50L);
		list.forEach(b->System.out.println(b));
	}
	
	/**
	 * ??΄μ§μ²λ¦?
	 * ??΄μ§??Ή κ²μλ¬? κ°μ, ? ? ¬λ°©μλ§? μ§?? ?λ©? ?¨
	 */
	@Test
	public void pagging() {
		//Pageable pageable=PageRequest.of(0, 10); //??¬ ??΄μ§?(0?΄λ©? μ²μ?Ό?―), ???΄μ§??Ή 10κ°μ©
//		Pageable pageable=PageRequest.of(0, 10, Direction.DESC, "bno");//bnoκΈ°μ??Όλ‘? ?΄λ¦Όμ°¨?, 10κ°?
		Pageable pageable=PageRequest.of(19, 10, Direction.DESC, "bno");//bnoκΈ°μ??Όλ‘? ?΄λ¦Όμ°¨?, 10κ°?
		
		Page<Board> page=rep.findAll(pageable);
		System.out.println("-----------------------");
		System.out.println("page.getNumber()="+page.getNumber()); //??¬ ??΄μ§? λ²νΈ
		System.out.println("page.getSize()="+page.getSize());
		System.out.println("page.getToTalPages()="+page.getTotalPages());
		System.out.println("page.previousPageable()="+page.previousPageable());
		System.out.println("page.previousPageable().getPageNumber()="+page.previousPageable().getPageNumber());
		System.out.println("page.nextPageable()="+page.nextPageable());
		
		System.out.println("page.isFirst()="+page.isFirst()); //μ²μ?΄??
		System.out.println("page.isLast()="+page.isLast()); //λ§μ?λ§ν?΄μ§???
		
		System.out.println("page.hasPrevious()="+page.hasPrevious()); //?΄? ??΄μ§? ????
		System.out.println("page.hasNext()="+page.hasNext()); //?€???΄μ§? ????
		System.out.println("-----------------------------------");
		
		List<Board> list=page.getContent();
		list.forEach(b->System.out.println(b));
		
	}
	///////////////////////////////////////
	/**
	 * JPQL μΏΌλ¦¬λ₯? ???Όλ‘? ??±?΄ μ£Όλ QueryDSL ?¬?©?κΈ?
	 */
	@Test
	public void predicate() {
		BooleanBuilder builder=new BooleanBuilder();
		QBoard board=QBoard.board;
		//?λ‘λ λ¬΄μ‘°κ±? ?¬?©
		
		//?¬?©?? λ©μ?
//		builder.and(board.bno.eq(130L)); //where bno=130
//		builder.and(board.bno.gt(130L)); //where bno>130
		
//		builder.andNot(board.bno.gt(130L)); //where bno<=130
		
//		builder.and(board.title.like("%12%")); //where title like '%12%'
		
//		LocalDateTime from=LocalDateTime.of(2022, 11, 26, 0, 0);
//		LocalDateTime to=LocalDateTime.of(2022, 11, 28, 0, 0);
		
//		builder.and(board.insertDate.between(from, to)); 
		//between ~~ and ~~(?κ°μ΄ 28?Ό 00 ?? ?΄?Ό 27?ΌκΉμ?λ§? ??΄)
		
//		builder.and(board.writer.toUpperCase().eq("user171".toUpperCase())); //μ§?κΈκ°,DBκ°? ??€ ??λ¬Έμλ‘? λ§μΆ°? λΉκ΅?κΈ?
		
		builder.and(board.writer.equalsIgnoreCase("user171")); //???λ¬Έμ κ΅¬λΆ ??΄ λΉκ΅(=)
		
		builder.and(board.writer.equalsIgnoreCase("user171")).or(board.bno.eq(186L));
		
		//??λ‘λ λ¬΄μ‘°κ±? ?¬?©
		Iterable<Board> it=rep.findAll(builder);
		List<Board> list=Lists.newArrayList(it);//Iterable ???? List??λ‘? λ³???΄μ£Όλ λ©μ?
		list.forEach(b->System.out.println(b));
	}
}
