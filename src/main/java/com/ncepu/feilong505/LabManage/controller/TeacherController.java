package com.ncepu.feilong505.LabManage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.ncepu.feilong505.LabManage.pojo.Teacher;
import com.ncepu.feilong505.LabManage.pojo.User;
import com.ncepu.feilong505.LabManage.service.TeacherService;
import com.rabbitmq.client.RpcClient.Response;

/**
 * TODO 教师前端控制器
 * 
 * @author xtysummer1121@foxmail.com
 * @date 2019年4月6日
 */
@RestController
@RequestMapping(path = "/teacher", method = RequestMethod.POST)
public class TeacherController {
    @Autowired
    TeacherService teacherService;

    /**
     * 
     * TODO 添加教师(管理员功能)
     * 
     * @author xtysummer1121@foxmail.com
     * @date 2019年4月6日
     * @param teacher
     * @return
     */
    @RequestMapping("/admin/add")
    public String addTeacher(@RequestBody Teacher teacher) {
	return JSONObject.toJSONString(teacherService.addTeacher(teacher));
    }

    /**
     * 
     * TODO 用户教师身份绑定
     * 
     * @author xtysummer1121@foxmail.com
     * @date 2019年4月6日
     * @param teacherPhone
     * @param userLong
     * @return
     */
    @RequestMapping("/bind")
    public String bindTeacher(@RequestBody BindBean bindBean) {
	return JSONObject.toJSONString(teacherService.bindTeacher(bindBean.teacherPhone, bindBean.userId));
    }

    /**
     * 
     * TODO 验证是否已绑定教师
     * 
     * @author xtysummer1121@foxmail.com
     * @date 2019年4月6日
     * @param userId
     * @return
     */
    @RequestMapping("/ifteacher")
    public String ifTeacher(@RequestBody User user) {
	return JSONObject.toJSONString(teacherService.ifTeacher(user.getId()));
    }

    static public class BindBean {
	public String teacherPhone;
	public Long userId;
    }
}
