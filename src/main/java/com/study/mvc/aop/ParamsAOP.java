package com.study.mvc.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class ParamsAOP {

    @Pointcut("@annotation(com.study.mvc.aop.annotation.ParamsAspect)") //이 어노테이션이 달려있는것을 찾아 포인트컷을 적용시켜라
    private void pointCut() {}

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object[] args = proceedingJoinPoint.getArgs();
        CodeSignature codeSignature = (CodeSignature)proceedingJoinPoint.getSignature();
        String[] paramsName = codeSignature.getParameterNames();
        String className = codeSignature.getDeclaringTypeName(); // 클래스명
        String methodName = codeSignature.getName(); //메소드명

        for(int i = 0; i < args.length; i++) {
            log.info("{}: {} ({}.{})", paramsName[i], args[i], className, methodName);
        }
//        log.info("전처리");

        Object result = proceedingJoinPoint.proceed(); // 이 코드 전에 쓰이면 전 처리 후에 쓰이면 후 처리 (비즈니스 로직)

        return result;
    }
}
