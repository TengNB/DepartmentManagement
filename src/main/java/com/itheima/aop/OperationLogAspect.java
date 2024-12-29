package com.itheima.aop;

import com.itheima.mapper.OperateLogMapper;
import com.itheima.pojo.OperateLog;
import com.itheima.utils.CurrentHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class OperationLogAspect {

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Around("@annotation(com.itheima.anno.Log)")
    public Object logOperation(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        // 执行目标方法
        Object result = joinPoint.proceed();
        // 计算耗时
        long endTime = System.currentTimeMillis();
        long costTime = endTime - startTime;

        // 构建日志实体
        OperateLog olog = new OperateLog();
        olog.setOperateEmpId(getCurrentUserId()); // 这里需要你根据实际情况获取当前用户ID
        olog.setOperateTime(LocalDateTime.now());
        olog.setClassName(joinPoint.getTarget().getClass().getName());
        olog.setMethodName(joinPoint.getSignature().getName());
        olog.setMethodParams(Arrays.toString(joinPoint.getArgs()));
        olog.setReturnValue(result != null ? result.toString() : "void");
        olog.setCostTime(costTime);
        
        // 保存日志
        log.info("记录操作日志: {}", olog);
        operateLogMapper.insert(olog);

        return result;
    }

    private Integer getCurrentUserId() {
        return CurrentHolder.getCurrentId();
    }
}