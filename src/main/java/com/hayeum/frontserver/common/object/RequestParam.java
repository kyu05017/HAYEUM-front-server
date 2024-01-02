package com.hayeum.frontserver.common.object;


import com.hayeum.frontserver.common.constant.ServicePort;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestParam {

	private String requestName;
	private SendMap<String,Object> sendMap;
	private String serviceID;
	private ServicePort target;
}
