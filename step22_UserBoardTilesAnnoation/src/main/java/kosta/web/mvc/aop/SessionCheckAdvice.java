package kosta.web.mvc.aop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * board���� �˻�,��, ���,����,�������� ��û�Ҷ� �ݵ�� �α��ε� ����ڸ�
 * �����Ҽ� �ֵ��� ����ó������ ��������üũ���Ѵ�. 
 * ����, ������ ���� �ʾҴٸ� ������������ �̵�
 * */
@Service
@Aspect
public class SessionCheckAdvice {

	/*@Before("execution(public * kosta.web.mvc.board.controller.BoardController.*(..))")
	public void before(JoinPoint joinPoint ) {
		//��������üũ - ���ǿ� loginUser ������ �ִ��� ã��!!!
		Object [] params = joinPoint.getArgs();
		
		HttpSession session = (HttpSession)params[0];
		if(session==null || session.getAttribute("loginUser") == null) {
			throw new RuntimeException("�α����ϰ� �̿����ּ���!!!!");
		}
	}*/
	////////////////////////////////////////////////////////////////////
	/**
	 *  Controller�� (joinpoint���) ù��° �μ��� HttpSession�� �������� �ʰ�
	 *  Service or dao �������� HttpServletRequest������ ����Ҽ� �ֵ��� �ϴ� ��� 
	 *    : spring 2.x �̻���� Service or dao ���� ���� HttpServletRequest�� ����Ҽ� �ֵ���
	 *      RequestContextHolder �� �����Ѵ�. 
	 * */
	@Before("execution(public * kosta.web.mvc.board.controller.BoardController.*(..))")
	public void before( ) {
		//��������üũ - ���ǿ� loginUser ������ �ִ��� ã��!!!
		RequestAttributes requestAttr = RequestContextHolder.getRequestAttributes();
		ServletRequestAttributes sra = (ServletRequestAttributes)requestAttr;
		HttpServletRequest request = sra.getRequest();
		
		HttpSession session = request.getSession();
		if(session==null || session.getAttribute("loginUser") == null) {
			throw new RuntimeException("�α����ϰ� �̿����ּ���!!!!");
		}
	}
}
















