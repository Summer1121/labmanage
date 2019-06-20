package com.ncepu.feilong505.LabManage.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.annotation.Resource;

import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.ncepu.feilong505.LabManage.common.AppConfigConst;
import com.ncepu.feilong505.LabManage.common.ResponseBody;
import com.ncepu.feilong505.LabManage.common.SpringUtil;
import com.ncepu.feilong505.LabManage.mapper.AppConfigMapper;
import com.ncepu.feilong505.LabManage.pojo.AppConfig;
import com.ncepu.feilong505.LabManage.service.AppConfigService;

/**
 * TODO
 * 
 * @author xtysummer1121@foxmail.com
 * @date 2019年4月30日
 */
@Service
public class AppConfigServiceImpl implements AppConfigService {

    @Autowired
    AppConfigMapper appConfigMapper;

    @Resource
    RedisTemplate<String, Object> redisTemplate;

    public static final String appConfigRedisKey = "labmanage-app-config";

    /*
     * (non-Javadoc)
     * 
     * @see com.ncepu.feilong505.LabManage.service.AppConfigService#getAppConfig()
     */
    @Override
    public ResponseBody getAppConfig() {
	ResponseBody responseBody = new ResponseBody();
	responseBody.error("过时接口");
//	try {
//	    AppConfig config = ((AppConfigServiceImpl) SpringUtil.getBean(this.getClass())).getAppConfig();
//	    if (config != null) {
//		responseBody.success(config);
//	    } else {
//		responseBody.error("没有本配置");
//	    }
//	} catch (Exception e) {
//	    responseBody.error();
//	    e.printStackTrace();
//	}
	return responseBody;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ncepu.feilong505.LabManage.service.AppConfigService#getCertificate()
     */
    @Override
    public ResponseBody getCertificate(String jsCode, String appId) throws Exception {
	ResponseBody responseBody = new ResponseBody();
	AppConfig config = ((AppConfigServiceImpl) SpringUtil.getBean(this.getClass())).getAppConfig(appId);

	String httpUrl = "https://api.weixin.qq.com/sns/jscode2session?appid=" + config.getAppId() + "&secret="
		+ config.getSecret() + "&js_code=" + jsCode + "&grant_type=authorization_code";
	String result = null;
	// 链接初始化
	try {
	    URL url = new URL(httpUrl);
	    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	    connection.setDoInput(true);
	    connection.setDoOutput(true);
	    connection.setRequestMethod("GET");
	    connection.setUseCaches(false);
	    connection.setInstanceFollowRedirects(true);
	    connection.setRequestProperty("Content-Type", "application/json");
	    connection.connect();

	    try (OutputStream os = connection.getOutputStream()) {
		os.write("".getBytes("UTF-8"));
	    }

	    try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
		String lines;
		StringBuffer sbf = new StringBuffer();
		while ((lines = reader.readLine()) != null) {
		    lines = new String(lines.getBytes(), "utf-8");
		    sbf.append(lines);
		    result = sbf.toString();
		}
	    }
	    connection.disconnect();

	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    if (result != null && !"".equals(result)) {
		JSONObject json = JSONObject.parseObject(result);
		responseBody.success(json);
	    } else {
		responseBody.error();
	    }
	}
	return responseBody;
    }

//常用方法 根据appId获取当前服务器秘钥配置

    private AppConfig getAppConfig(String appId) throws Exception {
//	if (AppConfigConst.getAppConfigConst() == null) {
	if (redisTemplate.opsForHash().hasKey(appConfigRedisKey, appId) == true)
	    return (AppConfig) redisTemplate.opsForHash().get(appConfigRedisKey, appId);

	AppConfig config = appConfigMapper.selectOneByAppId(appId);
	redisTemplate.opsForHash().put(appConfigRedisKey, appId, config);
	return config;
//	    if (config != null)
//		{
//		AppConfigConst.setAppConfigConst(config);
//		return config;
//		}
//	    else {
//		throw new Exception("no such config");
//	    }
//	}
//	else return AppConfigConst.getAppConfigConst();
    }
}
