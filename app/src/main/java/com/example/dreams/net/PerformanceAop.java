package com.example.dreams.net;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * Created by likaiyu on 2020/2/15.
 */
@Aspect
public class PerformanceAop {
    @Around("call(* com.example.dreams.net.MApplication.**(..))")
    public void getTime(ProceedingJoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();

        long time = System.currentTimeMillis();
        try {
            joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println(signature.getName() +" cost " + (System.currentTimeMillis() - time));
    }
}
