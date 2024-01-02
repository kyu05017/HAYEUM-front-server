package com.hayeum.frontserver.common.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface UseToken {
    // 토큰 생성
    public String createToken();
    // 세션 저장
    public void setSession(String token, HttpServletRequest request);
    // 쿠키 설정
    public void setCookie(String token, HttpServletResponse response);

}
