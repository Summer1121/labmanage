package com.ncepu.feilong505.LabManage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.ncepu.feilong505.LabManage.pojo.CourseNotice;
import com.ncepu.feilong505.LabManage.service.CourseNoticeService;

/**
 * TODO
 * 
 * @author xtysummer1121@foxmail.com
 * @date 2019年5月4日
 */
@RestController
@RequestMapping(path = "/notice", method = RequestMethod.POST)
public class CourseNoticeController {
    @Autowired
    CourseNoticeService courseNoticeService;

    /**
     * 
     * TODO 添加公告
     * 
     * @author xtysummer1121@foxmail.com
     * @date 2019年5月4日
     * @param courseNotice
     * @return
     */
    @RequestMapping("/add")
    public String addNotice(@RequestBody CourseNotice courseNotice) {
	return JSONObject.toJSONString(courseNoticeService.addNotice(courseNotice.getUserId(),
		courseNotice.getCourseId(), courseNotice.getCourseNoticeContent()));
    }

    /**
     * 
     * TODO 删除公告
     * 
     * @author xtysummer1121@foxmail.com
     * @date 2019年5月4日
     * @param courseNotice
     * @return
     */
    @RequestMapping("/remove")
    public String removeNotice(@RequestBody CourseNotice courseNotice) {
	return JSONObject.toJSONString(courseNoticeService.removeNotice(courseNotice.getUserId(),
		courseNotice.getCourseId(), courseNotice.getId()));
    }

    /**
     * 
     * TODO 修改公告
     * 
     * @author xtysummer1121@foxmail.com
     * @date 2019年5月4日
     * @param courseNotice
     * @return
     */
    @RequestMapping("/update")
    public String updateNotice(@RequestBody CourseNotice courseNotice) {
	return JSONObject.toJSONString(courseNoticeService.updateNotice(courseNotice.getUserId(),
		courseNotice.getCourseId(), courseNotice.getId(), courseNotice.getCourseNoticeContent()));
    }

    /**z
     * 
     * TODO 获取公告
     * 
     * @author xtysummer1121@foxmail.com
     * @date 2019年5月4日
     * @param courseNotice
     * @return
     */
    @RequestMapping("/get")
    public String getNotice(@RequestBody CourseNotice courseNotice) {
	return JSONObject.toJSONString(courseNoticeService.getNotice(courseNotice.getId()));
    }

    /**
     * 
     * TODO 获取公告列表
     * 
     * @author xtysummer1121@foxmail.com
     * @date 2019年5月4日
     * @param courseNotice
     * @return
     */
    @RequestMapping("/getlist")
    public String getNoticeList(@RequestBody CourseNotice courseNotice) {
	return JSONObject.toJSONString(courseNoticeService.getNoticeList(courseNotice.getCourseId()));
    }
}
