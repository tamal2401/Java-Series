package com.demo.commonslogging;

import com.google.common.base.Stopwatch;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public abstract class AbstractLogExecutionAspect {

    @Around("@annotation(com.demo.commonslogging.LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        Stopwatch timer = Stopwatch.createStarted();

        Object execVal = joinPoint.proceed();

        timer.stop();


        return  null;
    }
}
