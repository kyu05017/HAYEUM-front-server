package com.hayeum.frontserver.application.te.controller;

import com.hayeum.frontserver.common.constant.TargetUrl;
import com.hayeum.frontserver.common.object.SendMap;
import com.hayeum.frontserver.common.util.HttpSend;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;

import java.util.HashMap;

@Controller
public class TestController {

	public SendMap<String,Object> testMethod(HttpServletRequest request) throws Exception {

		/**
		 *TODO : 모든 request 와 response는 SendMAP으로 처리
		 */

		HashMap<String, Object> formData = new HashMap<>();
		formData.put("name", "j4j");
		formData.put("age", 123);

		HashMap<String, Object> response = HttpSend.callBackServer(formData, "/TestController/test" , TargetUrl.US);

		return new SendMap<String,Object>();
	}
}
