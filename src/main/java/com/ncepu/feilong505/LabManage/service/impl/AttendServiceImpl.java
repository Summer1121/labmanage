package com.ncepu.feilong505.LabManage.service.impl;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.ncepu.feilong505.LabManage.vo.AttendStatis;

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

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.ncepu.feilong505.LabManage.service.AttendServiceImpl#attend(com.ncepu.
     * feilong505.LabManage.pojo.CourseUser)
     */
    @Override
    public ResponseBody arrive(Long userId, Long courseId, Long id) {
	ResponseBody responseBody = new ResponseBody();
	try {
	    // 本课堂和用户是否已经绑定
	    CourseUser courseUser = new CourseUser();
	    courseUser.setUserId(userId);
	    courseUser.setCourseId(courseId);
	    courseUser = (CourseUser) courseUserServce.findOneCourseUser(courseUser).getData();
	    if (courseUser != null) {// 如果已经绑定
		//查看是否已经签到
		AttendExample attendExample = new AttendExample();
		attendExample.createCriteria().andAttendCourseUserIdEqualTo(courseUser.getId());
		if (attendMapper.selectByExample(attendExample).isEmpty()) {//如果未签到
		    // 创建签到信息
		    Attend record = new Attend().setId(id).setAttendCourseUserId(courseUser.getId())
			    .setAttendArriveTime(new Date()).setAttendStatus(0);
		    attendMapper.insert(record);
		    responseBody.success("签到成功");
		} else {
		    responseBody.error("请勿重复签到");
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
     * @see
     * com.ncepu.feilong505.LabManage.service.AttendServiceImpl#leave(com.ncepu.
     * feilong505.LabManage.pojo.CourseUser)
     */
    @Override
    public ResponseBody leave(Long userId, Long courseId, Long id) {
	ResponseBody responseBody = new ResponseBody();
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
     * @see
     * com.ncepu.feilong505.LabManage.service.AttendServiceImpl#findListWithCourse(
     * com.ncepu.feilong505.LabManage.pojo.CourseUser)
     */
    @Override
    public ResponseBody findListWithCourse(Long courseId) {
	ResponseBody responseBody = new ResponseBody();
	try {
	    List<AttendStatis> attendStatis = attendMapper.selectAttendStatis(courseId);
	    if (attendStatis != null && !attendStatis.isEmpty()) {
		responseBody.success(attendStatis);
	    } else {
		responseBody.error("未查询到签到信息");
	    }
	} catch (Exception e) {
	    responseBody.error();
	    e.printStackTrace();
	}
	return responseBody;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.ncepu.feilong505.LabManage.service.AttendService#findListWithUser(java.
     * lang.Long)
     */
    @Override
    public ResponseBody findListWithUser(Long userId, Long courseId) {
	ResponseBody responseBody = new ResponseBody();
	try {
	    CourseUserExample courseUserExample = new CourseUserExample();
	    courseUserExample.createCriteria().andUserIdEqualTo(userId).andCourseIdEqualTo(courseId);
	    CourseUser courseUser = courseUserMapper.selectByExample(courseUserExample).get(0);
	    if (courseUser != null) {
		AttendExample attendExample = new AttendExample();
		attendExample.createCriteria().andAttendCourseUserIdEqualTo(courseUser.getId());
		List<Attend> attends = attendMapper.selectByExample(attendExample);
		if (attends != null && !attends.isEmpty()) {
		    responseBody.success(attends);
		} else {
		    responseBody.error("查无结果");
		}
	    } else {
		responseBody.error("查无结果");
	    }
	} catch (Exception e) {
	    responseBody.error();
	    e.printStackTrace();
	}
	return responseBody;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.ncepu.feilong505.LabManage.service.AttendService#findAttendList(java.lang
     * .Long, java.lang.Integer)
     */
    @Override
    public ResponseBody findAttendList(Long courseId, Long id, Integer flag) {
	ResponseBody responseBody = new ResponseBody();
	try {
	    if (flag == null)
		flag = 0;
	    List<AttendStatis> attendStatis = attendMapper.selectAttendByCourse(courseId, id, flag);
	    if (attendStatis != null && !attendStatis.isEmpty()) {
		responseBody.success(attendStatis);
	    } else {
		responseBody.error("查无结果");
	    }
	} catch (Exception e) {
	    responseBody.error();
	    e.printStackTrace();
	}
	return responseBody;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.ncepu.feilong505.LabManage.service.AttendService#getAttendCount(java.lang
     * .Long, java.lang.Long)
     */
    @Override
    public ResponseBody getAttendCount(Long courseId, Long id) {
	Map<String, Integer> result = new HashMap<String, Integer>();
	ResponseBody responseBody = new ResponseBody();
	try {
	    // 获取签到人数
	    int arrive = attendMapper.selectAttendCount(courseId, id);

	    // 获取课堂总人数
	    CourseUserExample courseUserExample = new CourseUserExample();
	    courseUserExample.createCriteria().andCourseIdEqualTo(courseId);
	    int total = (int) courseUserMapper.countByExample(courseUserExample);
	    // 获取未签到人数
	    int notArrive = total - arrive;

	    result.put("arrive", arrive);
	    result.put("total", total);
	    result.put("notArrive", notArrive);

	    if (!result.isEmpty())
		responseBody.success(result);
	    else {
		responseBody.error("查无结果");
	    }
	} catch (Exception e) {
	    responseBody.error();
	    e.printStackTrace();
	}
	return responseBody;
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
	ResponseBody responseBody = new ResponseBody();
	try {
	    if (flag == 0)// 签到
	    {
		int id = courseMapper.selectByPrimaryKey(courseId).getCourseAttendSum() + 1;// 签到次数+1

		// 更新数据库总签到次数
		Course course = new Course().setId(courseId).setCourseAttendSum(id);
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
