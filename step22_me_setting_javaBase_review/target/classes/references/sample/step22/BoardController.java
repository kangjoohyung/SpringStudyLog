package references.sample.step22;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import kosta.web.mvc.board.dto.ElectronicsDTO;
import kosta.web.mvc.board.service.BoardService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
	
	
	private final BoardService boardService;
	public static final String PATH_SAVE="C:\\Edu\\Spring\\fileSave";
	
	/**
	 * ��ü�˻�
	 * */
	@RequestMapping("/list")
	public void list(/* HttpSession session , */ Model model) {
		//���� ȣ���ؼ� ��ü�Խù� �����ͼ� �����Ѵ�.
		List<ElectronicsDTO> list = boardService.selectAll();
		model.addAttribute("list", list); //${list}
	}
	
	/**
	 * �����
	 * */
	@RequestMapping("/write")
	public void write (/*HttpSession session */ ) {}
	
	/**
	 * ����ϱ�
	 * */
	@RequestMapping("/insert")
	public String insert(/* HttpSession session , */ElectronicsDTO electronics ) throws IOException{
		
		//������ ÷�εǾ��ٸ� ����������, fname,fsize�� �����Ѵ�. 
		MultipartFile  mfile = electronics.getFile();
		if(mfile.getSize() > 0) {//÷�ε����Ͽ� �뷮�� �ִٸ� 
			electronics.setFname(mfile.getOriginalFilename());
			electronics.setFsize(mfile.getSize());
		}
		
		boardService.insert(electronics);
		//int result=boardService.insert(electronics);
		
		if(mfile.getSize() > 0) {
		//if(result!=0 && mfile.getSize() > 0) {
		  mfile.transferTo(new File(PATH_SAVE+"/" + mfile.getOriginalFilename()));//������ ����
		}
		
		return "redirect:/board/list";
	}
	
	
	/**
	 * �󼼺���
	 * */
	@RequestMapping("/read/{modelNum}")
	public ModelAndView read(/* HttpSession session , */@PathVariable String modelNum) {
		ElectronicsDTO dto = boardService.selectByModelNum(modelNum, true);//true �� ��ȸ�� ����!!
		return new ModelAndView("board/read", "elec", dto);
	}
	
	/**
	 * �ٿ�ε�
	 * */
	@RequestMapping("/down")
	public ModelAndView down(HttpSession session ,String fname) {
		File file = new File(PATH_SAVE+"/" + fname);
		
		return new ModelAndView("downLoadView", "fname", file); //downLoadView�� bean�� id�� ã�´�.
	}
	
	/**
	 * ������
	 * */
	@RequestMapping("/updateForm")
	public String updateForm(/* HttpSession session , */ String modelNum , Model model) {
		ElectronicsDTO dto = boardService.selectByModelNum(modelNum, false);//false�� ��ȸ�������ȵ�
		model.addAttribute("elec", dto);
		return "board/update";
	}
	
	
	/**
	 * �����Ϸ��ϱ�
	 * */
	@RequestMapping("/update")
	public ModelAndView update(/* HttpSession session , */ElectronicsDTO electronics) {
		boardService.update(electronics);
		
		ElectronicsDTO dbElec = boardService.selectByModelNum(electronics.getModelNum(), false);
		
		return new ModelAndView("board/read", "elec", dbElec);
	}
	
	/**
	 * �����ϱ�
	 * */
	@RequestMapping("/delete")
	public String delete(/* HttpSession session , */String modelNum, String password) {
		boardService.delete(modelNum, password);
		
		
		
		return "redirect:/board/list";
	}

}










