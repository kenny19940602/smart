package com.kenny.smart.component;

import com.kenny.smart.common.api.CommonResult;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

/**
 * ClassName: BindingResultAspect
 * Function:  HibernateValidator错误结果处理切面
 * Date:      2019/9/26 15:06
 * @author Kenny
 * version    V1.0
 */
@Aspect
@Component
@Order(2)
public class BindingResultAspect {

    @Pointcut("execution(public * com.kenny.smart.controller.*.*(..))")
    public void BindingResult(){
    }

    @Around("BindingResult()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof BindingResult) {
                BindingResult result = (BindingResult) arg;
                if (result.hasErrors()) {
                    FieldError fieldError = result.getFieldError();
                    if (fieldError != null) {
                        return CommonResult.validateFailed(fieldError.getDefaultMessage());
                    }else {
                        return CommonResult.validateFailed();
                    }
                }
            }
        }
        return joinPoint.proceed();
    }

}
