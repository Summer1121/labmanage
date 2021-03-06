package com.ncepu.feilong505.LabManage.controller;

import com.alibaba.fastjson.JSONObject;
import com.ncepu.feilong505.LabManage.common.ResponseBody;
import com.ncepu.feilong505.LabManage.pojo.User;
import com.ncepu.feilong505.LabManage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * TODO 用户模块控制器
 *
 * @author xtysummer1121@foxmail.com
 * @date 2019年3月21日
 */
@RestController
@RequestMapping(path = "/user", method = RequestMethod.POST)
public class UserController {
	@Autowired
	UserService userService;

	/**
	 * TODO 登录
	 *
	 * @param user
	 * @return
	 * @author xtysummer1121@foxmail.com
	 * @date 2019年3月25日
	 */
	@RequestMapping("/login")
	public String login(@RequestBody User user, HttpServletRequest request) {
		return JSONObject.toJSONString(userService.Login(user, request));
	}

	/**
	 * TODO 微信登录
	 *
	 * @param user
	 * @return
	 * @author xtysummer1121@foxmail.com
	 * @date 2019年3月25日
	 */
	@RequestMapping("/wxlogin")
	public String wxLogin(@RequestBody User user, HttpServletRequest request) {
		return JSONObject.toJSONString(userService.WXLogin(user, request));
	}

	/**
	 * TODO 注册
	 *
	 * @param user
	 * @return
	 * @author xtysummer1121@foxmail.com
	 * @date 2019年3月25日
	 */
	@RequestMapping("/regis")
	public String regis(@RequestBody User user) {
		return JSONObject.toJSONString(userService.register(user));
	}

	/**
	 * TODO 注销
	 *
	 * @param request
	 * @return
	 * @author xtysummer1121@foxmail.com
	 * @date 2019年4月3日
	 */
	@RequestMapping(path = "/logout")
	public String Logout(HttpServletRequest request) {

		ResponseBody responseBody = new ResponseBody();
		try {
			request.getSession().removeAttribute("logined");
			responseBody.success("注销成功");
		} catch (Exception e) {
			responseBody.error("发生了错误");
			e.printStackTrace();
		}
		return JSONObject.toJSONString(responseBody);
	}

	/**
	 * TODO 修改用户信息
	 *
	 * @param user
	 * @return
	 * @author xtysummer1121@foxmail.com
	 * @date 2019年4月8日
	 */
	@RequestMapping("/edit")
	public String editUser(@RequestBody User user, HttpServletRequest request) {
		user.setId((Long) request.getSession().getAttribute("userId"));
		return JSONObject.toJSONString(userService.editUser(user));
	}

	/**
	 * TODO 通过条件查询用户
	 *
	 * @param bean
	 * @return
	 * @throws InterruptedException
	 * @author xtysummer1121@foxmail.com
	 * @date 2019年4月8日
	 */
	@RequestMapping("/find")
	public String findUser(@RequestBody UserBean bean) throws InterruptedException {
		return JSONObject.toJSONString(userService.findUserList(bean.userId, bean.courseId, bean.groupId, bean.flag));
	}

	public static class UserBean {
		public Long userId;
		public Long courseId;
		public Integer groupId;
		public Integer flag;
	}
}
