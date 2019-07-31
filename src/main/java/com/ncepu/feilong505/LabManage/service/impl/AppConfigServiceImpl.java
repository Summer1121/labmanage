package com.ncepu.feilong505.LabManage.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ncepu.feilong505.LabManage.common.ResponseBody;
import com.ncepu.feilong505.LabManage.common.SpringUtil;
import com.ncepu.feilong505.LabManage.controller.AppConfigController;
import com.ncepu.feilong505.LabManage.mapper.AppConfigMapper;
import com.ncepu.feilong505.LabManage.pojo.AppConfig;
import com.ncepu.feilong505.LabManage.service.AppConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * TODO
 *
 * @author xtysummer1121@foxmail.com
 * @date 2019年4月30日
 */
@Service
public class AppConfigServiceImpl implements AppConfigService {

	@Value("${time.interval.WXAccessToken}")
	Integer WXAccessTokenTimeInterval;

	@Autowired
	AppConfigMapper appConfigMapper;

	@Resource
	RedisTemplate<String, Object> redisTemplate;

	@Autowired
	StringRedisTemplate stringRedisTemplate;

	public static final String appConfigRedisKey = "labmanage-app-config-";

	public static final String appAccessTokenRedisKey = "labmanage-app-access-token-";

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


	@Override
	/**获取微信的accessToken*/
	public String getWXAccessToken(String appId) {
		//获取配置信息
		AppConfig config = null;
		try {
			config = getAppConfig(appId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (config == null)
			return null;
		//查看缓存中是否存在本token
		String accessToken = stringRedisTemplate.opsForValue().get(appAccessTokenRedisKey + appId);
		if (accessToken != null) {
			return accessToken;
		}

		String httpUrl =
				"https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + config.getAppId()
						+ "&secret=" + config.getSecret();
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

				String AccessToken = (String) json.get("access_token");
				if (AccessToken != null) {
					stringRedisTemplate.opsForValue().set(appAccessTokenRedisKey + appId, AccessToken);
					stringRedisTemplate.expire(appAccessTokenRedisKey + appId, WXAccessTokenTimeInterval,
							TimeUnit.SECONDS);
				}
				return AccessToken;
			}
			return null;
		}
	}

	@Override
	public ResponseBody getUnlimited(AppConfigController.WXCodeParam wxCodeParam) {
		String accessToken = getWXAccessToken(wxCodeParam.getAppId());
		if (accessToken == null)
			return new ResponseBody().error("accessToken is null");
		else {
			String httpUrl = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=" + accessToken;
			String result = null;
			// 链接初始化
			try {
				URL url = new URL(httpUrl);
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				connection.setDoInput(true);
				connection.setDoOutput(true);
				connection.setRequestMethod("POST");
				connection.setUseCaches(false);
				connection.setInstanceFollowRedirects(true);
				connection.setRequestProperty("Content-Type", "application/json");
				connection.connect();
				DataOutputStream out = new DataOutputStream(connection.getOutputStream());
				//添加请求体参数
				StringBuilder sb = new StringBuilder();
//				sb.append(String.format("access_token=%s", accessToken));
				sb.append("{");
				//添加场景参数
				sb.append(String.format("\"scene\":\"%s\"", wxCodeParam.getScene().toString()));
				//添加其他变量
				if (wxCodeParam.getPage() != null)
					sb.append(String.format(",\"page\"：%s", wxCodeParam.getPage()));
				if (wxCodeParam.getAuto_color() != null)
					sb.append((String.format(",\"auto_color\"：%b", wxCodeParam.getAuto_color())));
				if (wxCodeParam.getWidth() != null)
					sb.append(String.format(",\"width\":%d", wxCodeParam.getWidth()));
				if (wxCodeParam.getIs_hyaline() != null)
					sb.append((String.format(",\"is_hyaline\":%b", wxCodeParam.getIs_hyaline())));
				if (wxCodeParam.getLine_color() != null)
					sb.append(String.format(",\"line_color\":%s", JSONObject.toJSONString(wxCodeParam.getLine_color())));
				sb.append("}");
				out.writeBytes(sb.toString());
				//关闭流
				out.flush();
				out.close();

				try (BufferedReader reader =
						     new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
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
					StringBuffer buffer = new StringBuffer(result);
					if (buffer != null)
						return new ResponseBody().setData(buffer);
				}
				return new ResponseBody().error();
			}
		}
	}

//常用方法 根据appId获取当前服务器秘钥配置

	private AppConfig getAppConfig(String appId) throws Exception {
//	if (AppConfigConst.getAppConfigConst() == null) {
		if (redisTemplate.opsForHash().hasKey(appConfigRedisKey, appId) == true)
			return (AppConfig) redisTemplate.opsForValue().get(appConfigRedisKey + appId);

		AppConfig config = appConfigMapper.selectOneByAppId(appId);
		redisTemplate.opsForValue().set(appConfigRedisKey + appId, config);
		redisTemplate.expire(appConfigRedisKey + appId, 60, TimeUnit.SECONDS);
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
