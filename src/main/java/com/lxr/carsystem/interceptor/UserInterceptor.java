package com.lxr.carsystem.interceptor;

import com.lxr.carsystem.entity.User;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserInterceptor extends HandlerInterceptorAdapter{
    /**
     * 当返回true的时候，可以正常访问controller,
     * 反之，不能正常访问controller
    * */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("拦截器执行了");
        HttpSession httpSession=request.getSession();
        //从session中获取用户
        User user=(User)httpSession.getAttribute("currentUser");
        if (user!= null) {
            return true;// 此时因为没有用户，所以一定会返回false,即会跳转至登录页
        }
        /**
         * 当返回false的时候，不能正常访问controller（不能按照controller中的映射跳转）,此时，跳转至登录页。
         * */
        response.sendRedirect("/user/login");
        return false;
    }
}
