package com.hayeum.frontserver.application.te.controller;

import com.hayeum.frontserver.common.object.SendMap;
import com.hayeum.frontserver.common.util.MemberControl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/logintest")
public class TestLoginController {

    @Autowired
    MemberControl memberControl;

    @GetMapping ("login")
    public SendMap<String,Object> testMethod(HttpServletRequest request , HttpServletResponse response) throws Exception {

        boolean isLogin = memberControl.doLogin(request, response);

        return new SendMap<String,Object>();
    }
}
