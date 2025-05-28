package com.itheima.aop;


import com.alibaba.fastjson.JSONObject;
import com.itheima.mapper.OperateLogMapper;
import com.itheima.pojo.OperateLog;
import com.itheima.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.bytecode.stackmap.TypeData;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Component
@Aspect
public class LogAspect {

    @Autowired
    OperateLogMapper operateLogMapper;

    @Autowired
    private HttpServletRequest request;

    @Pointcut("@annotation(com.itheima.anno.Log)")
    public void logPointcut() {}

    @Around("logPointcut()")
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {
        //登录人id
        //也就是请求头中的jwt令牌进行解析后获得的用户id
        String jwt = request.getHeader("token");
        JwtUtils.parseJWT(jwt);
        Claims  claims = JwtUtils.parseJWT(jwt);
        Integer OperateUser = (Integer) claims.get("userId");
        //操作时间

        LocalDateTime operateTime = LocalDateTime.now();

        //操作类名

        String className = joinPoint.getTarget().getClass().getName();

        //操作方法名

        String methodName = joinPoint.getSignature().getName();

        //方法参数

        Object[] args = joinPoint.getArgs();
        String methodParams = Arrays.toString(args);

        Long startTime = System.currentTimeMillis();
        //放行继续执行
        Object res = joinPoint.proceed();

        Long endTime = System.currentTimeMillis();

        //操作方法返回值
        String returnValue = JSONObject.toJSONString(res);

        //操作耗时

        Long costTime = endTime - startTime;



        //记录操作日志
        OperateLog Log = new OperateLog(null, OperateUser, operateTime, className, methodName, methodParams, returnValue, costTime);



        operateLogMapper.insert(Log);

        log.info("操作日志记录成功{}", className);

        return res;
    }


}
