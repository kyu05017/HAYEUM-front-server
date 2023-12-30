package com.hayeum.frontserver.application.te.controller;


import java.util.Enumeration;
import java.util.HashMap;

import com.hayeum.frontserver.common.constant.ServiceMethod;
import com.hayeum.frontserver.common.constant.ServicePort;
import com.hayeum.frontserver.common.util.HttpSend;
import lombok.extern.slf4j.Slf4j;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import com.hayeum.frontserver.common.object.SendMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/test")
public class TestController {

	@GetMapping("/test.Do")
	public SendMap<String,Object> testMethod(HttpServletRequest request) throws Exception {

		/**
		 *TODO : 모든 request 와 response는 SendMAP으로 처리
		 */
		SendMap<String, Object> formData = new SendMap<>(request);
		formData.getBodyIn().setValue("test" ,"파라미티");
		formData.getBodyIn().setValue("test2","파라미티");
		HashMap<String, Object> response = HttpSend.callServer(formData,"/TestController/test" , ServicePort.DATABASE);

		return new SendMap<String,Object>();
	}
}
