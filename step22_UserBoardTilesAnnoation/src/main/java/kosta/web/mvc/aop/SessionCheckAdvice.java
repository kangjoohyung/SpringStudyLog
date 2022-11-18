package kosta.web.mvc.aop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * board쪽의 검색,상세, 등록,수정,삭제등을 요청할때 반드시 로그인된 사용자만
 * 접근할수 있도록 사전처리에서 세션유무체크를한다. 
 * 만약, 인증이 되지 않았다면 에러페이지로 이동
 * */
@Service
@Aspect
public class SessionCheckAdvice {
	
//	@Autowired
//	private HttpSession session; //주입하게되면 session이 공유가 되기때문에, 생기는 문제들이 있을수있음
//	@Autowired
//	private HttpServletRequest request; //마찬가지 -> 구조상 문제로 주입사용하면 안됨
//->Controller에서만 사용하려는 영역이기때문에, service까지 와서 접근하면 안됨.
//->현재 AOP영역이어서, 주입받으면 안됨 ->그래서 RequestContextHolder로 그때그때 쓰고 끝낼수있게 함
//(그럼 컨트롤러에서는 주입 되는듯?)

	/*@Before("execution(public * kosta.web.mvc.board.controller.BoardController.*(..))")
	public void before(JoinPoint joinPoint ) {
		//세션유무체크 - 세션에 loginUser 정보가 있는지 찾기!!!
		Object [] params = joinPoint.getArgs();
		
		HttpSession session = (HttpSession)params[0];
		if(session==null || session.getAttribute("loginUser") == null) {
			throw new RuntimeException("로그인하고 이용해주세요!!!!");
		}
	}*/
	////////////////////////////////////////////////////////////////////
	/**
	 *  Controller의 (joinpoint대상) 첫번째 인수로 HttpSession을 전달하지 않고
	 *  Service or dao 영역에서 HttpServletRequest정보를 사용할수 있도록 하는 방법 
	 *    : spring 2.x 이상부터 Service or dao 영역 에서 HttpServletRequest를 사용할수 있도록
	 *      RequestContextHolder 를 제공한다. 
	 * */
	@Before("execution(public * kosta.web.mvc.board.controller.BoardController.*(..))")
	public void before( ) {
		//세션유무체크 - 세션에 loginUser 정보가 있는지 찾기!!!
		RequestAttributes requestAttr = RequestContextHolder.getRequestAttributes();
		ServletRequestAttributes sra = (ServletRequestAttributes)requestAttr;
		HttpServletRequest request = sra.getRequest();
		
		HttpSession session = request.getSession();
		if(session==null || session.getAttribute("loginUser") == null) {
			throw new RuntimeException("로그인하고 이용해주세요!!!!");
		}
	}
}
















