package com.hayeum.frontserver.application.interceptor;

import com.hayeum.frontserver.application.service.SessionService;
import com.hayeum.frontserver.common.object.SendMap;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if(!HandlerInterceptor.super.preHandle(request, response, handler))
        {
            log.info("Login Interceptor super class error");
            return false;
        }

        SendMap<String,Object> sendMap = new SendMap<>(request);

        return loginExceptionCheck(sendMap.getHeaderIn().getValue("token",""));
    }

    private boolean loginExceptionCheck(String token)
    {
        String error = "success";

        /* check */
        if(token.isEmpty()) {
            error = "emptyToken";
        }else if(SessionService.loginMap.containsKey(token)){
            error = "alreadyLogin";
        }

        /* result*/
        if(error.equals("success"))
        {
            log.info("로그인 성공");
            return true;
        }else{
            log.info("로그인 에러 -> {}",error);
            // 기존 기기의 로그인 해제 SessionService.loginMap.get(token).invalidate();
            // or
            // 새로운 로그인 요청 클라이언트 drop
            return false;
        }
    }
}
