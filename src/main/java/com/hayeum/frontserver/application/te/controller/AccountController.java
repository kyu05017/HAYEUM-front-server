package com.hayeum.frontserver.application.te.controller;

import com.hayeum.frontserver.application.service.SessionService;
import com.hayeum.frontserver.common.constant.service.ServiceMethod;
import com.hayeum.frontserver.common.constant.service.ServicePort;
import com.hayeum.frontserver.common.object.SendMap;
import com.hayeum.frontserver.common.util.HttpSend;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Slf4j
@Controller
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final SessionService sessionService;
    @GetMapping("/login")
    public SendMap<String,Object> doLogin(HttpServletRequest request) throws Exception {

        SendMap<String, Object> formData = new SendMap<>(request);

        //formData.setValue("token",formData.getHeaderIn().getValue("token",""));
        SendMap<String, Object> response = HttpSend.callServer(formData,"/account-controller/login" , ServicePort.DATABASE, ServiceMethod.GET);
        sessionService.accountSessionSave(formData.getHeaderIn().getValue("token",""),response.get("info"));

        return response;
    }

    @GetMapping("/my-page")
    public SendMap<String,Object> getMyInfo(HttpServletRequest request) throws Exception {
        SendMap<String, Object> formData = new SendMap<>(request);

        try{
            System.out.println(" info0 " +request.getSession().getAttribute(
                    request.getSession().getAttribute("token").toString()
            ));

        }catch(NullPointerException e) {
            request.getSession().invalidate();
        }

        SendMap<String, Object> response = HttpSend.callServer(formData,"/account-controller/my-info" , ServicePort.DATABASE,ServiceMethod.GET);

        return response;
    }
}
