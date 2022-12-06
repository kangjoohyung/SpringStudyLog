package references.JPA.boot.step32;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kosta.mvc.domain.FreeBoard;
import kosta.mvc.domain.Reply;
import kosta.mvc.service.FreeBoardService;
import kosta.mvc.service.ReplyService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/reply")
@RequiredArgsConstructor
public class ReplyController {
//	@Autowired
//	private ReplyService replyService;
	private final ReplyService replyService;
	
	private final FreeBoardService boardService;

	/**
	 * 댓글 등록 폼
	 */
	@RequestMapping("/writeForm")
	public String writeForm(Long bno, Model model) {
		model.addAttribute("bno", bno);
		return "reply/write";
	}
	
	/**
	 * 등록하기
	 */
	@RequestMapping("/insert")
//	public void insert(Reply reply, FreeBoard board) {
//	public void insert(Reply reply, Long bno) { //1번방법
	public String insert(Reply reply, Long bno) { //2번방법
	
		FreeBoard board=new FreeBoard(bno);
		reply.setFreeBoard(board);
	
		//서비스호출
		replyService.insert(reply);
		
		//board/read쪽으로 이동
		FreeBoard dbBoard=boardService.selectBy(bno, false);
//		new ModelAndView("board/read", "board", dbBoard); //1번방법
		
		return "redirect:/board/read/"+bno+"?flag=1"; //2번방법
	}
	
	/**
	 * 삭제하기
	 */
	@RequestMapping("/delete/{rno}/{bno}")
	public String delete(@PathVariable Long rno, @PathVariable Long bno) {
		replyService.delete(rno);
		
		return "redirect:/board/read/"+bno+"?flag=1";
	}
}
