package com.ncepu.feilong505.LabManage.service;

import org.springframework.stereotype.Service;

import com.ncepu.feilong505.LabManage.common.ResponseBody;

/**
 * TODO
 * 
 * @author xtysummer1121@foxmail.com
 * @date 2019年4月30日
 */
@Service
public interface AppConfigService {
    /**
     * 
     * TODO 获取配置信息
     * 
     * @author xtysummer1121@foxmail.com
     * @date 2019年4月30日
     * @return
     */
    ResponseBody getAppConfig();

    /**
     * 
     * TODO 获取小程序用的凭证
     * 
     * @author xtysummer1121@foxmail.com
     * @date 2019年4月30日
     * @return
     */
    ResponseBody getCertificate(String jsCode,String appId) throws Exception;
}