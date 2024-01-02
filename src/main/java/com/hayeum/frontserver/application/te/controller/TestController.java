package com.hayeum.frontserver.application.te.controller;

import com.hayeum.frontserver.common.constant.service.ServiceMethod;
import com.hayeum.frontserver.common.object.RequestParam;
import lombok.extern.slf4j.Slf4j;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import com.hayeum.frontserver.common.util.HttpSend;
import com.hayeum.frontserver.common.object.SendMap;
import com.hayeum.frontserver.common.constant.service.ServicePort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

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

		List<String> tempList = new ArrayList<>();
		tempList.add("1");
		tempList.add("2");
		tempList.add("3");
		tempList.add("4");
		tempList.add("5");
		formData.getBodyIn().setValue("test3",tempList);

		SendMap<String, Object> response = HttpSend.callServer(formData,"/TestController/test" , ServicePort.DATABASE, ServiceMethod.POST);

		List<String> returnList = (ArrayList)response.getBodyIn().get("test3");

		for(String temp : returnList){
			log.info("list get : [{}]",temp);
		}

		log.info(response.toString());

		RequestParam param = RequestParam.builder()
				.requestName("")
				.sendMap(formData)
				.serviceID("/TestController/test")
				.target(ServicePort.DATABASE)
				.build();


		return new SendMap<String,Object>();
	}
}
