package com.ncepu.feilong505.LabManage.common;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

/**
 * TODO 时间标准格式
 * 
 * @author xtysummer1121@foxmail.com
 * @date 2019年3月20日
 */
public class TimeFormat {
    final public static String TIME = "YYYY-MM-dd HH:mm:ss";
    final public static String TIMEWithoutSpace = "YYYY-MM-dd-HH-mm-ss";
    final public static String JsonTime = "yyyy-MM-dd HH:mm:ss";

    final private static SimpleDateFormat sdf = new SimpleDateFormat(TIME);
    final private static SimpleDateFormat sdfwos = new SimpleDateFormat(TIMEWithoutSpace);

    // SimpleDateFormat 本身是线程不安全的
    public static synchronized String formate(Date date) {
	return sdf.format(date);
    }

    // 生成没有空格的字符串
    public static synchronized String formateWithoutSpace(Date date) {
	return sdfwos.format(date);
    }
}
