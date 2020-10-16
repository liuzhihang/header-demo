package com.liuzhihang.demo.interceptor;

import com.liuzhihang.demo.annotion.CheckToken;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {


    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {
    }

    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3) throws Exception {
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 请求方法是否存在注解
        boolean assignableFrom = handler.getClass().isAssignableFrom(HandlerMethod.class);

        if (!assignableFrom) {
            return true;
        }

        CheckToken checkToken = null;
        if (handler instanceof HandlerMethod) {
            checkToken = ((HandlerMethod) handler).getMethodAnnotation(CheckToken.class);
        }

        // 没有加注解 直接放过
        if (checkToken == null) {
            return true;
        }

        if (checkToken.checkAuthorization()) {
            String authorization = request.getHeader("authorization");
            log.info("header authorization : {}", authorization);
            if (StringUtils.isBlank(authorization)) {
                log.error("从Header中获取Authorization失败");
                return false;
            }
        }

        // ... 其他逻辑省略

        return true;
    }


}