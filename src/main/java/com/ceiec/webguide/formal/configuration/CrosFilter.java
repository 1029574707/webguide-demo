package com.ceiec.webguide.formal.configuration;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * CreateDate：2017/11/1
 * Author：NieLixiang
 * Description: 该类主要用于服务端跨域
 **/

@Component
public class CrosFilter implements Filter {

    /**
     * 跨域配置
     *
     * @param req http请求
     * @param res http响应
     * @param chain 责任链
     * @throws IOException IO异常
     * @throws ServletException Servlet异常
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, token, language");
        chain.doFilter(req, res);
    }

    /**
     * 初始化配置
     *
     * @param filterConfig 初始化配置参数
     */
    @Override
    public void init(FilterConfig filterConfig) {
    }

    /**
     * 配置销毁
     */
    @Override
    public void destroy() {
    }
}