package com.ncepu.feilong505.LabManage.service;

import com.ncepu.feilong505.LabManage.common.ResponseBody;
import com.ncepu.feilong505.LabManage.controller.AttendController.AttendCondition;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

/**
 * TODO 考勤服务
 *
 * @author xtysummer1121@foxmail.com
 * @date 2019年3月25日
 */
@Service
public interface AttendService {
	/**
	 * TODO 签到
	 *
	 * @param userId, courseId
	 * @return
	 * @author xtysummer1121@foxmail.com
	 * @date 2019年3月25日
	 */
	public ResponseBody arrive(Long userId, Long courseId, Long id);

	/**
	 * TODO 签退
	 *
	 * @return
	 * @author xtysummer1121@foxmail.com
	 * @date 2019年3月25日
	 */
	public ResponseBody leave(Long userId, Long courseId, Long id);

	/**
	 * TODO 查询某个课堂的某次签到情况
	 *
	 * @param courseId
	 * @param id
	 * @param flag     0表示签到用户 1表示未签到的用户
	 * @return
	 * @author xtysummer1121@foxmail.com
	 * @date 2019年4月20日
	 */
	public ResponseBody findAttendList(Long courseId, Long id, Integer flag);

	/**
	 * TODO 某班级某次签到人数统计
	 *
	 * @param courseId
	 * @param id
	 * @return
	 * @author xtysummer1121@foxmail.com
	 * @date 2019年4月27日
	 */
	public ResponseBody getAttendCount(Long courseId, Long id);

	/**
	 * TODO 获取某个班级按次数签到列表
	 *
	 * @param courseId
	 * @return
	 * @author xtysummer1121@foxmail.com
	 * @date 2019年6月11日
	 */
	public ResponseBody getAttendCountList(Long courseId);

	/**
	 * TODO 获取某个班级的学生考勤情况统计
	 *
	 * @param courseId
	 * @return
	 * @author xtysummer1121@foxmail.com
	 * @date 2019年3月25日
	 */
	public ResponseBody findListWithCourse(Long courseId);

	/**
	 * TODO 获取某用户在某个班级的签到记录
	 *
	 * @param userId
	 * @param courseId
	 * @return
	 * @author xtysummer1121@foxmail.com
	 * @date 2019年4月21日
	 */
	public ResponseBody findListWithUser(Long userId, Long courseId);

	/**
	 * TODO  生成当前签到或者签退key码
	 *
	 * @param userId   本数值来源于session
	 * @param courseId
	 * @param flag     0为签到，1为签退
	 * @return
	 * @author xtysummer1121@foxmail.com
	 * @date 2019年6月17日
	 */
	public ResponseBody createAttendString(Long userId, Long courseId, int flag);

	/**
	 * TODO 输出某个班级的签到记录列表
	 *
	 * @param courseId
	 * @return
	 * @author xtysummer1121@foxmail.com
	 * @date 2019年6月11日
	 */
	public ResponseBody fileOut(Long courseId, HttpServletResponse response);

	/**
	 * TODO 条件查询签到记录
	 *
	 * @param condition
	 * @return
	 * @author xtysummer1121@foxmail.com
	 * @date 2019年6月12日
	 */
	public ResponseBody findWithCondition(AttendCondition condition);

	/**
	 * TODO 文件输出条件查询签到记录结果
	 *
	 * @param condition
	 * @return
	 * @author xtysummer1121@foxmail.com
	 * @date 2019年6月12日
	 */
	public ResponseBody fileOutWithCondition(AttendCondition condition, HttpServletResponse response);

}
