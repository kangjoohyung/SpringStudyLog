package kosta.web.mvc.views;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

/**
 * 파일다운로드 기능 구현 view
 * */
@Component("downLoadView") //생성 <bean class=""  id="downLoadView"/> 대신
public class DownLoadCustomView extends AbstractView{ 
	//AbstractView : view페이지와 같은 역할임. -> 상속받은 클래스도 뷰역할

	@Override
	protected void renderMergedOutputModel(Map<String, Object> map,
		HttpServletRequest request, HttpServletResponse response) throws Exception {

		//여기서 PrintWriter out=response.getWriter();
		//out.println("hello");  식으로 출력할수 있는 view역할 클래스
		
		File file = (File) map.get("fname");//파일객체....(다운로드할 파일객체) ->model자리로 들어온 인수명
		
		response.setContentType("application/download;charset-UTF-8");
		response.setContentLength((int)file.length());
	
		
		String userAgent = request.getHeader("User-Agent");
		
		boolean isInternetExplorer = userAgent.indexOf("MSIE") > -1; //찾는 메소드
		String fileName = null;
		
		if(isInternetExplorer) //explorer일때 찾기
			fileName = URLEncoder.encode(file.getName() , "UTF-8");
		else //그 외 브라우저
			fileName = new String(file.getName().getBytes("UTF-8") , "iso-8859-1") ;
		
		
		response.setHeader("Content-Disposition","attachment;filename=\"" + fileName.replace("+", "%20") + "\";");
		//response.setHeader("Content-Transfer-Encoding", "binary");
		
		OutputStream out = response.getOutputStream(); //저장기능
		FileInputStream fis = null;
		try{
			fis = new FileInputStream(file);
			FileCopyUtils.copy(fis, out); //스프링에서 제공하는 메소드(copy)-인수는 InputStream, OutputStream
			
		}catch(Exception e){
			//map.put("error", e.toString());
			e.printStackTrace();
		}
		finally{
			if(fis != null){
				try{
					fis.close();
				}
				catch(IOException ex){}
			}
		}		
		out.flush();
		
		
	}



	
}







