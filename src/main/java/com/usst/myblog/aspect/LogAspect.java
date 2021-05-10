package com.usst.myblog.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

//@Aspect
//@Component
public class LogAspect {

//    private final Logger logger= (Logger) LoggerFactory.getLogger(this.getClass());
    private Logger logger = LoggerFactory.getLogger("logger");
    @Pointcut("execution(* com.usst.myblog.controller.*.*(..))")
    public void log(){

    }

    @Before("log()")
    public void before(JoinPoint joinPoint){

        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        StringBuffer url = request.getRequestURL();
        String ip = request.getRemoteAddr();
//        String classMethod = request.getMethod();不对
        String classMethod=joinPoint.getSignature().getDeclaringTypeName()+'.'+joinPoint.getSignature().getName();

        logInfo requestInfo = new logInfo();
        requestInfo.setClassMethod(classMethod);
        requestInfo.setIp(ip);
        requestInfo.setUrl(url);
//        logger.info("-----------before-------------");
        logger.info("request info:"+requestInfo.toString());
    }

    @After("log()")
    public void after(){
//        logger.info("-----------after--------------");

//        logger.info();
    }

    @AfterReturning(returning = "result",pointcut = "log()")
    public void afterReturning(Object result){
//        logger.info("-----------returning after--------------");

        logger.info("result:",result.toString());//不管用
    }

    //定义一个内部类，封装url，ip,classmethod
    public class logInfo{
        public StringBuffer url;
        public String ip;
        public String classMethod;

        public StringBuffer getUrl() {
            return url;
        }

        public void setUrl(StringBuffer url) {
            this.url = url;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public String getClassMethod() {
            return classMethod;
        }

        public void setClassMethod(String classMethod) {
            this.classMethod = classMethod;
        }
    }
}

