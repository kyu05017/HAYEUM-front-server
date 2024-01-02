package com.hayeum.frontserver.config;

import com.hayeum.frontserver.application.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SessionMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // 세션 관련 인터셉터
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/account/login");


    }
}