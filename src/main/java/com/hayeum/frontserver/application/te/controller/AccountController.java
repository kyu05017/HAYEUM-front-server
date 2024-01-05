package com.hayeum.frontserver.application.te.controller;

import com.hayeum.frontserver.application.service.SessionService;
import com.hayeum.frontserver.common.constant.service.ServiceMethod;
import com.hayeum.frontserver.common.constant.service.ServicePort;
import com.hayeum.frontserver.common.object.SendMap;
import com.hayeum.frontserver.common.util.HttpSend;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;


@Slf4j
@Controller
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final SessionService sessionService;


    @GetMapping("/login")
    public SendMap<String,Object> doLogin(HttpServletRequest request) throws Exception {

        SendMap<String, Object> formData = new SendMap<>(request);
        SendMap<String, Object> response = HttpSend.callServer(formData,"/account-controller/login" , ServicePort.DATABASE, ServiceMethod.POST);

        String result = sessionService.accountSessionSave(formData.getHeaderIn().getValue("token",""),response.getBodyOut().getValue("info",""));
        log.info("클라이언트 세션에 저장할 로그인 정보 -> {}",result);

        return response;
    }

    @GetMapping("/my-page")
    public SendMap<String,Object> getMyInfo(HttpServletRequest request) throws Exception {
        SendMap<String, Object> formData = new SendMap<>(request);



        SendMap<String, Object> response = HttpSend.callServer(formData,"/account-controller/my-info" , ServicePort.DATABASE,ServiceMethod.POST);

        return response;
    }
}
