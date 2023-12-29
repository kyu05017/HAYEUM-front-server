package com.hayeum.frontserver.common.object;

import io.netty.util.internal.StringUtil;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;

@AllArgsConstructor
public class BaseMap<k,v> extends HashMap<k,v> {

	private static final long serialVersionUID = 1L;
	private static final String EMPTY = "";

	public String getValue(String key) throws RuntimeException {
		return getValue(key, EMPTY,true);
	}

	public String getValue(String key, String defaultValue) throws RuntimeException {
		return getValue(key, defaultValue, true);
	}

	private String getValue(String key,String defaultValue, boolean check) throws RuntimeException {
		String returnValue = EMPTY;
		if(check)
			returnValue = (StringUtils.equals(EMPTY, StringUtils.trimToEmpty(String.valueOf(super.get("key")))))
					? "" : String.valueOf(super.get("key"));
		return returnValue;
	}

	public void setValue(String key, Object value)  throws RuntimeException {
		String setKey   = StringUtils.equals(EMPTY,key)?EMPTY:key;
		Object setValue = (value != null)? value: "";
		super.put((k) setKey, (v) setValue);
	}
}
