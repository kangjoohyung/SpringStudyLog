package kosta.web.mvc.board.service;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import kosta.web.mvc.board.controller.BoardController;
import kosta.web.mvc.board.dto.ElectronicsDTO;
import kosta.web.mvc.board.repository.BoardDAO;
import kosta.web.mvc.user.repository.UserDAO;

@Service
@Transactional
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardDAO boardDAO;
	

	@Override
	public List<ElectronicsDTO> selectAll() {
		return boardDAO.selectAll();
	}

	@Override
	public ElectronicsDTO selectByModelNum(String modelNum, boolean state) {
		if(state) {//조회수증가
			int result = boardDAO.readnumUpdate(modelNum);
			if(result==0)throw new RuntimeException("조회수 증가 오류로 게시물 조회에 실패했습니다.");
		}
		
		ElectronicsDTO dto = boardDAO.selectByModelNum(modelNum);
		if(dto==null)throw new RuntimeException("게시물을 조회 할 수 없습니다.");
		
		return dto;
	}
	

	@Override
	public int insert(ElectronicsDTO electronics){
		int result =0;
		try {
		  result = boardDAO.insert(electronics);
		}catch (Exception e) {
			throw new RuntimeException("등록되지 않았습니다.");
		}
		return result;
	}

	@Override
	public int delete(String modelNum, String password) {
		//비밀번호 일치여부 확인
	  ElectronicsDTO dbEle = boardDAO.selectByModelNum(modelNum);
	  if(!dbEle.getPassword().equals(password)) {
		 throw new RuntimeException("비밀번호 오류로 삭제할수 없습니다."); 
	  }
	  
	  int re =  boardDAO.delete(modelNum, password);
	  if(re==0)throw new RuntimeException("삭제되지 않았습니다.");
	  
	   //만약 첨부된파일이 있는 게시물을 삭제했다면 폴더에서도 삭제하자.
	  if(dbEle.getFname()!=null) {
		  File file = new File(BoardController.PATH_SAVE+"/"+dbEle.getFname());
		  file.delete();
	  }
	  
		return re;
	}
	

	@Override
	public int update(ElectronicsDTO electronics) {
		//수정전에 비밀번호 일치 여부 확인
		ElectronicsDTO dbElec = boardDAO.selectByModelNum(electronics.getModelNum());
		if(!dbElec.getPassword().equals(electronics.getPassword())) {
			throw new RuntimeException("비밀번호 오류로 수정 할수 없습니다.");
		}
		
		int re = boardDAO.update(electronics);
		if(re==0)throw new RuntimeException("수정되지 않았습니다.");
		
		return re;
	}

}











