package kosta.mvc.controller;

import java.io.File;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import kosta.mvc.dto.UploadDTO;
import kosta.mvc.util.FileRenameTimeIndex;

@Controller
public class UploadController {

	/**
	 * MultipartFile�� ����ϱ� ���ؼ��� bean���� CommonsMultipartFileResolver�� ����ʼ�
	 * 
	 * @param name
	 * @param file
	 * @param session
	 * @return ModelAndView
	 */
	@RequestMapping("/upload")
	public ModelAndView upload(String name, MultipartFile file, HttpSession session) { 
		//<input type="file" name="file" , name�� ���� �μ���(file) ���ƾ���
		
		//String saveDir="C://Edu";
		
		//session �μ��� �ְ��� ��������
		String saveDir=session.getServletContext().getRealPath("/WEB-INF/save");
		
//		String originalFileName=file.getOriginalFilename(); // ÷���� ���� �̸�
		String newName=new FileRenameTimeIndex().newNameOnly(saveDir, file.getOriginalFilename());
		
		try {
			file.transferTo(new File(saveDir+"/" + newName));//����(���ε�)
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		ModelAndView mv=new ModelAndView();
		
		mv.addObject("name", name);
		mv.addObject("saveDir", saveDir);
		mv.addObject("originalFileName", newName);
		mv.addObject("fileSize", file.getSize());
		
		mv.setViewName("uploadResult"); //�̵� : WEB-INF/views/uploadResult.jsp
		return mv;
	}
	
	@RequestMapping("/upload2")
	public String upload2(UploadDTO upload, HttpSession session ) {
		
		String saveDir=session.getServletContext().getRealPath("/WEB-INF/save");
		
		String originalFileName=upload.getFile().getOriginalFilename(); // ÷���� ���� �̸�
		upload.setFileName(originalFileName);
		upload.setFileSize(upload.getFile().getSize());
		
		try {
			upload.getFile().transferTo(new File(saveDir+"/"+originalFileName));//����(���ε�)
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return "uploadResult";
	}
	
	/**
	 * �ٿ�ε� �׸� ��������
	 */
	@RequestMapping("/downList")
	public ModelAndView downList(HttpSession session) { //�����н��� �ޱ����� session �μ��ֱ�
		//save�����ȿ� �ִ� �����̸��� ������ Model�� �����ϰ� ��� �̵�
		String path=session.getServletContext().getRealPath("/WEB-INF/save");
		File file=new File(path); //save��θ� �μ��� ����� ���ϵ� ��ü�� ���
		String fileNames[]=file.list();//������ ���� ���ϵ��� ����Ʈ�� �迭�� ���
		
		return new ModelAndView("downList", "fileNames", fileNames);
	}
	
	/**
	 * �ٿ�ε� �ϱ�
	 */
	@RequestMapping("/down")
	public ModelAndView down(String fileName, HttpSession session) {
		String path=session.getServletContext().getRealPath("/WEB-INF/save");
		
		//������ ��θ��� bean�� �̸����� ã�ư��� �ϱ�
		//���� �̸��� bean�̸��� ������ ��ü�� ã�Ƽ� �䰡 ����ȴ�.
		return new ModelAndView("downLoadView", "fname", new File(path+"/"+fileName)); 
	}
}
