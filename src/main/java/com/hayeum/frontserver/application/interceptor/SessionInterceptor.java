package com.hayeum.frontserver.application.interceptor;

import com.hayeum.frontserver.application.service.SessionService;
import com.hayeum.frontserver.common.object.SendMap;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Optional;

@Slf4j
public class SessionInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if(!HandlerInterceptor.super.preHandle(request, response, handler))
        {
            log.info("Session Interceptor super class error");
            return false;
        }

        try{
            //Object _obj = request.getSession(false).getAttribute(request.getSession().getAttribute("token").toString());
            SendMap<String,Object> sendMap = new SendMap<>(request);
            Object _obj = request.getSession(false).getAttribute("token");
            if(_obj != null) {
                System.out.println(" info = " +request.getSession().getAttribute(_obj.toString()));
                log.info("로그인 확인");
                return true;
            }
        }catch(NullPointerException e) {
            request.getSession().invalidate();
        }
        log.info("로그인 정보 찾을 수 없음");
        return false;
    }
}
