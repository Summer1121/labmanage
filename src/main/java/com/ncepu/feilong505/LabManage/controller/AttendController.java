package com.ncepu.feilong505.LabManage.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.ncepu.feilong505.LabManage.common.BaseCondition;
import com.ncepu.feilong505.LabManage.service.AttendService;
import com.rabbitmq.client.RpcClient.Response;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

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
    public String arrive(@RequestBody AttendBean bean, HttpServletRequest request) {
	if (bean.getUserId() == null)
	    bean.userId = (Long) request.getSession().getAttribute("userId");
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
    public String leave(@RequestBody AttendBean bean, HttpServletRequest request) {
	if (bean.getUserId() == null)
	    bean.userId = (Long) request.getSession().getAttribute("userId");
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
    public String createAttendString(@RequestBody AttendBean bean, HttpServletRequest request) {
	bean.userId = (Long) request.getSession().getAttribute("userId");
	return JSONObject.toJSONString(attendService.createAttendString(bean.userId, bean.courseId, bean.flag));
    }

    /**
     * 
     * TODO 获取某次签到的列表(签到/未签到的用户列表)
     * 
     * @author xtysummer1121@foxmail.com
     * @date 2019年4月21日
     * @param bean
     * @param flag 0签到者 1未签到者
     * @return
     */
    @RequestMapping("/getnowlist")
    public String getNowAttendList(@RequestBody AttendBean bean) {
	return JSONObject.toJSONString(attendService.findAttendList(bean.courseId, bean.id, bean.flag));
    }

    /**
     * 
     * TODO 获取某次签到的数据统计(人数统计)
     * 
     * @author xtysummer1121@foxmail.com
     * @date 2019年4月27日
     * @param bean
     * @return
     */
    @RequestMapping("/getcount")
    public String getAttendCount(@RequestBody AttendBean bean) {
	return JSONObject.toJSONString(attendService.getAttendCount(bean.courseId, bean.id));
    }

    /**
     * 
     * TODO 获取某课堂签到按次数的数据统计列表(人数统计)
     * 
     * @author xtysummer1121@foxmail.com
     * @date 2019年6月11日
     * @param bean
     * @return
     */
    @RequestMapping("/getcountlist")
    public String getAttendCountList(@RequestBody AttendBean bean) {
	return JSONObject.toJSONString(attendService.getAttendCountList(bean.courseId));
    }

    /**
     * 
     * TODO 获取某课堂的签到统计(各用户签到次数)
     * 
     * @author xtysummer1121@foxmail.com
     * @date 2019年4月21日
     * @param bean
     * @return
     * @throws InterruptedException
     */
    @RequestMapping("/getbycourse")
    public String getListByCourse(@RequestBody AttendBean bean) throws InterruptedException {
	return JSONObject.toJSONString(attendService.findListWithCourse(bean.courseId));
    }

    /**
     * 
     * TODO 获取某用户在某课堂的签到统计(签到记录列表) （学生与教师功能 ）
     * 
     * @author xtysummer1121@foxmail.com
     * @date 2019年4月21日
     * @param bean
     * @return
     */
    @RequestMapping("/getbyuser")
    public String getListByUser(@RequestBody AttendBean bean) {
	return JSONObject.toJSONString(attendService.findListWithUser(bean.userId, bean.courseId));
    }

    /**
     * 
     * TODO 输出某个班级的签到记录
     * 
     * @author xtysummer1121@foxmail.com
     * @date 2019年6月12日
     * @return
     */
    @RequestMapping("download/classlist")
    public String attendfileOut(@RequestBody AttendBean bean, HttpServletResponse response) {
	return JSONObject.toJSONString(attendService.fileOut(bean.courseId, response));
    }

    /**
     * 
     * TODO 通过条件查询签到记录
     * 
     * @author xtysummer1121@foxmail.com
     * @date 2019年6月13日
     * @param condition
     * @return
     */
    @RequestMapping("/search")
    public String attendWithCondition(@RequestBody AttendCondition condition) {
	return JSONObject.toJSONString(attendService.findWithCondition(condition));
    }

    /**
     * 
     * TODO 导出查询记录
     * 
     * @author xtysummer1121@foxmail.com
     * @date 2019年6月13日
     * @param condition
     * @return
     */
    @RequestMapping("/download/search")
    public String attendFileOutWithCondition(@RequestBody AttendCondition condition, HttpServletResponse response) {
	return JSONObject.toJSONString(attendService.fileOutWithCondition(condition, response));
    }

    @Getter
    @Setter
    public static class AttendBean {
	private Long courseId;
	private Long userId;
	private Long id;
	private Integer flag;// 用于判定生成二维码的类别
    }

    // 用于设定接收查询条件的bean
    @Getter
    @Setter
    @Accessors(chain = true)
    public static class AttendCondition extends BaseCondition {
	String userNum;// 学号
	String userClass;// 班级
	String userName;// 学生姓名
	String courseId;// 课堂id
    }
}
