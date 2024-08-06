package com.library.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    // Define advice to log before method execution
    @Before("execution(* com.library.*.*(..))")
    public void logBefore() {
        System.out.println("Logging before method execution");
    }

    // Define advice to log after method execution
    @After("execution(* com.library.*.*(..))")
    public void logAfter() {
        System.out.println("Logging after method execution");
    }
}
