package sample10tea;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

//@Component //id="boardController"
@Controller
public class BoardController {
	
   @Autowired	
   private BoardService boardService;
   
   @Autowired
   private BoardDTO boardDTO;
   
   @Autowired
   private BoardDTO boardDTO2;
   
   public void test() {
	   System.out.println("boardDTO = " + boardDTO +", subject : " + boardDTO.getSubject());
	   System.out.println("boardDTO2 = " + boardDTO2+", subject : " + boardDTO2.getSubject());
	   
	   System.out.println("boardService = " + boardService);
	   
	   boardService.select();
   }
}
