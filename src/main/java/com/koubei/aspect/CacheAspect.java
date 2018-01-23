package com.koubei.aspect;

import com.koubei.annotation.QueryCache;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

@Aspect
@Component
public class CacheAspect {
    //controller包的子包里面任何方法
    @Pointcut("execution(public * com.koubei.controller.*.*(..))")
    public void cacheData() {
    }

    @Before("cacheData()")
    public void beforeCacheData(JoinPoint joinPoint) {

        System.out.println("调用方法之前。。。。");
        //判断是否需要缓存
        Object[] args = joinPoint.getArgs();
        Class type = joinPoint.getSignature().getDeclaringType();
        for (int i = 0; i < args.length; i++) {
            if (args[i] != null) {
                System.out.println(args[i]);
            }
        }
        //判断缓存时间
        Method[] methods=type.getDeclaredMethods();
        for (Method method:methods){
            if (method.isAnnotationPresent(QueryCache.class)) {
                QueryCache queryCache = method.getAnnotation(QueryCache.class);
                String key = queryCache.value();
                int time = queryCache.time();
                System.out.println(key);
            }
        }
        System.out.println("11111111111");
    }

    @AfterReturning(value = "cacheData()", returning = "result")
    public void afterCacheData(JoinPoint joinPoint, Object result) {
        System.out.println("调用方法结束之后。。。。");
    }

    //抛出异常时才调用
    @AfterThrowing(value = "cacheData()", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Exception ex) {
        System.out.println("校验token出现异常了......");
    }

    /**
     * 环绕通知(需要携带类型为ProceedingJoinPoint类型的参数)
     * 环绕通知包含前置、后置、返回、异常通知；ProceedingJoinPoin 类型的参数可以决定是否执行目标方法
     * 且环绕通知必须有返回值，返回值即目标方法的返回值
     *
     * @param point
     */
    @Around(value = "cacheData()")
    public Object aroundMethod(ProceedingJoinPoint point) {

        Object result = null;
        String methodName = point.getSignature().getName();
        try {
            //前置通知
            System.out.println("The method " + methodName + " start. param<" + Arrays.asList(point.getArgs()) + ">");
            //执行目标方法
            result = point.proceed();
            //返回通知
            System.out.println("The method " + methodName + " end. result<" + result + ">");
        } catch (Throwable e) {
            //异常通知
            System.out.println("this method " + methodName + " end.ex message<" + e + ">");
            throw new RuntimeException(e);
        }
        //后置通知
        System.out.println("The method " + methodName + " end.");
        return result;
    }
}
