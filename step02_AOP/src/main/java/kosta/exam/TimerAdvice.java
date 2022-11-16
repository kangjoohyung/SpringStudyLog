package kosta.exam;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.util.StopWatch;

/**
 * 공통기능 : around방식(사전,사후)
 * 
 * -사전처리 : 현재시간을 구한다.
 * -사후처리 : 현재시간을 구해서 사후시간-사전시간=총소요시간 구한다
 *
 */
public class TimerAdvice {
	
	/**
	 * -JoinPoint : 실제 타겟 대상, 즉 메소드에 대한 정보를 가져올 수 있는 메소드를 제공한다.
	 * -ProceedingJoinPoint : JoinPoint의 확장개념으로 메소드에 대한 정보도 제공해 주고 
	 * 						 타겟대상 또는 다음 advice를 호출할 수 있는 메소드를 제공한다.
	 * 인수 : ProceedingJoinPoint
	 * 리턴 : Object-타겟대상이 리턴한 값
	 */
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable{
		//사전처리
		String methodName=joinPoint.getSignature().getName();
		
		System.out.println("[LOG]"+methodName+"호출되기 전 사전처리 입니다.\n스탑워치 start");
		
		StopWatch sw=new StopWatch();//스프링에서 제공하는 유틸리티 클래스의 메소드
		sw.start(); //스탑워치 스타트
		
		//실제 타겟대상을 호출(사전-사후 사이에 꼭 필요)
		Object obj=joinPoint.proceed(); //Throwable 익셉션 발생, 보통 직접 처리안하고 던짐(위에 throws추가)
		
		//사후처리
		sw.stop();
		System.out.println("[LOG]"+methodName+"리턴값 obj="+obj+
				"\n\t"+methodName+"의 총 실행 ms="+sw.getTotalTimeMillis()+"ms"
				+"\n\t"+methodName+"의 호출 완료 후 사후처리 완료");
		
		return obj;
	}
}
