package com.hayeum.frontserver.common.util;

import com.hayeum.frontserver.common.constant.service.ServiceMethod;
import com.hayeum.frontserver.common.constant.service.ServicePort;
import com.hayeum.frontserver.common.constant.service.ServiceUrl;
import com.hayeum.frontserver.common.extend.TargetExtend;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TargetService extends TargetExtend {

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
		switch (url){
			case LOCAL      : returnUrl = this.LOCAL;  break;
			case DEV        : returnUrl = this.DEV; break;
			case PROD       : returnUrl = this.PROD; break;
			default         : returnUrl = this.PROD; break;
		}
		return returnUrl;
	}

	public String setPort(ServicePort port){
		return (port == ServicePort.DATABASE)?DATABASE:FILE;
	}
	public String setMethod(ServiceMethod method){
		return String.valueOf(method);
	}
}
