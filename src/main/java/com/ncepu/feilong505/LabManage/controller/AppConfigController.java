package com.ncepu.feilong505.LabManage.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.ncepu.feilong505.LabManage.mapper.AppConfigMapper;
import com.ncepu.feilong505.LabManage.service.AppConfigService;

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

    @RequestMapping("/getcert")
    public String getCertificate(@RequestBody Map<String, String> param) throws Exception {
	return JSONObject.toJSONString(appConfigService.getCertificate(param.get("jsCode")), false);
//	return JSONObject.toJSONString(object, true);
    }

}
