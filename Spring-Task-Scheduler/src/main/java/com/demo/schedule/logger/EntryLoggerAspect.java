package com.demo.schedule.logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Component
@EnableAspectJAutoProxy
public class EntryLoggerAspect {

    @Around("@annotation(com.demo.schedule.logger.LoggerEntry)")
    public Object entryExitLog(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        final Logger logger = LoggerFactory.getLogger(EntryLoggerAspect.class.getName());
        logger.info(">>> Enter {} {} ", proceedingJoinPoint.getSignature().getDeclaringTypeName(), proceedingJoinPoint.getSignature().getName());
        Object proceed = proceedingJoinPoint.proceed();
        logger.info("<<< Exit {} {} ", proceedingJoinPoint.getSignature().getDeclaringTypeName(), proceedingJoinPoint.getSignature().getName());
        return proceed;
    }
}
