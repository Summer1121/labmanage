package com.ncepu.feilong505.LabManage.common;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * TODO  
 * @author xtysummer1121@foxmail.com
 * @date 2019年4月7日
 */
public class X0String2LongTest {

	@Test
	public void test() {
		System.out.println(new X0String2Long("2ff").getVal());
		System.out.println(new X0String2Long(767l).getStr());
	}

}
