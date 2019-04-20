package com.ncepu.feilong505.LabManage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.ncepu.feilong505.LabManage.pojo.Course;
import com.ncepu.feilong505.LabManage.service.CourseService;

/**
 * TODO 课堂信息前端控制器
 * 
 * @author xtysummer1121@foxmail.com
 * @date 2019年4月6日
 */
@RestController
@RequestMapping(path = "/course", method = RequestMethod.POST)
public class CourseController {
    @Autowired
    CourseService courseService;

    /**
     * 
     * TODO 添加课堂
     * 
     * @author xtysummer1121@foxmail.com
     * @date 2019年4月12日
     * @param course
     * @return
     */
    @RequestMapping("/add")
    public String addCourse(@RequestBody Course course) {
	return JSONObject.toJSONString(courseService.addCourse(course));
    }

    /**
     * 
     * TODO 修改课堂信息
     * 
     * @author xtysummer1121@foxmail.com
     * @date 2019年4月12日
     * @param course
     * @return
     */
    @RequestMapping("update")
    public String updateCourse(@RequestBody Course course) {
	return JSONObject.toJSONString(courseService.editCourse(course));
    }

    /**
     * 
     * TODO 查找一个课堂
     * 
     * @author xtysummer1121@foxmail.com
     * @date 2019年4月12日
     * @param course
     * @return
     */
    @RequestMapping("/find")
    public String findOneCourse(@RequestBody Course course) {
	return JSONObject.toJSONString(courseService.findOneCourse(course.getId()));
    }

    /**
     * 
     * TODO 根据条件查找课堂
     * 
     * @author xtysummer1121@foxmail.com
     * @date 2019年4月12日
     * @param course
     * @return
     */
    @RequestMapping("findlist")
    public String findCourseList(@RequestBody Course course) {
	return JSONObject.toJSONString(courseService.findCourseList(course));
    }
    
    
}
