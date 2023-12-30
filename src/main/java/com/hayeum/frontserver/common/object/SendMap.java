package com.hayeum.frontserver.common.object;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import jakarta.servlet.http.HttpServletRequest;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Map;


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
	@SuppressWarnings("unchecked")
	public SendMap(HttpServletRequest request) throws IOException {
		this(new SetMap<String, Object>(), new SetMap<String, Object>(), new SetMap<String, Object>(), new SetMap<String, Object>(), null);

		log.info(" ** header Parameters **");
		Enumeration<?> header = request.getHeaderNames();
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

		StringBuilder sb = new StringBuilder();
		BufferedReader br = null;
		String line = "";

		try {
			InputStream inputStream = request.getInputStream();
			if (inputStream != null) {
				br = new BufferedReader(new InputStreamReader(inputStream));
				while ((line = br.readLine()) != null) {
					sb.append(line);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = null;
		Map<String, Object> bodyMap = null;
		try {
			jsonObject = (JSONObject) jsonParser.parse(sb.toString());
			if(jsonObject != null) {
				bodyMap = new ObjectMapper().readValue(jsonObject.toString(), Map.class);
			}
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		if(bodyMap != null) {
			bodyMap.forEach((k,v) ->{
				log.info("key : [{}] | value : [{}]", k, v);
				this.bodyIn.setValue(k,v);
			});
		}

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
