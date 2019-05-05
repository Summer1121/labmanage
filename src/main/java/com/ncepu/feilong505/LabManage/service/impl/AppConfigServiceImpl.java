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

import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
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

    /*
     * (non-Javadoc)
     * 
     * @see com.ncepu.feilong505.LabManage.service.AppConfigService#getAppConfig()
     */
    @Override
    public ResponseBody getAppConfig() {
	ResponseBody responseBody = new ResponseBody();
	try {
	    AppConfig config = ((AppConfigServiceImpl) SpringUtil.getBean(this.getClass())).getAppConfig(1);
	    if (config != null) {
		responseBody.success(config);
	    } else {
		responseBody.error("没有本配置");
	    }
	} catch (Exception e) {
	    responseBody.error();
	    e.printStackTrace();
	}
	return responseBody;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ncepu.feilong505.LabManage.service.AppConfigService#getCertificate()
     */
    @Override
    public ResponseBody getCertificate(String jsCode) throws Exception {
	ResponseBody responseBody = new ResponseBody();
	AppConfig config = ((AppConfigServiceImpl) SpringUtil.getBean(this.getClass())).getAppConfig(1);

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

//常用方法 获取当前服务器秘钥配置
    private AppConfig getAppConfig(int i) throws Exception {
	if (AppConfigConst.appConfigConst == null) {
	    AppConfig config = appConfigMapper.selectByPrimaryKey(i);
	    if (config != null)
		{
		AppConfigConst.appConfigConst=config;
		return config;
		}
	    else {
		throw new Exception("no such config");
	    }
	}
	else return AppConfigConst.appConfigConst;
    }
}
