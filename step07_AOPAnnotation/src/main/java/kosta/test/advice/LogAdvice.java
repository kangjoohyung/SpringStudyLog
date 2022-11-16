package kosta.test.advice;

import java.io.FileWriter;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

@Service("advice")
@Aspect
public class LogAdvice {
   private FileWriter fw;
   
   public LogAdvice() {
	   try {
	    fw = new FileWriter("src/main/java/kosta/test/advice/log.txt" , true);//이어쓰기
	   }catch (Exception e) {
		e.printStackTrace();
	  }
   }
   
   @Around("execution(public * kosta.test.*.*Impl.*(..))")
   public Object around(ProceedingJoinPoint joinPoint)throws Throwable {
	   String methodName = joinPoint.getSignature().getName();
	   int i = joinPoint.getArgs().length;
	   
	   StopWatch sw = new StopWatch();
	   sw.start();
	    
	   Object obj = joinPoint.proceed();//joinPoint호출=target호출 or next adivce호출
	   sw.stop();
	   
	   StringBuilder sb = new StringBuilder();
	   sb.append(methodName +" / 인수의 개수 " + i );
	   sb.append(" / 리턴값 : " + obj );	   
	   sb.append(" / 총 실행 nanos : /" + sw.getTotalTimeNanos()+ "nanos");
	  
	   fw.write(sb.toString()+"\n");
	   fw.flush();
	   
	   return obj;
	   
   }
}







