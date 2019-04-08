package com.ncepu.feilong505.LabManage.service;

import org.springframework.stereotype.Service;

import com.ncepu.feilong505.LabManage.common.ResponseBody;
import com.ncepu.feilong505.LabManage.pojo.Attend;
import com.ncepu.feilong505.LabManage.pojo.CourseUser;
import com.rabbitmq.client.RpcClient.Response;

/**
 * TODO 考勤服务
 *
 * @author xtysummer1121@foxmail.com
 * @date 2019年3月25日
 */
@Service
public interface AttendService {
	/**
	 * 
	 * TODO 签到
	 * 
	 * @author xtysummer1121@foxmail.com
	 * @date 2019年3月25日
	 * @param userId, courseId
	 * @return
	 */
	public ResponseBody arrive(Long userId, Long courseId,Long id);

	/**
	 * 
	 * TODO 签退
	 * 
	 * @author xtysummer1121@foxmail.com
	 * @date 2019年3月25日
	 * @param attend
	 * @return
	 */
	public ResponseBody leave(Long userId, Long courseId,Long id);

	/**
	 * 
	 * TODO 通过一个 课堂和用户的绑定 查询（某用户在某课堂的情况）
	 * 
	 * @author xtysummer1121@foxmail.com
	 * @date 2019年3月25日
	 * @param attend
	 * @return
	 */
	public ResponseBody findListWithCourseUser(Long userId, Long courseId);

	/**
	 * 
	 * TODO 获取某个班级的学生考勤情况统计
	 * 
	 * @author xtysummer1121@foxmail.com
	 * @date 2019年3月25日
	 * @param attend
	 * @return
	 */
	public ResponseBody findListWithCourse(Long courseId);
	
	/**
	 * 
	 * TODO  生成当前签到或者签退key码
	 * @author xtysummer1121@foxmail.com
	 * @date 2019年4月7日
	 * @param courseId
	 * @param flag 0表示签到  1表示签退
	 * @return
	 */
	public ResponseBody createAttendString(Long courseId,int flag);
}
