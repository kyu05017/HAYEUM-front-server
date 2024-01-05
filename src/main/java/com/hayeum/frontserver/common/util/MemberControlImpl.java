package com.hayeum.frontserver.common.util;

import com.hayeum.frontserver.common.constant.service.ServiceMethod;
import com.hayeum.frontserver.common.constant.service.ServicePort;
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

        String uuidToken = useToken.createToken();

        formData.getBodyIn().setValue("token",uuidToken);

        // 통신하여 유저 정보 일치 확인
        SendMap<String, Object> resp
                = HttpSend.callServer(formData, "/login", ServicePort.DATABASE, ServiceMethod.POST);

        log.info(resp.toString());
        boolean loginResult = (Boolean) resp.getBodyOut().get("loginResult");
        // 로그인 결과 로그
        log.info(String.valueOf(loginResult));

        if( !loginResult )
            return false;

        useToken.setSession(uuidToken, request);

        useToken.setCookie(uuidToken, response);

        return true;
    }

    @Override
    public boolean doLogout(HttpServletRequest request, HttpServletResponse response) {
        return false;
    }
}
