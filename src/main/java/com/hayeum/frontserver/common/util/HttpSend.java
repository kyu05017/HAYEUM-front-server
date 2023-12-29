package com.hayeum.frontserver.common.util;

import java.util.HashMap;
import lombok.extern.slf4j.Slf4j;
import com.hayeum.frontserver.common.constant.TargetUrl;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
public class HttpSend {

	private static String EMPTY = "";

	private static String serverType = "";

	public static HashMap<String, Object> callBackServer(HashMap<String,Object> formData, String serviceId, String target){

		HashMap<String,Object> requestMap = (HashMap<String, Object>)formData.clone();

		WebClient webClient = WebClient
				.builder()
				.baseUrl(TargetUrl.DEV+target)
				.build();

		HashMap response = webClient
				.post()
				.uri(serviceId)
				.header("Content-Type","application/json")
				.header("test","ddd")
				.bodyValue(requestMap)
				.retrieve()
				.bodyToMono(HashMap.class)
				.block();

		if(log.isDebugEnabled()) {
			response.forEach((e,k) -> {
				log.info("key [{}]" ,e);
				log.info("value [{}]" ,e);
			});
		}

		// 데이터 정제
		if (response != null){}

		return response;
	}
}
