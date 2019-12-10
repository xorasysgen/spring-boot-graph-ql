package api.graph.ql.logging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

@Aspect
@Component
public class LogAdvice {
	private static Logger logger=LoggerFactory.getLogger(LogAdvice.class);
	
	
	
	@Before(value="execution(* api.graph.ql.service.*.*(..) )")
	public void before() {
		logger.warn("before aspect calling");
	}
	
	@After(value="execution(* api.graph.ql.service.*.*(..) )")
	public void after() {
		logger.warn("After aspect calling");
	}
	
	@Pointcut(value ="execution(* api.graph.ql.*.*.*(..) )")
	public  void pointcut() {
	} 
	
	@Around(value = "pointcut()") //@Around(value = "execution(* api.graph.ql.*.*.*(..) )")
	public Object globalLogger(ProceedingJoinPoint proceedingJoinPoint ) {
		logger.debug("->>> Pointcut execution <<<-");
		String methodName=proceedingJoinPoint.getSignature().getName();//get method name
		String classname=proceedingJoinPoint.getTarget().getClass().toString();// get class name
		Object[] args=proceedingJoinPoint.getArgs();
		Gson gson=new Gson();
		ObjectMapper objectMapper=new ObjectMapper();
		String tojson= gson.toJson(args);
		logger.info(" [" + classname + " ] class invoked ");
		logger.info(" [" + methodName + " ] method invoked");
		logger.info(" [" + tojson + " ] arguments");
		Object proceed=new Object();
		try {
			proceed = proceedingJoinPoint.proceed(); // interceptor proceed
		} catch (Throwable e) {
			e.printStackTrace();
		}
		logger.info(" [" + classname + " ] class invoked ");
		logger.info(" [" + methodName + " ] method invoked");
		try {
			logger.info("Response#" + objectMapper.writeValueAsString(proceed));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return proceed;
		
	}
	
	

}
