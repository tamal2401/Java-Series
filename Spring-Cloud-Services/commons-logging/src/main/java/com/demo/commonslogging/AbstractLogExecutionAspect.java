package com.demo.commonslogging;

import com.google.common.base.Stopwatch;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Aspect
@Component
public abstract class AbstractLogExecutionAspect {

    private final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private static final String CLAZZ = "class";
    private static final String ACTION = "action";
    private static final String ELAPSE_TIME = "elapsedTime";
    private static final String DURATION = "duration";

    @Around("@annotation(com.demo.commonslogging.LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        Stopwatch timer = Stopwatch.createStarted();
        Object execVal = joinPoint.proceed();
        timer.stop();

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Arrays.stream(signature.getMethod().getAnnotations())
                .filter(annotation -> annotation instanceof LogExecutionTime)
                .forEach(annotation -> {
                    String action = StringUtils.defaultIfBlank(((LogExecutionTime) annotation).action(), joinPoint.getSignature().getName());
                    String clazz = joinPoint.getSignature().getDeclaringType().getName();
                    long duration = timer.elapsed(TimeUnit.MILLISECONDS);

                    MDC.put(CLAZZ, clazz);
                    MDC.put(ACTION, action);
                    MDC.put(DURATION, String.valueOf(duration));
                    MDC.put(ELAPSE_TIME, String.valueOf(duration));

                    LoggingUtil.logTiming("Executing from {}, Executing method {}, Execution time {} ms", clazz, action, duration);

                    MDC.remove(CLAZZ);
                    MDC.remove(ACTION);
                    MDC.remove(DURATION);
                    MDC.remove(ELAPSE_TIME);

                });
        return execVal;
    }
}
