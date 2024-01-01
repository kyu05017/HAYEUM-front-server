package com.hayeum.frontserver.common.util;

import com.hayeum.frontserver.common.constant.ServiceMethod;
import com.hayeum.frontserver.common.constant.ServicePort;
import com.hayeum.frontserver.common.constant.ServiceUrl;

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
		String returnPort = (port == ServicePort.DATABASE)?this.DATABASE:this.FILE;
		return returnPort;
	}
	public String setMethod(ServiceMethod method){
		return String.valueOf(method);
	}
}
