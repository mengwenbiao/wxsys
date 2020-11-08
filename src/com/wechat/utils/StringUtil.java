package com.wechat.utils;

import java.security.MessageDigest;
import java.util.Date;

public final class StringUtil {
	
	public static String getSha1str(String str) {
		return SHA1.encode(str);
	}
	
	public static long getWxCreateTime() {
		
		return new Date().getTime()/100000;
	}
	
	
	
	//sha1 加密
	public  static class SHA1 {
	    private static final char[] HEX = {'0', '1', '2', '3', '4', '5',
	            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
	    
	    private static String getFormattedText(byte[] bytes) {
	        int len = bytes.length;
	        StringBuilder buf = new StringBuilder(len * 2);
	        // 把密文转换成十六进制的字符串形式  
	        for (int j = 0; j < len; j++) {
	            buf.append(HEX[(bytes[j] >> 4) & 0x0f]);
	            buf.append(HEX[bytes[j] & 0x0f]);
	        }
	        return buf.toString();
	    }
	    public static String encode(String str) {
	        if (str == null) {
	            return null;
	        }
	        try {
	            MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
	            messageDigest.update(str.getBytes());
	            return getFormattedText(messageDigest.digest());
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	    }
	} 

}