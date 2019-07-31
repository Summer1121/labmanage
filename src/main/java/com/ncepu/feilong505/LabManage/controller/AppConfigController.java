package com.ncepu.feilong505.LabManage.controller;

import com.alibaba.fastjson.JSONObject;
import com.ncepu.feilong505.LabManage.service.AppConfigService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * TODO
 *
 * @author xtysummer1121@foxmail.com
 * @date 2019年4月30日
 */
@RestController
@RequestMapping(method = RequestMethod.POST)
public class AppConfigController {

	@Autowired
	AppConfigService appConfigService;

	/**
	 * 根据jscode和appId 获取小程序凭证
	 */
	@RequestMapping("/getcert")
	public String getCertificate(@RequestBody Map<String, String> param) throws Exception {
		return JSONObject.toJSONString(appConfigService.getCertificate(param.get("jsCode"), param.get("appId")));
//	return JSONObject.toJSONString(object, true);
	}

	/**
	 * TODO 根据传入的参数获取小程序二维码的字节码
	 *
	 * @param wxCodeParam
	 * @return java.lang.String
	 * @className AppConfigController
	 * @author xtysummer1121@foxmail.com
	 * @methodName getCodeBuffer
	 * @date 2019-07-30
	 */
	@PostMapping("/getWXCodeBuffer")
	public String getCodeBuffer(@RequestBody WXCodeParam wxCodeParam) {
		return JSONObject.toJSONString(appConfigService.getUnlimited(wxCodeParam));
	}


	/**
	 * 微信二维码参数
	 */
	@Data
	@Accessors(chain = true)
	@AllArgsConstructor
	@NoArgsConstructor
	public static class WXCodeParam {
		/**
		 * 开发者appId
		 */
		String appId;

		/**
		 * 参数
		 */
		Map<String, Object> scene;
		/**
		 * 页面地址 默认主页
		 */
		String page;
		/**
		 * 二维码宽度 默认430
		 */
		Integer width;
		/**
		 * 是否自动颜色 默认是
		 */
		Boolean auto_color;
		/**
		 * 非自动颜色时使用rgb设置颜色 默认r:0,g:0,b:0
		 */
		Map<String, Integer> line_color;
		/**
		 * 设置是否透明背景 默认否
		 */
		Boolean is_hyaline;
	}

}
