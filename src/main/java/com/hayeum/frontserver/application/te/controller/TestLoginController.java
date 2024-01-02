package com.hayeum.frontserver.application.te.controller;

import com.hayeum.frontserver.common.object.SendMap;
import com.hayeum.frontserver.common.util.MemberControl;
import com.hayeum.frontserver.common.util.MemberControlImpl;
import com.hayeum.frontserver.common.util.UseToken;
import com.hayeum.frontserver.common.util.UseUuid;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/logintest")
public class TestLoginController {

    @Autowired
    MemberControl memberControl;

    @PostMapping("login")
    public SendMap<String,Object> testMethod(HttpServletRequest request , HttpServletResponse response) throws Exception {

        boolean isLogin = memberControl.doLogin(request, response);

        return new SendMap<String,Object>();
    }
}
