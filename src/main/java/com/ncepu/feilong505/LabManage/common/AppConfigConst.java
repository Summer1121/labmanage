package com.ncepu.feilong505.LabManage.common;

import com.ncepu.feilong505.LabManage.pojo.AppConfig;

import lombok.Data;

/**
 * TODO 用于记录小程序的开发者配置（机密） 客户端第一次请求验证时从数据库初始化本类的实例
 * 
 * @author xtysummer1121@foxmail.com
 * @date 2019年5月3日
 */
public class AppConfigConst {
    AppConfigConst(AppConfig appConfig) {
	if (this.appConfigConst == null) {
	    this.appConfigConst.setSecret(appConfig.getSecret());
	    this.appConfigConst.setAppId(appConfig.getAppId());
	}
    }

    /**
     * 变量的封装 近似于常量
     */
    private static AppConfig appConfigConst;

    /**
     * @return the appConfigConst
     */
    public static AppConfig getAppConfigConst() {
	return appConfigConst;
    }

    /**
     * @param appConfigConst the appConfigConst to set
     */
    public static void setAppConfigConst(AppConfig appConfigConst) {
	if (appConfigConst == null) {
	    AppConfigConst.appConfigConst = appConfigConst;
	}
    }

}
