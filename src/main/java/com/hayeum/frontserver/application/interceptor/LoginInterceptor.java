package com.hayeum.frontserver.application.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hayeum.frontserver.common.constant.service.ServiceMethod;
import com.hayeum.frontserver.common.object.SendMap;
import com.hayeum.frontserver.common.util.HttpSend;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.hayeum.frontserver.common.constant.service.ServicePort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import java.util.HashMap;

@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }
}
