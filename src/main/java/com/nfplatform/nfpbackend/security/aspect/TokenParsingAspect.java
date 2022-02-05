package com.nfplatform.nfpbackend.security.aspect;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class TokenParsingAspect {

    @Around("execution(* com.nfplatform.nfpbackend.*.*.*.*(.., @com.nfplatform.nfpbackend.security.annotation.ParseUser (*), ..))")
    public Object parseToken(ProceedingJoinPoint point) throws Throwable {
        return point.proceed();
    }
}
