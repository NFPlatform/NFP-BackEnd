package com.nfplatform.nfpbackend.security.aspect;

import com.nfplatform.nfpbackend.user.repository.entity.User;
import com.nfplatform.nfpbackend.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Optional;

@Aspect
@Component
@RequiredArgsConstructor
public class TokenParsingAspect {

    private final UserService userService;

    @Around("execution(* com.nfplatform.nfpbackend.*.*.*.*(.., @com.nfplatform.nfpbackend.security.annotation.ParseUser (*), ..))")
    public Object parseToken(ProceedingJoinPoint point) throws Throwable {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String authHeader = Optional.ofNullable(request.getHeader("Authorization"))
                .orElseThrow(Exception::new);

        if (authHeader.split(" ")[0].equals("Token") == false) {
            throw new Exception();
        }

        String token = authHeader.split(" ")[1];

        User user = userService.getUserFromToken(token);

        Object[] args = Arrays.stream(point.getArgs())
                .map(data -> {
                    if (data instanceof User) {
                        data = user;
                    }
                    return data;
                }).toArray();

        return point.proceed(args);
    }
}
