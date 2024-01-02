package com.hayeum.frontserver.common.object;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@AllArgsConstructor
public class BaseMap<k,v> extends HashMap<k,v> {

	private static final long serialVersionUID = 1L;
	private static final String EMPTY = "";

	public Object get(String key) throws RuntimeException {
		Object o = super.get(key);
		if(o instanceof List) {
			o = (List)o;
		} else if(o instanceof Map) {
			o = (Map)o;
		} else if(o instanceof Set) {
			o = (Set)o;
		}
		return o;
	}

	public String getValue(String key, String defaultValue) throws RuntimeException {
		String result = "";
		try {
			Object o = (Object) super.get(key);
			if(o != null) {
				result = String.valueOf(o.toString());
			} else {
				return defaultValue;
			}
		} catch (Exception e) {}
		return result;
	}

	public int getValue(String key,int defaultValue) throws RuntimeException {
		int result = 0;
		try {
			Object o = (Object) super.get(key);
			if(o != null) {
				result = Integer.valueOf(o.toString());
			} else {
				return defaultValue;
			}
		} catch (Exception e) {}
		return result;
	}

	public long getValue(String key,long defaultValue) throws RuntimeException {
		long result = 0;
		try {
			Object o = (Object) super.get(key);
			if(o != null) {
				result = Long.parseLong(o.toString());
			} else {
				return defaultValue;
			}
		} catch (Exception e) {}
		return result;
	}

	public boolean getValue(String key,boolean defaultValue) throws RuntimeException {
		boolean result = false;
		try {
			Object o = (Object) super.get(key);
			if(o != null) {
				result = Boolean.parseBoolean(o.toString());
			} else {
				return defaultValue;
			}
		}catch (Exception e) {}
		return result;
	}

	public float getValue(String key,float defaultValue) throws RuntimeException {
		float result = 0f;
		try {
			Object o = (Object) super.get(key);
			if(o != null) {
				result = Float.parseFloat(o.toString());
			} else {
				return defaultValue;
			}
		}catch (Exception e) {}
		return result;
	}

	public double getValue(String key,double defaultValue) throws RuntimeException {
		double result = 0f;
		try {
			Object o = (Object) super.get(key);
			if(o != null) {
				result = Double.parseDouble(o.toString());
			} else {
				return defaultValue;
			}
		}catch (Exception e) {}
		return result;
	}

	@SuppressWarnings("unchecked")
	private Object getValue(String key,Object defaultValue) throws RuntimeException {
		Object returnValue = (Object)super.get(key);
		Object result = null;
		if(returnValue != null) {
			if(returnValue instanceof String) {
				result = String.valueOf(returnValue);
			}
			if(returnValue instanceof Boolean) {
				result = Boolean.parseBoolean(returnValue.toString());
			}
			if(returnValue instanceof Integer) {
				result = Integer.parseInt(returnValue.toString());
			}
			if(returnValue instanceof Long) {
				result = Long.parseLong(returnValue.toString());
			}
			if(returnValue instanceof Float) {
				result = Float.parseFloat(returnValue.toString());
			}
			if(returnValue instanceof Double) {
				result = Double.parseDouble(returnValue.toString());
			}
			if(returnValue instanceof Map<?,?>){
				result = (Map)returnValue;
			}
			if(returnValue instanceof List<?>){
				result = (List)returnValue;
			}
			if(returnValue instanceof Set<?>){
				result = (Set)returnValue;
			}
		}

		return (result == null)? EMPTY : result;
	}

	public void setValue(String key, Object value)  throws RuntimeException {
		String setKey   = StringUtils.equals(EMPTY,key)?EMPTY:key;
		Object setValue = (value != null)? value: "";
		super.put((k) setKey, (v) setValue);
	}
}
