package com.ncepu.feilong505.LabManage.common;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * TODO MD5加密
 * 
 * @author xtysummer1121@foxmail.com
 * @date 2019年6月7日
 */
@Component
public class MD5Util {
    /**
     * MD5方法
     * 
     * @param text 明文
     * @param key  密钥
     * @return 密文
     * @throws Exception
     */

    private static String key;

    @Value("${md5.key}")
    public void setKey(String param) {
	key = param;
    }

    public static String md5(String text) throws Exception {
	// 加密后的字符串
	String encodeStr = DigestUtils.md5Hex(text + key);
//	System.out.println("MD5加密后的字符串为:encodeStr=" + encodeStr);
	return encodeStr;
    }

    /**
     * MD5验证方法
     * 
     * @param text 明文
     * @param md5  密文
     * @return true/false
     * @throws Exception
     */
    public static boolean verify(String text, String md5) throws Exception {
	// 根据传入的密钥进行验证
	String md5Text = md5(text);
	if (md5Text.equalsIgnoreCase(md5)) {
//	    System.out.println("MD5验证通过");
	    return true;
	}

	return false;
    }
}
