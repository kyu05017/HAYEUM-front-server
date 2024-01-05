package com.hayeum.frontserver.application.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
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
    public String accountSessionSave(String token,Object userInfoJson)
    {
        log.info("token -> {}",token);
        log.info("userInfoJson -> {}",userInfoJson == null);
        try{
            if(!token.isEmpty() && userInfoJson != null) {
                UserInfoVo userInfoVo = objectMapper.readValue(userInfoJson.toString(),UserInfoVo.class);
                session.setAttribute("1111", userInfoVo);
                session.setAttribute("token","1111");
                loginMap.put(token,session);
                log.info("로그인, 세션 저장 성공");
                return objectMapper.writeValueAsString(userInfoVo);
            }else{
                log.info("값 확인 \n토큰 : {}\njson데이터{}",token,userInfoJson == null);
            }
        }catch(JsonProcessingException e) {
            System.out.println("accountSessionSave" + e);
        }


        return "none";
    }
}
