package kosta.mvc.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String key=request.getParameter("key");
		System.out.println("key="+key);
		
		//key에 해당하는 Controller를 찾아서 호출하고 그 결과를 받아서 뷰로 이동한다.
		// ->이전에는 리스너로 만들어서 맵으로 찾았음->이번엔 패턴 변경
		Controller con=HandlerMapping.getFactory().getController(key);
		
		ModelAndView mv=con.handleRequest(request, response);
		
		if(mv.isRedirect()) {//redirect방식
			response.sendRedirect(mv.getViewName());
		}else {//forward방식
			request.getRequestDispatcher(mv.getViewName()).forward(request, response);
		}
		
	}

}
