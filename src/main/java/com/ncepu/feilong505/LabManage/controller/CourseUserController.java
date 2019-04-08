package com.ncepu.feilong505.LabManage.controller;

import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.ncepu.feilong505.LabManage.mapper.CourseUserMapper;
import com.ncepu.feilong505.LabManage.pojo.CourseUser;
import com.ncepu.feilong505.LabManage.service.CourseUserService;

/**
 * TODO 用户课堂关系模块儿 控制器
 * 
 * @author xtysummer1121@foxmail.com
 * @date 2019年3月25日
 */
@RestController
@RequestMapping(value = "/courseuser", method = RequestMethod.POST)
public class CourseUserController {
	@Autowired
	CourseUserService courseUserService;

	/**
	 * 
	 * TODO 用户和课堂绑定
	 * 
	 * @author xtysummer1121@foxmail.com
	 * @date 2019年3月25日
	 * @param userId
	 * @param courseId
	 * @return
	 */
	@RequestMapping("/getin")
	public String getInCourse(@RequestBody CourseUser courseUser) {
		return JSONObject
				.toJSONString(courseUserService.addCourseUser(courseUser.getUserId(), courseUser.getCourseId()));
	}
}
