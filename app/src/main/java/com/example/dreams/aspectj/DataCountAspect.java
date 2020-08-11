package com.example.dreams.aspectj;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * Created by likaiyu on 2020/3/27.
 */
@Aspect
public class DataCountAspect {

    @Pointcut("execution(@com.example.dreams.aspectj.DataCount * *(..))")
    public void dataCountPoint() {
    }

    @After("dataCountPoint()")
    public void dataCount(JoinPoint joinPoint) throws Throwable{
//        Context context = (Context) joinPoint.getThis();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        if(method.isAnnotationPresent(DataCount.class)){
            DataCount annotation = method.getAnnotation(DataCount.class);
        }

    }

}
