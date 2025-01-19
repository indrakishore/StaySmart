package com.indra.StaySmart.controller;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    @Before("execution(* com.indra.StaySmart.controller.*.*(..))")
    public void logBefore() {
        System.out.println("Before method execution");
    }
}
