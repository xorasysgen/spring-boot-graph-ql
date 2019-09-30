package api.graph.ql.logging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExecutionTracker {
	
	private Logger logger=LoggerFactory.getLogger(ExecutionTracker.class.getName());
	
	@Around("@annotation(api.graph.ql.annotation.ExecutionTimeDetector)")
	public Object calulateExecutionTime(ProceedingJoinPoint joinPoint) {
		Object object = null;
		long begin = System.currentTimeMillis();
		try {
			object = joinPoint.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		logger.info("Method " + joinPoint.getSignature().getName() + " of " +   joinPoint.getTarget().getClass().toString() + " taken taken : [" + (end - begin) + "] ms");
		return object;

	}
}
