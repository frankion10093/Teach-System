package com.itheima.aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
@Order(1)
public class TimeAspect {


    @Pointcut("execution(* com.itheima.service.*.*(..) )")
    public void pu() {}

    @Before("@annotation(com.itheima.anno.MyLog)")
    public void before(){
        log.info("Befroe>>>>>");
    }

    @After("pu()")
    public void after(JoinPoint joinPoint ){
        log.info("After>>>>>");
    }

//    @Around("execution(* com.itheima.service.DeptService.*(..))")

    @Around("pu()")
    public Object recordTime(ProceedingJoinPoint joinPoint) throws Throwable {

        String name = joinPoint.getTarget().getClass().getName();
        log.info("目标对象的类名{}",name);

        //获取开始时间
        Long startTime = System.currentTimeMillis();
        //调用方法
        Object result = joinPoint.proceed();
        //调用之后获取时间
        Long endTime = System.currentTimeMillis();
        //getnature()获取方法名
        System.out.println(joinPoint.getSignature() + "执行时间：" + (endTime - startTime) + "ms");

        return result;

    }


}
