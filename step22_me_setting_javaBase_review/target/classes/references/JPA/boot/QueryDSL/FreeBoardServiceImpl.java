package references.JPA.boot.QueryDSL;

import java.util.List;

import javax.management.RuntimeErrorException;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.util.ArrayBuilders.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;

import kosta.mvc.domain.FreeBoard;
import kosta.mvc.domain.QFreeBoard;
import kosta.mvc.repository.FreeBoardRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional //transactional 처리
public class FreeBoardServiceImpl implements FreeBoardService {

	private final JPAQueryFactory queryFactory;
	private final FreeBoardRepository freeRep;
	
	@Override
	public List<FreeBoard> selectAll() {
		return freeRep.findAll();
	}

	@Override
	public Page<FreeBoard> selectAll(Pageable pageable) {
		return freeRep.findAll(pageable);
	}

	//@Transactional
	@Override
	//public FreeBoard insert(FreeBoard board) {
	public void insert(FreeBoard board) {
		FreeBoard resultBoard=freeRep.save(board);
		System.out.println("resultBoard="+resultBoard);
		
		//주문테이블 insert
		//상세테이블 insert
		//등등의 트랜젝션 유지한 채로 값을 전달해야 할 때 결과 값(resultBoard) 사용(??)
		//return resultBoard; //확인용
	}

	@Override
	public FreeBoard selectBy(Long bno, boolean state) {
		
		if(state) {//조회수 증가
			freeRep.updateReadnum(bno);
		}
		
		FreeBoard board=freeRep.findById(bno).orElse(null);

		if(board==null) throw new RuntimeException("상세보기 오류입니다");
		
		return board;
	}

	@Override
	public FreeBoard update(FreeBoard board) {
		FreeBoard dbBoard=freeRep.findById(board.getBno()).orElse(null);
		if(dbBoard==null) throw new RuntimeException("글번호 오류로 수정할 수 없습니다");
		
		if(!dbBoard.getPassword().equals(board.getPassword()))
			throw new RuntimeException("비밀번호 오류로 수정할 수 없습니다");
		
		//수정하자
		dbBoard.setContent(board.getContent());
		dbBoard.setSubject(board.getSubject());
		
		return dbBoard;
	}

	@Override
	public void delete(Long bno, String password) {
		//방법1 패스워드 찾아본다음에 비교해서 삭제하기
		//방법2 쿼리를 직접 만들어서 인수로 받아 삭제하기->queryDsl사용해볼예제
//		BooleanBuilder builder=new BooleanBuilder(); //이거 안먹힘
		
		//설정파일 : JPAQueryFactory 생성/설정후 JPAQueryFactory 주입후 querydsl사용가능
		
		System.out.println("queryFactory="+queryFactory);
		
		QFreeBoard freeBoard=QFreeBoard.freeBoard;
		//delete board ~~~~
		long re=queryFactory.delete(freeBoard)
				.where(freeBoard.bno.eq(bno)
						.and(freeBoard.password.eq(password))
				)
				.execute();
		
		System.out.println("re="+re);
		
		if(re==0) throw new RuntimeException("삭제할 수 없습니다");

	}

}
