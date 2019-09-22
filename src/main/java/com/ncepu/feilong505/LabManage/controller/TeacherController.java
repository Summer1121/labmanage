package com.ncepu.feilong505.LabManage.controller;

import com.alibaba.fastjson.JSONObject;
import com.ncepu.feilong505.LabManage.pojo.Teacher;
import com.ncepu.feilong505.LabManage.pojo.User;
import com.ncepu.feilong505.LabManage.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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
	 * TODO 添加教师(管理员功能)
	 *
	 * @param teacher
	 * @return
	 * @author xtysummer1121@foxmail.com
	 * @date 2019年4月6日
	 */
	@RequestMapping("/admin/add")
	public String addTeacher(@RequestBody Teacher teacher) {
		return JSONObject.toJSONString(teacherService.addTeacher(teacher));
	}

	/**
	 * TODO 用户教师身份绑定
	 *
	 * @param teacherPhone
	 * @param userLong
	 * @return
	 * @author xtysummer1121@foxmail.com
	 * @date 2019年4月6日
	 */
	@RequestMapping("/bind")
	public String bindTeacher(@RequestBody BindBean bindBean) {
		return JSONObject.toJSONString(teacherService.bindTeacher(bindBean.teacherPhone, bindBean.code,
				bindBean.userId));
	}

	/**
	 * TODO 验证是否已绑定教师
	 *
	 * @param userId
	 * @return
	 * @author xtysummer1121@foxmail.com
	 * @date 2019年4月6日
	 */
	@RequestMapping("/ifteacher")
	public String ifTeacher(@RequestBody User user) {
		return JSONObject.toJSONString(teacherService.ifTeacher(user.getId()));
	}

	/**
	 * TODO 获取并发送短信验证码
	 *
	 * @param user
	 * @param request
	 * @return java.lang.String
	 * @className TeacherController
	 * @author xtysummer1121@foxmail.com
	 * @methodName getBindCode
	 * @date 2019-09-15
	 */
	@RequestMapping("/bind/code")
	public String getBindCode(@RequestBody User user, HttpServletRequest request) {
		Long userId = (Long) request.getSession().getAttribute("userId");
		if (userId != null)
			user.setId(userId);
		return JSONObject.toJSONString(teacherService.getBindCode(user.getUserPhone(), user.getId()));
	}

	static public class BindBean {
		public String teacherPhone;
		public Long userId;
		//验证码
		public String code;
	}
}
