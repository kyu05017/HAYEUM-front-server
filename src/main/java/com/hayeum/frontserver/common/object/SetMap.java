package com.hayeum.frontserver.common.object;

import java.util.Iterator;

public class SetMap<k,v> extends BaseMap<k,v>{

	private static final long serialVersionUID = 1L;

	@Override
	public SetMap<k,v> clone(){
		return (SetMap<k, v>)super.clone();
	}

	public final String toLog() {
		Iterator<Entry<k,v>> i = entrySet().iterator();
		if(i.hasNext() == false){
			return "{}";
		}

		StringBuilder sb = new StringBuilder();
		sb.append("{");
		for(;;){
			Entry<k,v> e = i.next();
			k key   = e.getKey();
			v value = e.getValue();
			sb.append((key == null)?"This Map" : key);
			sb.append("=");
			sb.append(value);

			if(i.hasNext() == false){
				return sb.append("}").toString();
			}
			sb.append(",").append(".");
		}
	}
}
