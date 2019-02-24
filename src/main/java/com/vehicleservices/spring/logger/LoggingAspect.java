package com.vehicleservices.spring.logger;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author Chandrasekaran 
 * 
 * @version 1.0
 *
 */
@Aspect
@Component
public class LoggingAspect {
	private static Logger logger = Logger.getLogger(LoggingAspect.class);

	@Pointcut("execution(* com.vehicleservices.spring.*.*(..))")

	public void selectAll() {
	}

	@Before("selectAll()")
	public void logBefore(JoinPoint joinPoint) {
		logger.info("logBefore() " + System.currentTimeMillis());

		logger.info("************************* logBefore ******************************************");
		logger.info("Class Name : " + joinPoint.getClass().getName());
		logger.info("Method Name : " + joinPoint.getSignature().getName());
		logger.info("Fields Name : " + joinPoint.getClass().getFields());

	}

	@After("selectAll()")
	public void logAfter(JoinPoint joinPoint) {
		logger.info("************************* logAfter ******************************************");
		logger.info("@After:" + System.currentTimeMillis());
		logger.info("Name : " + joinPoint.getSignature().getName());

	}

	@Around("execution(* com.vehicleservices.spring.*.*(..))")
	public Object aroundWebMethodE(ProceedingJoinPoint pjp) throws Throwable {
		logger.info("************************* AroundWebMethod ******************************************");
		String packageName = pjp.getSignature().getDeclaringTypeName();
		String methodName = pjp.getSignature().getName();
		long start = System.currentTimeMillis();
		if (!pjp.getSignature().getName().equals("initBinder")) {
			logger.info("Entering method [" + packageName + "." + methodName + "]");
		}
		Object output = pjp.proceed();
		long elapsedTime = System.currentTimeMillis() - start;
		if (!methodName.equals("initBinder")) {
			logger.info("Exiting method [" + packageName + "." + methodName + "]; exec time (ms): " + elapsedTime);
		}
		return output;
	}

	@AfterReturning(pointcut = "execution(* com.vehicleservices.spring.*.*(..))", returning = "val")
	public void logAfterReturning(Object val) {
		logger.info("************************* logAfterReturning ******************************************");
		logger.info("logAfterReturning() " + System.currentTimeMillis());
		logger.info("Method return value:" + val);

	}

	@AfterThrowing(pointcut = "execution(* com.vehicleservices.spring.*.*(..))", throwing = "exception")
	public void logAfterThrowing(Exception exception) {
		logger.info("************************* logAfterThrowing   ******************************************");
		logger.info("logAfterThrowing() " + System.currentTimeMillis());
		logger.info("Exception caught:" + exception.getMessage());
		logger.error("Error description" + exception);

		exception.printStackTrace();
		StringWriter stack = new StringWriter();
		exception.printStackTrace(new PrintWriter(stack));
		logger.info("logAfterThrowing" + stack.toString());
	}
}
