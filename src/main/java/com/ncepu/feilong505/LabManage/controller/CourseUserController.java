package com.ncepu.feilong505.LabManage.controller;

import java.nio.file.Path;
import java.util.Map;

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

    /**
     * 
     * TODO 获取用户的课堂列表
     * 
     * @author xtysummer1121@foxmail.com
     * @date 2019年4月12日
     * @param bean
     * @return
     */
    @RequestMapping("/getcourselist")
    public String findCourseWithUser(@RequestBody courseUserBean bean) {
	return JSONObject.toJSONString(courseUserService.findCourseWithUser(bean.userId, bean.status));
    }

    /**
     * 
     * TODO 获取一个课堂的用户列表
     * 
     * @author xtysummer1121@foxmail.com
     * @date 2019年4月18日
     * @param bean
     * @return
     */
    @RequestMapping("/getuserlist")
    public String findUserWithCourse(@RequestBody courseUserBean bean) {
	return JSONObject.toJSONString(courseUserService.findUserWithCourse(bean.courseId));
    }

    /**
     * 
     * TODO 某用户通过输入字符串进入小组（类似于面对面建群）
     * 
     * @author xtysummer1121@foxmail.com
     * @date 2019年5月4日
     * @return
     */
    @RequestMapping("groupinwithstr")
    public String getinGroup(@RequestBody Map<String, Object> param) {
	return JSONObject.toJSONString(courseUserService.groupIn(Long.valueOf((int) param.get("userId")),
		Long.valueOf((int) param.get("courseId")), (String) param.get("groupKey")));
    }

    /**
     * 
     * TODO 通过组号进入某小组
     * 
     * @author xtysummer1121@foxmail.com
     * @date 2019年5月4日
     * @param courseUser
     * @return
     */
    @RequestMapping("groupinwithid")
    public String getinGroup(@RequestBody CourseUser courseUser) {
	return JSONObject.toJSONString(
		courseUserService.groupIn(courseUser.getUserId(), courseUser.getCourseId(), courseUser.getGroupId()));
    }

    /**
     * 
     * TODO 退出某分组  
     * @author xtysummer1121@foxmail.com
     * @date 2019年5月4日
     * @param courseUser
     * @return
     */
    @RequestMapping("groupout")
    public String quitGroup(@RequestBody CourseUser courseUser) {
	return JSONObject.toJSONString(courseUserService.groupOut(courseUser.getUserId(), courseUser.getCourseId()));
    }

    static public class courseUserBean {
	public Long userId;// 用户id
	public Integer status;// 课堂状态
	public Long courseId;// 课堂id
    }

}
