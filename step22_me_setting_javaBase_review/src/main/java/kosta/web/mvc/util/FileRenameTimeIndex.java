package kosta.web.mvc.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
 
	
/**
 * 파일 업로드시 이름 변경 (중복방지) 클래스
 * -> 기존파일명+생성시간+중복index로 변경
 * 
 * 업로드 파일이름 : 기존파일명_yyyy-MM-dd_hhMMss_sss
 * 중복시 파일이름 : 기존파일명_yyyy-MM-dd_hhMMss_sss(2~9999)
 * 
 * 사용예시
 * 
 * File newFile = new FileRenameTimeIndex().rename(new File("경로및파일이름"));
 * 
 * String fileName=new FileRenameTimeIndex().rename(String path, String name);
 * 
 * @author 강주형
 *
 */
public class FileRenameTimeIndex {

	/**
	 * 파일이 있는지 확인하기
	 * @param File f
	 * @return 없으면 true(사용가능), 있으면 false(사용불가)
	 */
	private boolean isNewFile(File f) { 
		try {
			return f.createNewFile(); //존재하는 파일이 아니면 true(생성가능)
		}catch (IOException ignored) {
			return false;
		}
	} //isNewFile(File f) end
	
	/**
	 * 파일 리네임
	 * @param File f
	 * @return File f (리네임후 파일)
	 */
	public File renameFile(File f){//File f는 원본 파일
     
		String name = f.getName();
		String body = null;
		String ext = null;
	 
		int dot = name.lastIndexOf(".");
		if (dot != -1) {//확장자가 없을때
		  body = name.substring(0, dot);
		  ext = name.substring(dot);
		} else { //확장자가 있을때
			body = name;
			ext = "";
		} //확장자 관련(else) 조건문 끝
 
		//이름에 붙일 시간 구하기
		Date timestamp = new Date(System.currentTimeMillis()); //시간 구하기(밀리세컨드까지)
		SimpleDateFormat timeSdf = new SimpleDateFormat ("yyyy-MM-dd_hhMMss_sss"); //포맷변경
	
		String newNameTime = body + "_" + timeSdf.format(timestamp)+ext; //기존파일명_시간+확장자
		f = new File(f.getParent(), newNameTime);
		
		if(isNewFile(f)) { //중복 없다면 시간만 붙이기	
			
			return f;
			
		}else{ //중복된 파일이 있을때 파일이름뒤에 (2~9999)까지 붙이기
			
			int count=2;
			
			while (!isNewFile(f) && count < 9999) {
				
				count++;
				String newNameIndex = 
						body + "_" + timeSdf.format(timestamp) + "(" + count + ")" + ext;
				
				f = new File(f.getParent(), newNameIndex);
				
			}//while end
		}//NameIndex(else) end

		 return f;

	}//rename(File f) end
	
	///////////////////////////////////////////////////////////
	
	/**
	 * 이름만 입력받아 파일이 있는지 확인하기
	 * @param String path
	 * @param String name
	 * @return 없으면 true(사용가능), 있으면 false(사용불가)
	 */
	private boolean isNewName(String path, String name) {
		File f=new File(path+"/"+name);
		try {
			return f.createNewFile(); //존재하는 파일이 아니면 true(생성가능)
		}catch (IOException ignored) {
			return false;
		}
	} //isNewName(String path, String name) end
	
	/**
	 * rename만하기 (경로+이름만 받기)
	 * @param String file, String name
	 * @return String (리네임후 경로+이름)
	 */
	public String newNameOnly(String path, String name){
     
		String body = null;
		String ext = null;
	 
		int dot = name.lastIndexOf(".");
		if (dot != -1) {//확장자가 없을때
		  body = name.substring(0, dot);
		  ext = name.substring(dot);
		} else { //확장자가 있을때
			body = name;
			ext = "";
		} //확장자 관련(else) 조건문 끝
 
		//이름에 붙일 시간 구하기
		Date timestamp = new Date(System.currentTimeMillis()); //시간 구하기(밀리세컨드까지)
		SimpleDateFormat timeSdf = new SimpleDateFormat ("yyyy-MM-dd_hhMMss_sss"); //포맷변경
	
		String newNameTime = body + "_" + timeSdf.format(timestamp)+ext; //기존파일명_시간+확장자
		
		if(isNewName(path, newNameTime)==false) {
			//중복된 파일이 있을때 파일이름뒤에 (2~9999)까지 붙이기
			
			int count=2;
			
			while (isNewName(path, newNameTime) && count < 9999) {
				
				count++;
				
			}//while끝
			
			String newNameIndex = 
					body + "_" + timeSdf.format(timestamp) + "(" + count + ")" + ext;
			
			return newNameIndex;
			
		}//if(isNewName(path, newNameTime)==false) end

		 return newNameTime;

	}//newNameOnly(String path, String name) end

}//class end