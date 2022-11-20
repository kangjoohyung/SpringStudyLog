package kosta.mvc.controller;

import java.io.File;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import kosta.mvc.dto.UploadDTO;

@Controller
public class UploadController {

	/**
	 * MultipartFile을 사용하기 위해서는 bean으로 CommonsMultipartFileResolver를 등록필수
	 * 
	 * @param name
	 * @param file
	 * @param session
	 * @return ModelAndView
	 */
	@RequestMapping("upload.do")
	public ModelAndView upload(String name, MultipartFile file, HttpSession session) { 
		//<input type="file" name="file" , name과 여기 인수명(file) 같아야함
		
		//String saveDir="C://Edu";
		
		//session 인수에 넣고나서 꺼내오기
		String saveDir=session.getServletContext().getRealPath("/WEB-INF/save");
		
		String originalFileName=file.getOriginalFilename(); // 첨부한 파일 이름
		
		try {
			file.transferTo(new File(saveDir+"/"+originalFileName));//저장(업로드)
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		ModelAndView mv=new ModelAndView();
		
		mv.addObject("name", name);
		mv.addObject("saveDir", saveDir);
		mv.addObject("originalFileName", originalFileName);
		mv.addObject("fileSize", file.getSize());
		
		mv.setViewName("uploadResult"); //이동 : WEB-INF/views/uploadResult.jsp
		return mv;
	}
	
	@RequestMapping("/upload2.do")
	public String upload2(UploadDTO upload, HttpSession session ) {
		
		String saveDir=session.getServletContext().getRealPath("/WEB-INF/save");
		
		String originalFileName=upload.getFile().getOriginalFilename(); // 첨부한 파일 이름
		upload.setFileName(originalFileName);
		upload.setFileSize(upload.getFile().getSize());
		
		try {
			upload.getFile().transferTo(new File(saveDir+"/"+originalFileName));//저장(업로드)
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return "uploadResult";
	}
	
	/**
	 * 다운로드 항목 가져오기
	 */
	@RequestMapping("/downList.do")
	public ModelAndView downList(HttpSession session) { //리얼패스로 받기위해 session 인수넣기
		//save폴더안에 있는 파일이름의 정보를 Model에 저장하고 뷰로 이동
		String path=session.getServletContext().getRealPath("/WEB-INF/save");
		File file=new File(path); //save경로를 인수로 경로의 파일들 객체에 담기
		String fileNames[]=file.list();//위에서 만든 파일들의 리스트를 배열로 담기
		
		return new ModelAndView("downList", "fileNames", fileNames);
	}
	
	/**
	 * 다운로드 하기
	 */
	@RequestMapping("/down.do")
	public ModelAndView down(String fileName, HttpSession session) {
		String path=session.getServletContext().getRealPath("/WEB-INF/save");
		
		//원래의 경로말고 bean의 이름으로 찾아가게 하기
		//뷰의 이름이 bean이름과 동일한 객체를 찾아서 뷰가 실행된다.
		return new ModelAndView("downLoadView", "fname", new File(path+"/"+fileName)); 
	}
}
