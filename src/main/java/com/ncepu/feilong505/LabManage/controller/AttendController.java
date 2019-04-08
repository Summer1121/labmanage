package com.ncepu.feilong505.LabManage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.ncepu.feilong505.LabManage.pojo.CourseUser;
import com.ncepu.feilong505.LabManage.service.AttendService;

/**
 * TODO
 * 
 * @author xtysummer1121@foxmail.com
 * @date 2019年3月25日
 */
@RestController
@RequestMapping(value = "/attend", method = RequestMethod.POST)
public class AttendController {
	@Autowired
	AttendService attendService;

	/**
	 * 
	 * TODO 签到
	 * 
	 * @author xtysummer1121@foxmail.com
	 * @date 2019年4月3日
	 * @param bean
	 * @return
	 */
	@RequestMapping("/arrive")
	public String arrive(@RequestBody attendBean bean) {
		return JSONObject.toJSONString(attendService.arrive(bean.userId, bean.courseId, bean.id));
	}

	/**
	 * 
	 * TODO 签退
	 * 
	 * @author xtysummer1121@foxmail.com
	 * @date 2019年4月3日
	 * @param bean
	 * @return
	 */
	@RequestMapping("/leave")
	public String leave(@RequestBody attendBean bean) {
		return JSONObject.toJSONString(attendService.leave(bean.userId, bean.courseId, bean.id));
	}

	/**
	 * 
	 * TODO 生成当前课堂签到或者签退的key码
	 * 
	 * @author xtysummer1121@foxmail.com
	 * @date 2019年4月7日
	 * @param bean
	 * @return
	 */
	@RequestMapping("/getinfo")
	public String createAttendString(@RequestBody attendBean bean) {
		return JSONObject.toJSONString(attendService.createAttendString(bean.courseId, bean.flag));
	}

	static public class attendBean {
		public Long courseId;
		public Long userId;
		public Long id;
		public Integer flag;// 用于判定生成二维码的类别
	}

}
