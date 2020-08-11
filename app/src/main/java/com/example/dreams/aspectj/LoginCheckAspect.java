package com.example.dreams.aspectj;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.dreams.FlowLayoutActivity;
import com.example.dreams.PracticeMakesPerfectActivity;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * Created by likaiyu on 2020/3/27.
 */
@Aspect
public class LoginCheckAspect {

    @Pointcut("execution(@com.example.dreams.aspectj.LoginCheck * *(..))")
    public void clickPoint() {
    }

    @Around("clickPoint()")
    public Object checkLogin(ProceedingJoinPoint joinPoint) throws Throwable{
        Context context = (Context) joinPoint.getThis();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
//        if(method.isAnnotationPresent(LoginCheck.class)){
//            LoginCheck annotation = method.getAnnotation(LoginCheck.class);
//            System.out.println("LoginCheckAspect "+method.getName()+" need login");
//        }
        if(true){
            return joinPoint.proceed();
        }else{
            System.out.println("LoginCheckAspect "+method.getName()+" need login");
            context.startActivity(new Intent(context, FlowLayoutActivity.class));
            return null;
        }
    }

}
