package com.java.spring.aop.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TimeLoggingAnnotation {

    @Around("@annotation(LogTieExecution)")
    public Object logging(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object obj = joinPoint.proceed();
        long meanTime = System.currentTimeMillis() - start;
        System.out.printf("Execution time for %s is :: %s ms", joinPoint.getSignature(), meanTime);
        return obj;
    }
}
