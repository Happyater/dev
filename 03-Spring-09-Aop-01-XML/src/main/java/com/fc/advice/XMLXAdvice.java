package com.fc.advice;

public class XMLXAdvice {
    public void before(){
        System.out.println("前置通知");
    }
    public void afterReturning(){
        System.out.println("后置通知");
    }
    public void afterThrowing(){
        System.out.println("异常通知");
    }
}
