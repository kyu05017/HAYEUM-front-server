package com.hayeum.frontserver.common.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface MemberControl {

    public boolean doLogin(HttpServletRequest request, HttpServletResponse response) throws IOException;

    public boolean doLogout(HttpServletRequest request, HttpServletResponse response);

}
