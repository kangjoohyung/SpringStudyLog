package kosta.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

/**
 * 사전, 사후 처리
 *
 */
@Service //id="aroundAdvice"
@Aspect
@Order(1)
public class AroundAdvice {
	
	@Around("PointCutDefinition.aa()")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		
		System.out.println("around방식의 사전처리 입니다");
		
		Object obj=joinPoint.proceed();
		
		System.out.println("\naround방식의 사후처리 입니다.");
		
		return obj;
	}
}
