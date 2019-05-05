package com.ncepu.feilong505.LabManage.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * TODO 用于获取bean、代理bean等的工具类
 * 
 * @author xtysummer1121@foxmail.com
 * @date 2019年5月3日
 */
@Component
public class SpringUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
	SpringUtil.applicationContext = applicationContext;
    }

    public static <T> T getBean(Class<T> cla) {
	return applicationContext.getBean(cla);
    }

    public static <T> T getBean(String name, Class<T> cal) {
	return applicationContext.getBean(name, cal);
    }

    public static Object getBean(String name) {
	return applicationContext.getBean(name);
    }

    public static String getProperty(String key) {
	return applicationContext.getBean(Environment.class).getProperty(key);
    }

}
