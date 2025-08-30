package com.dev.sbWebapp.global.log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class LogExecutionAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogExecutionAspect.class.getSimpleName());

    @Around("@annotation(logExecution)")
    public Object logExecution(ProceedingJoinPoint joinPoint, LogExecution logExecution) throws Throwable {
        String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
        String methodName = joinPoint.getSignature().getName();

        // 메서드 실행 전
        LOGGER.info("================ {} {} START ================", className, methodName);
        // 메서드 실행
        Object result = joinPoint.proceed();
        // 메서드 실행 후
        LOGGER.info("================ {} {} END ================", className, methodName);

        return result;
    }
}