package com.shiny.aop.monitor;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author shiny
 **/
@Aspect
@Component
public class ServiceMonitor {

    @AfterReturning("execution(* com.shiny.aop.service.impl..*(..))")
    public void logServiceAccess(JoinPoint joinPoint){
        System.out.println("Completed: "+joinPoint);
    }
}