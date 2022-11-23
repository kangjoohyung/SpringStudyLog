package kosta.web.mvc.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import kosta.web.mvc.board.dto.ElectronicsDTO;
import kosta.web.mvc.util.FileRenameTimeIndex;

public class BoardController {
	/**
	 * ��ü�˻�
	 * */
	/**
	 * �����
	 * */
	/**
	 * ����ϱ�
	 * */
	@RequestMapping("/insert")
	public String insert(/* HttpSession session , */ElectronicsDTO electronics ) throws IOException{
		
		//������ ÷�εǾ��ٸ� fname,fsize�� �����Ѵ�. 
		MultipartFile  mfile = electronics.getFile();
		
		if(mfile.getSize() > 0) {//÷�ε����Ͽ� �뷮�� �ִٸ�(null�ƴ϶��)
			
			//���� �̸� �ߺ� ���� ��ȯ ��Ű��
			String newName=new FileRenameTimeIndex().newNameOnly(PATH_SAVE, mfile.getOriginalFilename());
			
			//���� DTO�� ���
			electronics.setFname(newName);
			electronics.setFsize(mfile.getSize());
		}
		
		
		boardService.insert(electronics);
		//int result=boardService.insert(electronics);
		
		/*
		if(mfile.getSize() > 0) {
		//if(result!=0 && mfile.getSize() > 0) {
		  mfile.transferTo(new File(PATH_SAVE+"/" + mfile.getOriginalFilename()));//������ ����
		}*/
		
		//���� ����ϱ�
		//�ߺ����� �ΰ� �����غ���
		if(mfile.getSize() > 0) {
			mfile.transferTo(new File(PATH_SAVE+"/" + electronics.getFname()));
		}
		
		return "redirect:/board/list";
	}
	
	/**
	 * �󼼺���
	 * */
	/**
	 * �ٿ�ε�
	 * */
	/**
	 * ������
	 * */
	/**
	 * �����Ϸ��ϱ�
	 * */
	/**
	 * �����ϱ�
	 * */
}
