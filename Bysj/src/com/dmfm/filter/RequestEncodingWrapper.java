package com.dmfm.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class RequestEncodingWrapper extends HttpServletRequestWrapper {

	private String encoding = "";
	
	public RequestEncodingWrapper(HttpServletRequest request) {
		//必须调用父类构造方法
		super(request);		
	}
	
	public RequestEncodingWrapper(HttpServletRequest request,String encoding){
		//必须调用父类构造方法
		super(request);
		this.encoding = encoding;
	}

	@Override
	public String getParameter(String name) {
		String value = getRequest().getParameter(name);
		try {
			//将参数进行编码转换
			if (value!=null&&!"".equals(value)) {
				value = new String(value.trim().getBytes("ISO-8859-1"), encoding);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}

	
}
