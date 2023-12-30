package com.hayeum.frontserver.common.util;

import io.netty.channel.ChannelOption;
import org.json.simple.JSONObject;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.netty.http.client.HttpClient;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import com.hayeum.frontserver.common.constant.ServiceMethod;
import com.hayeum.frontserver.common.constant.ServicePort;
import com.hayeum.frontserver.common.constant.ServiceUrl;
import lombok.extern.slf4j.Slf4j;
import com.hayeum.frontserver.common.object.SetMap;
import com.hayeum.frontserver.common.object.SendMap;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClientRequestException;

@Slf4j
public class HttpSend {

	private static final String EMPTY = "";
	private static final String RESULT = "result";
	private static final String successResultCode = "00000";
	private static final String ENTER   = System.lineSeparator();
	private static final String TAB     = "    ";

	/**
	 * @deprecated : 서버 통신 유틸
	 * @param formData
	 * @param serviceId
	 * @param target
	 * @return
	 * @throws WebClientRequestException
	 */
	@SuppressWarnings("unchecked")
	public static HashMap<String, Object> callServer(
			SendMap<String,Object> formData,
			String serviceId,
			ServicePort target
	) throws WebClientRequestException {
		WebClient webClient = setBaseUrl(target);
		SendMap<String,Object> getResponse = postRequest(webClient, formData , serviceId);
		return new HashMap<>();
	}
	@Deprecated
	@SuppressWarnings("unchecked")
	private static HashMap<?,?> getRequest(
			WebClient webClient,
			SendMap<String,Object> formData,
			String serviceId
	) throws WebClientRequestException {
		HashMap resultMap = new HashMap<>();
		Map<String,Object> setHeaders = setHeaders(formData.getHeaderIn());
		Map<String,Object> setBodyPrams= formData.getBodyIn().clone();
		log.info("*************** GET Http Send *****************");
		try{
			JSONObject jsonObject = new JSONObject(setBodyPrams);
			resultMap = webClient
					.get()
					.uri(serviceId)
					.acceptCharset(StandardCharsets.UTF_8)
					.headers(head ->{
						setHeaders.forEach((k,v) -> {head.add(k,(String)v);});
					})
					.header("requestParams",jsonObject.toJSONString())
					.retrieve()
					.bodyToMono(HashMap.class)
					.block();
			resultMap.put(RESULT,successResultCode);
		}catch (WebClientRequestException e){
			resultMap.put("error",e.getMessage());
			resultMap.put(RESULT,"11111");
		}
		log.info("***********************************************");
		return resultMap;
	}

	@SuppressWarnings("unchecked")
	private static SendMap<String,Object> postRequest(
			WebClient webClient,
			SendMap<String,Object> formData,
			String serviceId
	)throws WebClientRequestException{
		SendMap<String,Object> resultMap = new SendMap<>();
		SetMap<String,Object> headerIn = setHeaders(formData.getHeaderIn());
		SetMap<String,Object> headerOut = new SetMap<>();
		SetMap<String,Object> bodyIn = formData.getBodyIn();
		SetMap<String,Object> bodyOut = new SetMap<>();
		resultMap.setServiceId(serviceId);
		log.info("*************** POST Http Send *****************");
		log.info(requestLog(formData));
		try{
			bodyOut = webClient
				.post()
				.uri(serviceId)
				.acceptCharset(StandardCharsets.UTF_8)
				.headers(head ->{
					headerIn.forEach((k,v) -> {head.add(k,(String)v);});
				})
				.bodyValue(bodyIn)
				.retrieve()
				.bodyToMono(SetMap.class)
				.block();
			headerOut.setValue(RESULT,successResultCode);
		}catch (WebClientRequestException e){
			headerOut.put("error",e.getMessage());
			headerOut.put(RESULT,"11111");
		}
		resultMap.getHeaderIn() .putAll(headerIn);
		resultMap.getBodyIn()   .putAll(bodyIn);
		resultMap.getHeaderOut().putAll(headerOut);
		resultMap.getBodyOut().  putAll(bodyOut);
		log.info(responseLog(formData));
		log.info("************************************************");
		return resultMap;
	}
	private static WebClient setBaseUrl (ServicePort target) {
		//  URL 세팅
		StringBuilder sb = new StringBuilder();
		sb.append(TargetService.getInstance().setUrl(ServiceUrl.LOCAL));
		sb.append(TargetService.getInstance().setPort(target));

		// TIMEOUT
		HttpClient httpClient = HttpClient.create()
				.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
				.responseTimeout(Duration.ofMillis(5000))
				.doOnConnected(conn -> conn
					.addHandlerLast(new ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS))
					.addHandlerLast(new WriteTimeoutHandler(5000, TimeUnit.MILLISECONDS))
				);

		return WebClient.builder()
		.baseUrl(sb.toString())
		.clientConnector(new ReactorClientHttpConnector(httpClient))
		.build();
	}
	private static SetMap<String,Object> setHeaders(SetMap<String,Object> paramMap) {
		SetMap<String, Object> returnMap = paramMap.clone();
		returnMap.setValue(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
		return returnMap;
	}

	private static String requestLog(SendMap sendMap){
		return new StringBuilder(ENTER).append(TAB).append("[REQUEST PARAMETER] : ").append(sendMap.toString())
				.append(ENTER).append(TAB).append("[BODY_IN] : ").append(sendMap.getBodyIn().toLog())
				.append(ENTER).append(TAB).append("[HEADER_IN] : ").append(sendMap.getHeaderIn().toLog())
				.append(ENTER).append(TAB).append("[REQUEST_SERVICE_ID] :").append(sendMap.getServiceId()).toString();
	}

	private static String responseLog(SendMap sendMap){
		return new StringBuilder(ENTER).append(TAB).append("[RESPONSE PARAMETER] : ").append(sendMap.toString())
				.append(ENTER).append(TAB).append("[BODY_OUT] : ").append(sendMap.getBodyOut().toLog())
				.append(ENTER).append(TAB).append("[HEADER_OUT] : ").append(sendMap.getHeaderOut().toLog())
				.append(ENTER).append(TAB).append("[REQUEST_SERVICE_ID] :").append(sendMap.getServiceId()).toString();
	}
}