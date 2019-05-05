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

    /**
     * 
     * TODO 获取某次签到的列表
     * 
     * @author xtysummer1121@foxmail.com
     * @date 2019年4月21日
     * @param bean
     * @return
     */
    @RequestMapping("/getnowlist")
    public String getNowAttendList(@RequestBody attendBean bean) {
	return JSONObject.toJSONString(attendService.findAttendList(bean.courseId, bean.id, bean.flag));
    }

    /**
     * 
     * TODO 获取某次签到的数据统计  
     * @author xtysummer1121@foxmail.com
     * @date 2019年4月27日
     * @param bean
     * @return
     */
    @RequestMapping("/getcount")
    public String getAttendCount(@RequestBody attendBean bean) {
	return JSONObject.toJSONString(attendService.getAttendCount(bean.courseId, bean.id));
    }

    /**
     * 
     * TODO 获取某课堂的签到统计
     * 
     * @author xtysummer1121@foxmail.com
     * @date 2019年4月21日
     * @param bean
     * @return
     */
    @RequestMapping("/getbycourse")
    public String getListByCourse(@RequestBody attendBean bean) {
	return JSONObject.toJSONString(attendService.findListWithCourse(bean.courseId));
    }

    /**
     * 
     * TODO 获取某用户在某课堂的签到统计
     * 
     * @author xtysummer1121@foxmail.com
     * @date 2019年4月21日
     * @param bean
     * @return
     */
    @RequestMapping("/getbyuser")
    public String getListByUser(@RequestBody attendBean bean) {
	return JSONObject.toJSONString(attendService.findListWithUser(bean.userId, bean.courseId));
    }

    static public class attendBean {
	public Long courseId;
	public Long userId;
	public Long id;
	public Integer flag;// 用于判定生成二维码的类别
			    // 用于在获取签到列表时选择签到者还是未签到者
    }

}
