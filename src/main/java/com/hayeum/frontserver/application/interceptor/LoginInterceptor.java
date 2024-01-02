package com.hayeum.frontserver.application.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hayeum.frontserver.common.constant.ServicePort;
import com.hayeum.frontserver.common.object.SendMap;
import com.hayeum.frontserver.common.object.UserInfoVo;
import com.hayeum.frontserver.common.util.HttpSend;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.HashMap;

@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    private final static ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        SendMap<String,Object> sendMap = new SendMap(request);
        //임시값 1111로 토큰 유효 확인
        String token = (String)request.getSession(false).getAttribute("token");
        /* 임시 */
        if(token == null)
        {
            request.getSession().setAttribute("token","1111");
        }

        SendMap<String, Object> formData = new SendMap<>(request);
        formData.getBodyIn().setValue("test" ,"test");

        HashMap<String, Object> result = HttpSend.callServer(formData,"/account-controller/login" , ServicePort.DATABASE);
        System.out.println(result);
        return true;
        //return result.get("result").equals("true");

        /*
            if(!token.equals("1111"))
        {
            log.info("토큰 값 에러 -> {}",token);
        }else{
            log.info("세션에 회원 정보 등록");
            임시 회원정보 생성 및 저장 수정 후 db조회 예정
        UserInfoVo _tmp = new UserInfoVo("test","emailtest",5);

        try{
            request.getSession().setAttribute(token,objectMapper.writeValueAsString(_tmp));
        }catch(Exception e) {
            log.info("json 변환 에러 -> {}",e);
        }

        try{
            if(request.getSession().getAttribute(token) == null)
            {
                log.info("세션 생성 에러");
            }else{
                log.info("세션 생성 성공 -> {}",request.getSession().getAttribute(token));
                request.getSession().setAttribute(token,);
                return new ObjectMapper().convertValue(_tmp, HashMap.class);
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
        return null;
        System.out.println("세션 pre 인터셉터");
        String token = "1111";
        HttpSession session = request.getSession(false);

        if(session != null) {
            Object jsonData = request.getSession(false).getAttribute(token);
            System.out.println(request.getSession(false).getId() + " 세션 아이디");
            if(jsonData == null || jsonData.toString().isEmpty())
            {
                log.info("로그인 안된 사용자 토큰 -> {}",token);
                return false;
            }
            UserInfoVo userInfo = new ObjectMapper().readValue(jsonData.toString(),UserInfoVo.class);

            log.info("로그인 사용자 세션 값 -> {}",userInfo.toString());
            return true;
        }else {
            log.info("세션 없음");
            return  false;
        }*/
    }
}
