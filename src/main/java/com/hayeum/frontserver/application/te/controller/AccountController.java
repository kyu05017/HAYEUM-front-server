package com.hayeum.frontserver.application.te.controller;

import com.hayeum.frontserver.common.constant.ServicePort;
import com.hayeum.frontserver.common.object.SendMap;
import com.hayeum.frontserver.common.util.HttpSend;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

@Slf4j
@Controller
@RequestMapping("/account")
public class AccountController {

    @GetMapping("/login")
    public SendMap<String,Object> doLogin(HttpServletRequest request) throws Exception {

        /**
         *TODO : 모든 request 와 response는 SendMAP으로 처리
         */
        SendMap<String, Object> formData = new SendMap<>(request);
        formData.getBodyIn().setValue("token" ,formData.getHeaderIn().getValue("token",""));
        HashMap<String, Object> response = HttpSend.callServer(formData,"/account-controller/login" , ServicePort.DATABASE);
        return null;
    }
    @GetMapping("/my-page")
    public SendMap<String,Object> getMyInfo(HttpServletRequest request) throws Exception {

        /**
         *TODO : 모든 request 와 response는 SendMAP으로 처리
         */
        SendMap<String, Object> formData = new SendMap<>(request);
        formData.getBodyIn().setValue("token" ,"1111");

        try{
            System.out.println(request.getSession().getAttribute("Info").toString());
        }catch(NullPointerException e) {
            System.out.println("getMyInfo" + e);
        }

        HashMap<String, Object> response = HttpSend.callServer(formData,"/account-controller/my-info" , ServicePort.DATABASE);
        return new SendMap<String,Object>();
    }
}
