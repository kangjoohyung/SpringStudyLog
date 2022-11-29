package references.JPA.boot.step32;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kosta.mvc.domain.FreeBoard;
import kosta.mvc.service.FreeBoardService;
import lombok.RequiredArgsConstructor;

//@RestController //ajax
@Controller
@RequestMapping("/board")
//@RequiredArgsConstructor
public class FreeBoardController {
	
	//private final FreeBoardService freeService; 
	//상수에 final선언하고 주입 피하기위해 @Autowired로 변경
	@Autowired
	private FreeBoardService freeService;

	private final static int PAGE_COUNT=10; //페이지내 레코드 수 보통 상수로 관리
	private final static int BLOCK_COUNT=4; //이전,다음 위해 블럭 설정
	
	/**
	 * 전체 검색 페이지
	 */
	@RequestMapping("/list")
	public void list(Model model,@RequestParam(defaultValue="1") int nowPage) { 
		//페이징 처리, 현재페이지도 인수로 받기, null이면 int이기에 에러발생함, defaultValue로 null 회피
		//service -> dao

		//페이징 처리 없을 때
//		List<FreeBoard> freeBoardList=freeService.selectAll();
//		model.addAttribute("freeList", freeBoardList);

		//페이징 처리 메소드
		Pageable page=PageRequest.of(nowPage-1, PAGE_COUNT, Direction.DESC, "bno"); //페이지 개수 보통 상수로 관리
		Page<FreeBoard> pageList=freeService.selectAll(page);
		
		int temp=0;
		if (nowPage!=1) { //내가 추가함 0나누기하면 안되니까
			temp=(nowPage-1)%BLOCK_COUNT;
		}
		int startPage=nowPage-temp;
		
		model.addAttribute("pageList", pageList);
		
		//페이징 처리 위한 정보들 값으로 넣어 전달
		model.addAttribute("blockCount", BLOCK_COUNT);
		model.addAttribute("startPage", startPage);
		model.addAttribute("nowPage", nowPage);
	}
	
	/**
	 * 등록폼
	 */
	@RequestMapping("/write")
	public void write() {
		
	}
	
	/**
	 * 게시물 등록하기
	 */
	@RequestMapping("/insert")
	public String insert(FreeBoard board) {
//		FreeBoard resultBoard=
		freeService.insert(board);
//		System.out.println("resultBoard="+resultBoard);
		
		return "redirect:/board/list"; //컨트롤러로 바로 갈때는 redirect: 혹은 forward:
	}
	
	/**
	 * 상세보기
	 */
	@RequestMapping("/read/{bno}")
	public ModelAndView read(@PathVariable Long bno, String flag) {
		//@PathVariable 주소의 값을 인수로 받기

		boolean state=flag==null?true:false;
		
		FreeBoard freeBoard=freeService.selectBy(bno, true);//true는 조회수 증가
		
		return new ModelAndView("board/read", "board", freeBoard);
	}
	
	/**
	 * 수정폼
	 */
	@RequestMapping("/updateForm")
	public ModelAndView updateForm(Long bno) {
		
		FreeBoard board=freeService.selectBy(bno, false);//조회수 증가 안함
		
		return new ModelAndView("board/update", "board", board);
	}
	
	/**
	 * 수정 완료하기
	 */
	@RequestMapping("/update")
	public ModelAndView update(FreeBoard board) {
		FreeBoard dbBoard=freeService.update(board);
		System.out.println("dbBoard="+dbBoard);
		
		return new ModelAndView("board/read", "board", dbBoard);
	}
	
	/**
	 * 삭제하기
	 */
	@RequestMapping("/delete")
	public String delete(Long bno, String password) {
		
		freeService.delete(bno, password);
		
		return "redirect:/board/list";
	}
}
