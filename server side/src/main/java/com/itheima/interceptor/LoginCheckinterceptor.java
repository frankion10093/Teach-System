package com.itheima.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.itheima.pojo.Result;
import com.itheima.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class LoginCheckinterceptor implements HandlerInterceptor {
    @Override//目标资源运行前
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
        //1.获取url请求
        String url = req.getRequestURL().toString();
        log.info("获得到url:{}",url);
        //2.判断是否包含login
        if(url.contains("login")){
            log.info("直接放行");
            return true;
        }
        //3.获取请求头中的令牌

        String jwt = req.getHeader("token");

        //4.判断令牌是否存在

        if(!StringUtils.hasLength(jwt)){
            log.info("token不存在");
            Result error = Result.error("NOT_LOGIN");
            //手动转换对象 对象->Json ----->阿里巴巴的fastJson
            String noLogin = JSONObject.toJSONString(error);
            resp.getWriter().write(noLogin);
            return false;
        }

        //5.解析token，如果解析失败，返回错误结果

        try {
            JwtUtils.parseJWT(jwt);
        }
        catch (Exception e){//解析失败
            e.printStackTrace();
            log.info("解析令牌失败，返回登录错误信息");
            Result error = Result.error("NOT_LOGIN");
            //手动转换对象 对象->Json ----->阿里巴巴的fastJson
            String noLogin = JSONObject.toJSONString(error);
            resp.getWriter().write(noLogin);
            return false;
        }

        //6.放行
        log.info("放行");
        return true;
    }

    @Override//目标资源运行后，视图渲染前
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override//视图渲染后
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
