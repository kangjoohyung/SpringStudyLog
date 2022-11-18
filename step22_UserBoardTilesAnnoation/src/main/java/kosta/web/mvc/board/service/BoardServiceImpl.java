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
		if(state) {//��ȸ������
			int result = boardDAO.readnumUpdate(modelNum);
			if(result==0)throw new RuntimeException("��ȸ�� ���� ������ �Խù� ��ȸ�� �����߽��ϴ�.");
		}
		
		ElectronicsDTO dto = boardDAO.selectByModelNum(modelNum);
		if(dto==null)throw new RuntimeException("�Խù��� ��ȸ �� �� �����ϴ�.");
		
		return dto;
	}
	

	@Override
	public int insert(ElectronicsDTO electronics){
		int result =0;
		try {
		  result = boardDAO.insert(electronics);
		}catch (Exception e) {
			throw new RuntimeException("��ϵ��� �ʾҽ��ϴ�.");
		}
		return result;
	}

	@Override
	public int delete(String modelNum, String password) {
		//��й�ȣ ��ġ���� Ȯ��
	  ElectronicsDTO dbEle = boardDAO.selectByModelNum(modelNum);
	  if(!dbEle.getPassword().equals(password)) {
		 throw new RuntimeException("��й�ȣ ������ �����Ҽ� �����ϴ�."); 
	  }
	  
	  int re =  boardDAO.delete(modelNum, password);
	  if(re==0)throw new RuntimeException("�������� �ʾҽ��ϴ�.");
	  
	   //���� ÷�ε������� �ִ� �Խù��� �����ߴٸ� ���������� ��������.
	  if(dbEle.getFname()!=null) {
		  File file = new File(BoardController.PATH_SAVE+"/"+dbEle.getFname());
		  file.delete();
	  }
	  
		return re;
	}
	

	@Override
	public int update(ElectronicsDTO electronics) {
		//�������� ��й�ȣ ��ġ ���� Ȯ��
		ElectronicsDTO dbElec = boardDAO.selectByModelNum(electronics.getModelNum());
		if(!dbElec.getPassword().equals(electronics.getPassword())) {
			throw new RuntimeException("��й�ȣ ������ ���� �Ҽ� �����ϴ�.");
		}
		
		int re = boardDAO.update(electronics);
		if(re==0)throw new RuntimeException("�������� �ʾҽ��ϴ�.");
		
		return re;
	}

}











