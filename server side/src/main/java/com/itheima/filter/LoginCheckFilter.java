package com.itheima.filter;

import com.alibaba.fastjson.JSONObject;
import com.itheima.pojo.Result;
import com.itheima.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
//@WebFilter("/*")
public class LoginCheckFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        //1.获取url请求
        String url = req.getRequestURL().toString();
        log.info("获得到url:{}",url);
        //2.判断是否包含login
        if(url.contains("login")){
            log.info("直接放行");
            filterChain.doFilter(servletRequest,servletResponse);
            return;
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
            return;
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
            return;
        }

        //6.放行
        log.info("放行");
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
