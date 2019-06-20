package com.ncepu.feilong505.LabManage.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * TODO 静态资源登记
 * 
 * @author xtysummer1121@foxmail.com
 * @date 2019年6月10日
 */
@Configuration
public class Registry implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	// 将用户图片请求映射对应文件位置 到static下
	registry.addResourceHandler("image/user/get/**").addResourceLocations("file:./images/userImages/");
	registry.addResourceHandler("file/user/get/**").addResourceLocations("file:./files/userImages/");
//		.addResourceLocations("classpath:/static/");
	
    }
}
