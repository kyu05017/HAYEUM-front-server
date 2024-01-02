package com.hayeum.frontserver.common.util;

import com.hayeum.frontserver.common.constant.ServiceMethod;
import com.hayeum.frontserver.common.constant.ServicePort;
import com.hayeum.frontserver.common.object.SendMap;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;
@Slf4j
@Component
@Primary
public class UseUuid implements UseToken{

    @Override
    public String createToken() {
        return UUID.randomUUID().toString();
    }

    @Override
    public void setSession(String token, HttpServletRequest request) {
        request.getSession().setAttribute(token, "loginVO");
    }

    @Override
    public void setCookie(String token, HttpServletResponse response) {
        Cookie clientToken = new Cookie("test", token);
        clientToken.setPath("/");
        response.addCookie(clientToken);
    }

}
