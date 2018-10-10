package com.kanper.config;

import com.kanper.annotation.Authorization;
import com.kanper.bean.UserBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Component
@Slf4j
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        Class c = ((HandlerMethod) handler).getBeanType();
        if ((c.getPackage().getName().equals("com.kanper.controller") && c.getAnnotation(Authorization.class) != null)) {
            return true;
        }
        UserBean userBean = (UserBean) request.getSession().getAttribute("user");


        if (c.getAnnotation(Authorization.class) == null && method.getAnnotation(Authorization.class) == null) {
            if (userBean == null) {
                response.sendRedirect("/user/login");
                return false;
            }
        } else {
            if (!userBean.isAdmin()) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("你必须有管理员权限才能操作");
                return false;
            }
        }


        return super.preHandle(request, response, handler);
    }
}
