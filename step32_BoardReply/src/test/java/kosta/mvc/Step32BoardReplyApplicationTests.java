package kosta.mvc;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import kosta.mvc.domain.FreeBoard;
import kosta.mvc.repository.FreeBoardRepository;

@SpringBootTest
@Commit
@Transactional
class Step32BoardReplyApplicationTests {
	@Autowired
	private FreeBoardRepository freeRep;

	@Test
	void contextLoads() {
		//freeboard에 105개 정도 레코드 추가
		for(int i=1; i<=105; i++) {
			freeRep.save(FreeBoard
					.builder()
					.subject("제목"+i)
					.writer("User"+i)
					.content("FreeBoard Test"+i)
					.password("1234")
					.readnum(0)
					.build());
			
		}//for end
	}//contextLoads() end

}
