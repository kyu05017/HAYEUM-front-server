package com.hayeum.frontserver.application.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hayeum.frontserver.common.object.UserInfoVo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service @RequiredArgsConstructor @Slf4j
public class SessionService {


    private final HttpSession session;
    private final ObjectMapper objectMapper;
    public static final HashMap<String, HttpSession> loginMap = new HashMap<>();
    public boolean accountSessionSave(String token,Object userInfoJson)
    {
        log.info("token -> {}",token);
        log.info("userInfoJson -> {}",userInfoJson == null);
        try{
            if(!token.isEmpty() && userInfoJson != null) {
                session.setAttribute("1111", objectMapper.readValue(userInfoJson.toString(), UserInfoVo.class));
                session.setAttribute("token","1111");
                loginMap.put(token,session);
                log.info("저장 성공");
            }else{
                log.info("값 확인 \n토큰 : {}\njson데이터{}",token,userInfoJson == null);
            }

        }catch(JsonProcessingException e) {
            System.out.println("token = " + token);
            System.out.println("userInfoJson = " + userInfoJson);
            System.out.println("accountSessionSave" + e);
        }
        return false;
    }
}
