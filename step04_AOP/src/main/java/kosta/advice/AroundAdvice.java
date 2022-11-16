package kosta.advice;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * 사전, 사후 처리
 *
 */
public class AroundAdvice {
	
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		
		System.out.println("around방식의 사전처리 입니다");
		
		Object obj=joinPoint.proceed();
		
		System.out.println("\naround방식의 사후처리 입니다.");
		
		return obj;
	}
}
