package kosta.advice;

import org.aspectj.lang.annotation.Pointcut;

/**
 * pointCut을 정의하는 클래스
 *
 */
public class PointCutDefinition {
	
	@Pointcut("execution(public * kosta.exam.*Impl.*(..))")
	public void aa() {}
	
	@Pointcut("execution(public void kosta.exam.*Impl.*(..))")
	public void bb() {}
	
	@Pointcut("execution(public String kosta.exam.*Impl.*(..))")
	public void cc() {}
	
}
