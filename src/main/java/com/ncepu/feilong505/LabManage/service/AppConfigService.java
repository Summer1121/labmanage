package com.ncepu.feilong505.LabManage.service;

import com.ncepu.feilong505.LabManage.common.ResponseBody;
import com.ncepu.feilong505.LabManage.controller.AppConfigController.WXCodeParam;
import org.springframework.stereotype.Service;

/**
 * TODO
 *
 * @author xtysummer1121@foxmail.com
 * @date 2019年4月30日
 */
@Service
public interface AppConfigService {
	/**
	 * TODO 获取配置信息
	 *
	 * @return
	 * @author xtysummer1121@foxmail.com
	 * @date 2019年4月30日
	 */
	ResponseBody getAppConfig();

	/**
	 * TODO 获取小程序用的凭证
	 *
	 * @return
	 * @author xtysummer1121@foxmail.com
	 * @date 2019年4月30日
	 */
	ResponseBody getCertificate(String jsCode, String appId) throws Exception;

	/**
	 * TODO 通过开发者配置获取小程序的accesstoken用作后续微信调用操作
	 *
	 * @param appId
	 * @return java.lang.String
	 * @className AppConfigService
	 * @author xtysummer1121@foxmail.com
	 * @methodName getWXAccessToken
	 * @date 2019-07-30
	 */
	String getWXAccessToken(String appId);

	/**
	 * TODO 根据参数获取小程序码
	 *
	 * @param wxCodeParam
	 * @return com.ncepu.feilong505.LabManage.common.ResponseBody
	 * @className AppConfigService
	 * @author xtysummer1121@foxmail.com
	 * @methodName getUnlimited
	 * @date 2019-07-30
	 */
	ResponseBody getUnlimited(WXCodeParam wxCodeParam);
}