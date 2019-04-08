package com.ncepu.feilong505.LabManage.common;

import org.apache.ibatis.javassist.expr.NewArray;

import lombok.Data;

/**
 * TODO 将16进制字符串转化为十进制Long
 * 
 * @author xtysummer1121@foxmail.com
 * @date 2019年4月7日
 */
@Data
public class X0String2Long {
	String str;
	Long val;

	void getVal(String str) {
		val = Long.valueOf(str, 16);
	}

	void getStr(Long val) {
		str = Long.toHexString(val);
	}

	public X0String2Long(String str) {
		this.str = str;
		getVal(str);
	}

	public X0String2Long(Long val) {
		this.val = val;
		getStr(val);
	}

}
