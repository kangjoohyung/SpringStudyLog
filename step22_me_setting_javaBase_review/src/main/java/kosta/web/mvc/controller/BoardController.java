package kosta.web.mvc.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import kosta.web.mvc.board.dto.ElectronicsDTO;
import kosta.web.mvc.util.FileRenameTimeIndex;

public class BoardController {
	/**
	 * 전체검색
	 * */
	/**
	 * 등록폼
	 * */
	/**
	 * 등록하기
	 * */
	@RequestMapping("/insert")
	public String insert(/* HttpSession session , */ElectronicsDTO electronics ) throws IOException{
		
		//파일이 첨부되었다면 fname,fsize를 설정한다. 
		MultipartFile  mfile = electronics.getFile();
		
		if(mfile.getSize() > 0) {//첨부된파일에 용량이 있다면(null아니라면)
			
			//파일 이름 중복 방지 변환 시키기
			String newName=new FileRenameTimeIndex().newNameOnly(PATH_SAVE, mfile.getOriginalFilename());
			
			//이후 DTO에 담기
			electronics.setFname(newName);
			electronics.setFsize(mfile.getSize());
		}
		
		
		boardService.insert(electronics);
		//int result=boardService.insert(electronics);
		
		/*
		if(mfile.getSize() > 0) {
		//if(result!=0 && mfile.getSize() > 0) {
		  mfile.transferTo(new File(PATH_SAVE+"/" + mfile.getOriginalFilename()));//폴더에 저장
		}*/
		
		//파일 등록하기
		//중복파일 두고 시험해보기
		if(mfile.getSize() > 0) {
			mfile.transferTo(new File(PATH_SAVE+"/" + electronics.getFname()));
		}
		
		return "redirect:/board/list";
	}
	
	/**
	 * 상세보기
	 * */
	/**
	 * 다운로드
	 * */
	/**
	 * 수정폼
	 * */
	/**
	 * 수정완료하기
	 * */
	/**
	 * 삭제하기
	 * */
}
