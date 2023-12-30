package com.hayeum.frontserver.common.object;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Enumeration;


@Getter @Setter @Slf4j
public class SendMap<k,v> extends BaseMap<k,v> {

	private static final long serialVersionUID = 1288455942608122525L;
	private static final String ENTER   = System.lineSeparator();
	private static final String TAB     = "    ";
	private static final String EMPTY   = "";

	private SetMap<String,Object> headerIn;
	private SetMap<String,Object> headerOut;
	private SetMap<String,Object> bodyIn;
	private SetMap<String,Object> bodyOut;
	private String serviceId;

	/**
	 * Construct
	 */
	public SendMap(){
		this(
			new SetMap<String,Object>(),
			new SetMap<String,Object>(),
			new SetMap<String,Object>(),
			new SetMap<String,Object>(),
			EMPTY
		);
	}

	/**
	 * Construct
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

	/**
	 * description : 컨트롤러 요청시 생성 가능
	 * @param request
	 */
	public SendMap(HttpServletRequest request){
		this(new SetMap<String, Object>(), new SetMap<String, Object>(), new SetMap<String, Object>(), new SetMap<String, Object>(), null);

		Enumeration<?> header = request.getHeaderNames();

		log.info(" ** header Parameters **");
		String headerName = EMPTY;
		String headerValue = EMPTY;

		while(header.hasMoreElements()) {
			headerName = (String) header.nextElement();
			headerValue = request.getHeader(headerName);
			log.info("key : [{}] | value : [{}]", headerName, headerValue);
			this.headerIn.setValue(headerName,headerValue);
		}
		log.info(" ** header Parameters **");

		log.info(" ** body Parameters **");
		Enumeration<?> body = request.getParameterNames();

		String bodyName = EMPTY;
		String bodyValue = EMPTY;

		while(body.hasMoreElements()) {
			bodyName = (String) body.nextElement();
			bodyValue = request.getParameter(bodyName);
			log.info("key : [{}] | value : [{}]", bodyName, bodyValue);
			this.bodyIn.setValue(bodyName,bodyValue);
		}
		log.info(" ** body Parameters **");
	}

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
