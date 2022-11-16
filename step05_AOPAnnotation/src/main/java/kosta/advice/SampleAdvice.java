package kosta.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

@Service //id="sampleAdvice"
@Aspect
@Order(2)
public class SampleAdvice {
	/**
	 * 사전처리 : before
	 * 타겟대상을 받을필요 없을때는 그냥 joinPoint만 인수에 넣기
	 * ->aspectj.lang으로 임포트
	 */
	@Before("PointCutDefinition.aa()")
	public void before(JoinPoint joinPoint) {
		String methodName=joinPoint.getSignature().getName();
		Object[] params=joinPoint.getArgs();
		System.out.println(methodName+"의 인수의 개수는"+params.length);
		if(params.length>0) {
			for(Object o : params) {
				System.out.println(methodName+"의 인수 : "+o);
			}
		}
		
		System.out.println(methodName+" 호출되기 전 before입니다\n");
	}
	
	/**
	 * 사후처리 : after-returning 예외발생 안했을때(정상동작)
	 * 
	 * 인수로 리턴값을 받기 위해서는 aop등록할때 
	 * returning="obj"필수
	 */
	@AfterReturning(pointcut = "PointCutDefinition.aa()", returning = "obj")
	public void afterReturning(JoinPoint joinPoint, Object obj) {//인수 순서는 상관없다
		String methodName=joinPoint.getSignature().getName();
		System.out.println(methodName+"의 리턴값은"+obj);
		System.out.println(methodName+"의 after-returning(예외없이 정상동작) 실행중\n");
		
	}
	
	/**
	 * 사후처리 : after-throwing 예외발생 했을때
	 * 인수로 예외정보를받기 위해서는 aop설정할때
	 * throwing="e" 필수
	 */
	@AfterThrowing(pointcut = "PointCutDefinition.aa()", throwing = "e")
	public void afterThrowing(Throwable e) {
		System.out.println("예외정보 : "+e);
		System.out.println("예외가 발생했을때 after-throwing 실행중");
		
	}
	
	/**
	 * 사후처리 : after 예외발생 여부와 상관없이 무조건
	 */
	@After("PointCutDefinition.aa()")
	public void afterFinally() {
		System.out.println("예외발생 여부 상관없이 무조건 사후처리");
	}
}
