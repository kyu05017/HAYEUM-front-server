package com.hayeum.frontserver.common.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomFilter implements Filter {
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

		if (servletRequest.getContentType() == null) {
			filterChain.doFilter(servletRequest, servletResponse);
			return;
		}
		if (servletRequest.getContentType().startsWith("multipart/form-data")) {
			filterChain.doFilter(servletRequest, servletResponse);
		} else {
			MultiReadableRequestBodyHttpServletRequest wrappedRequest = new MultiReadableRequestBodyHttpServletRequest((HttpServletRequest) servletRequest);
			filterChain.doFilter(wrappedRequest, servletResponse);
		}
	}
}