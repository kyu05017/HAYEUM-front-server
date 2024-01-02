package com.hayeum.frontserver.common.util;

import com.hayeum.frontserver.common.constant.ServiceMethod;
import com.hayeum.frontserver.common.constant.ServicePort;
import com.hayeum.frontserver.common.object.SendMap;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Primary
@Component
public class MemberControlImpl implements MemberControl{

    @Autowired
    private UseToken useToken;

    @Override
    public boolean doLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {

        SendMap<String, Object> formData = new SendMap<>(request);

        log.info(formData.toString());

        // 통신하여 유저 정보 일치 확인
        try {
            SendMap<String, Object> resp = HttpSend.callServer(formData, "/login", ServicePort.DATABASE, ServiceMethod.POST);
        }catch (Exception e){
            log.error(e.getMessage());
        }

        String uuidToken = useToken.createToken();

        useToken.setSession(uuidToken, request);

        useToken.setCookie(uuidToken, response);

        return true;
    }

    @Override
    public boolean doLogout(HttpServletRequest request, HttpServletResponse response) {
        return false;
    }
}
