package com.hayeum.frontserver.common.object;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter @Setter @Slf4j
public class SendMap<k,v> extends BaseMap<k,v> {

	private static final long serialVersionUID = 1288455942608122525L;
	private static final String ENTER   = System.lineSeparator();
	private static final String TAB     = "    ";
	private SetMap<String,Object> headerIn;
	private SetMap<String,Object> headerOut;
	private SetMap<String,Object> bodyIn;
	private SetMap<String,Object> bodyOut;
	private String serviceId;

	public SendMap(){
		this(new SetMap<String,Object>(),new SetMap<String,Object>(),new SetMap<String,Object>(),new SetMap<String,Object>(),null);
	}

	/**
	 * description : 기본 생성자
	 * @param headerIn
	 * @param headerOut
	 * @param bodyIn
	 * @param bodyOut
	 * @param serviceId
	 */
	public SendMap(SetMap<String, Object> headerIn, SetMap<String, Object> headerOut, SetMap<String, Object> bodyIn, SetMap<String, Object> bodyOut,String serviceId) {
		this.headerIn   = headerIn;
		this.headerOut  = headerOut;
		this.bodyIn     = bodyIn;
		this.bodyOut    = bodyOut;
		this.serviceId  = serviceId;
	}
	
	/*
	* public SendMap(HttpServletRequest request){
	*   //TODO : 리퀘스트의 헤더 바디를 받아 XSS 처리 후 반환 함수 
	* }
	**/

	@Override
	public SendMap<k,v> clone(){
		return new SendMap<k,v>(this.headerIn.clone(), this.headerOut.clone(), this.bodyIn.clone(), this.bodyOut.clone(), this.serviceId);
	}

	@Override
	public String toString() {
		return new StringBuilder(ENTER).append(TAB).append("[DATA] : ").append(super.toString())
		.append(ENTER).append(TAB).append("[BODY_IN] : ").append(this.bodyIn.toLog())
		.append(ENTER).append(TAB).append("[BODY_OUT] : ").append(this.bodyOut.toLog())
		.append(ENTER).append(TAB).append("[HEADER_IN] : ").append(this.headerIn.toLog())
		.append(ENTER).append(TAB).append("[HEADER_OUT] : ").append(this.headerOut.toLog())
		.append(ENTER).append(TAB).append("[REQUEST_SERVICE_ID] :").append(this.serviceId).toString();
	}
}
