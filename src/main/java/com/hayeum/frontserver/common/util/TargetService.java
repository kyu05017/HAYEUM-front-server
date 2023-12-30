package com.hayeum.frontserver.common.util;

import com.hayeum.frontserver.common.constant.ServiceMethod;
import com.hayeum.frontserver.common.constant.ServicePort;
import com.hayeum.frontserver.common.constant.ServiceUrl;
import org.apache.commons.lang3.StringUtils;

public class TargetService {

	private static TargetService instance;
	private static final String EMPTY = "";

	private TargetService(){}

	public static TargetService getInstance() {
		if(instance == null) {
			synchronized (TargetService.class){
				instance = new TargetService();
			}
		}
		return instance;
	}

	public String setUrl(ServiceUrl url){
		String returnUrl = EMPTY;
		switch (StringUtils.trimToEmpty(String.valueOf(url))){
			case "LOCAL"    : returnUrl = "http://localhost:";  break;
			case "DEV"      : returnUrl = "http://개발서버URL:"; break;
			case "STG"      : returnUrl = "http://STG서버URL:";  break;
			case "PROD"     : returnUrl = "http://운영서버URL:"; break;
			default         : returnUrl = "http://운영서버URL:"; break;
		}
		return returnUrl;
	}

	public String setPort(ServicePort port){
		String returnPort = EMPTY;
		switch (StringUtils.trimToEmpty(String.valueOf(port))){
			case "DATABASE"    : returnPort = "8081";  break;
			case "FILE"        : returnPort = "8082"; break;
		}
		return returnPort;
	}
	public String setMethod(ServiceMethod method){
		return String.valueOf(method);
	}
}
