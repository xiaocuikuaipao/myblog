package com.usst.myblog.Interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class MyInterceptor implements HandlerInterceptor {
    //重写prehandler

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //在此设置拦截进入管理页的请求
        Object user = request.getSession().getAttribute("user");
        if (user!=null){
            return true;
        }else {
//            request.getSession().setAttribute("msg","请先登录！");
            request.getRequestDispatcher("/admin/").forward(request,response);
            return false;
        }

    }
}
