package com.dmjd.security;

import sun.misc.BASE64Encoder;

public class Base64 {
	public static String jdkBase64(String str){
		try {
			BASE64Encoder encoder =new BASE64Encoder();
			str = encoder.encode(str.getBytes());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return str;
	}
}
