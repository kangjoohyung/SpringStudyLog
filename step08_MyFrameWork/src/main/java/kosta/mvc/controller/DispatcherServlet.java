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
		
		//key�� �ش��ϴ� Controller�� ã�Ƽ� ȣ���ϰ� �� ����� �޾Ƽ� ��� �̵��Ѵ�.
		// ->�������� �����ʷ� ���� ������ ã����->�̹��� ���� ����
		Controller con=HandlerMapping.getFactory().getController(key);
		
		ModelAndView mv=con.handleRequest(request, response);
		
		if(mv.isRedirect()) {//redirect���
			response.sendRedirect(mv.getViewName());
		}else {//forward���
			request.getRequestDispatcher(mv.getViewName()).forward(request, response);
		}
		
	}

}
