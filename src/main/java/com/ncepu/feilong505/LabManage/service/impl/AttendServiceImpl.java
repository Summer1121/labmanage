package com.ncepu.feilong505.LabManage.service.impl;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ncepu.feilong505.LabManage.common.ResponseBody;
import com.ncepu.feilong505.LabManage.mapper.AttendMapper;
import com.ncepu.feilong505.LabManage.mapper.CourseMapper;
import com.ncepu.feilong505.LabManage.mapper.CourseUserMapper;
import com.ncepu.feilong505.LabManage.pojo.Attend;
import com.ncepu.feilong505.LabManage.pojo.AttendExample;
import com.ncepu.feilong505.LabManage.pojo.Course;
import com.ncepu.feilong505.LabManage.pojo.CourseExample;
import com.ncepu.feilong505.LabManage.pojo.CourseUser;
import com.ncepu.feilong505.LabManage.pojo.CourseUserExample;
import com.ncepu.feilong505.LabManage.pojo.User;
import com.ncepu.feilong505.LabManage.pojo.UserExample;
import com.ncepu.feilong505.LabManage.service.AttendService;
import com.ncepu.feilong505.LabManage.service.CourseUserService;

/**
 * TODO
 * 
 * @author xtysummer1121@foxmail.com
 * @date 2019年3月25日
 */
@Service
public class AttendServiceImpl implements AttendService {

	@Autowired
	AttendMapper attendMapper;

	@Autowired
	CourseUserMapper courseUserMapper;

	@Autowired
	CourseUserService courseUserServce;

	@Autowired
	CourseMapper courseMapper;

	ResponseBody responseBody;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ncepu.feilong505.LabManage.service.AttendServiceImpl#attend(com.ncepu.
	 * feilong505.LabManage.pojo.CourseUser)
	 */
	@Override
	public ResponseBody arrive(Long userId, Long courseId, Long id) {
		responseBody = new ResponseBody();
		try {
			// 本课堂和用户是否已经绑定
			CourseUser courseUser = new CourseUser();
			courseUser.setUserId(userId);
			courseUser.setCourseId(courseId);
			courseUser = (CourseUser) courseUserServce.findOneCourseUser(courseUser).getData();
			if (courseUser != null) {// 如果已经绑定
				// 创建签到信息
				Attend record = new Attend().setId(id).setAttendCourseUserId(courseUser.getId())
						.setAttendArriveTime(new Date()).setAttendStatus(0);
				attendMapper.insert(record);
				responseBody.success("签到成功");
			} else {
				responseBody.error("本课堂和用户未绑定");
			}
		} catch (Exception e) {
			responseBody.error("发生了错误");
			e.printStackTrace();
		}
		return responseBody;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ncepu.feilong505.LabManage.service.AttendServiceImpl#leave(com.ncepu.
	 * feilong505.LabManage.pojo.CourseUser)
	 */
	@Override
	public ResponseBody leave(Long userId, Long courseId, Long id) {
		responseBody = new ResponseBody();
		try {
			// 本课堂和用户是否已经绑定
			CourseUser courseUser = new CourseUser();
			courseUser.setUserId(userId);
			courseUser.setCourseId(courseId);
			courseUser = (CourseUser) courseUserServce.findOneCourseUser(courseUser).getData();
			if (courseUser != null) {// 如果已经绑定
				// 设置要修改的签到信息的条件（具有相同签到id，具有相同绑定id、状态为未签退、3个小时内的签到）
				// 获取三个小时前的时间
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(new Date());
				calendar.add(calendar.HOUR, -3);
				Date arriveTimeLimit = calendar.getTime();

				AttendExample example = new AttendExample();
				example.createCriteria().andIdEqualTo(id).andAttendCourseUserIdEqualTo(courseUser.getId())
						.andAttendStatusEqualTo(0).andAttendArriveTimeGreaterThanOrEqualTo(arriveTimeLimit);
				// 设置修改的签退信息
				Attend record = new Attend().setAttendStatus(1).setAttendLeaveTime(new Date());
				if (attendMapper.updateByExampleSelective(record, example) >= 1)
					responseBody.success("签退成功");
				else {
					responseBody.error("没有成功签退");
				}
			} else {
				responseBody.error("本课堂和用户未绑定");
			}
		} catch (Exception e) {
			responseBody.error("发生了错误");
			e.printStackTrace();
		}
		return responseBody;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ncepu.feilong505.LabManage.service.AttendServiceImpl#
	 * findListWithCourseUser(com.ncepu.feilong505.LabManage.pojo.CourseUser)
	 */
	@Override
	public ResponseBody findListWithCourseUser(Long userId, Long courseId) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ncepu.feilong505.LabManage.service.AttendServiceImpl#findListWithCourse(
	 * com.ncepu.feilong505.LabManage.pojo.CourseUser)
	 */
	@Override
	public ResponseBody findListWithCourse(Long courseId) {
//		attendExample example = new CourseUserExample();
//		example.createCriteria().andCourseIdEqualTo(courseId);
//		List<User> users = attendMapper.selectByExample(example);
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ncepu.feilong505.LabManage.service.AttendService#createAttendString(java.
	 * lang.Long, int)
	 */

	@Override
	public ResponseBody createAttendString(Long courseId, int flag) {
		responseBody = new ResponseBody();
		try {
			if (flag == 0)// 签到
			{
				int id = courseMapper.selectByPrimaryKey(courseId).getCourseAttendSum() + 1;//签到次数+1
				
				//更新数据库总签到次数
				Course course=new Course().setId(courseId).setCourseAttendSum(id);
				courseMapper.updateByPrimaryKeySelective(course);
				
				Long time = new Date().getTime();
				responseBody.success(courseId + "&" + id + "&" + time + "&" + flag);
			} else if (flag == 1)// 签退
			{
				int id = courseMapper.selectByPrimaryKey(courseId).getCourseAttendSum();
				Long time = new Date().getTime();
				responseBody.success(courseId + "&" + id + "&" + time + "&" + flag);
			}
		} catch (Exception e) {
			responseBody.error("发生了错误");
			e.printStackTrace();
		}
		return responseBody;
	}

}
