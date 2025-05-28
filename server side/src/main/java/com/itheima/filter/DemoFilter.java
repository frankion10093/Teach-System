package com.itheima.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

//@WebFilter("/*")
public class DemoFilter implements Filter {

    @Override //初始化方法只调用一次
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        System.out.println("Demo init方法执行了");
    }

    @Override // 拦截到请求之后调用，调用多次
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Demo 放行前的逻辑");
        //放行
        filterChain.doFilter(servletRequest,servletResponse);

        System.out.println("Demo  拦截到了请求,放行后的逻辑");
    }

    @Override //销毁的时候调用
    public void destroy() {
        Filter.super.destroy();
        System.out.println("Demo  destory方法执行了");
    }
}
