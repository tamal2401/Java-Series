package com.example.demo.cacheannotation.annotationimplementation;

import com.example.demo.cacheannotation.CacheTTL;
import com.example.demo.cacheannotation.service.CacheService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.aspectj.weaver.tools.cache.CacheKeyResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
@Aspect
public class CacheTtlAspect {

    @Autowired
    CacheService service;

    @Around("@annotation(CacheTTL)")
    public Object implementCache(ProceedingJoinPoint joinPoint) throws Throwable {

        // getting method for the joinpoint
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();

        // gwtting method argsuments
        Object[] args = joinPoint.getArgs();

        // generating KEY with the method name and arguments
        String key = Keygenerator.generateKey(method.getName(), args);

        // getting ReturnObject from the generated KEY anr return type
        Object returnObject = service.cacheGet(key, method.getReturnType());

        // check if reurn object is not NULL then return the return object
        if(returnObject!=null) return returnObject;

        // execute method nd get the return object
        returnObject = joinPoint.proceed(args);

        // get annotation instance for the method and get properties for that instance
        CacheTTL annotaton = method.getAnnotation(CacheTTL.class);

        // put the key, return object and ttlMinutes limit in the cache
        service.cachePut(key, returnObject, annotaton.ttlMinutes());

        return returnObject;
    }
}
