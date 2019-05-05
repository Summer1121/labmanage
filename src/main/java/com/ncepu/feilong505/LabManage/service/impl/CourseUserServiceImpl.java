package com.ncepu.feilong505.LabManage.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.generator.codegen.mybatis3.model.ExampleGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.ncepu.feilong505.LabManage.common.ResponseBody;
import com.ncepu.feilong505.LabManage.controller.CourseUserController.courseUserBean;
import com.ncepu.feilong505.LabManage.mapper.CourseMapper;
import com.ncepu.feilong505.LabManage.mapper.CourseUserMapper;
import com.ncepu.feilong505.LabManage.mapper.UserMapper;
import com.ncepu.feilong505.LabManage.pojo.Course;
import com.ncepu.feilong505.LabManage.pojo.CourseExample;
import com.ncepu.feilong505.LabManage.pojo.CourseUser;
import com.ncepu.feilong505.LabManage.pojo.CourseUserExample;
import com.ncepu.feilong505.LabManage.pojo.User;
import com.ncepu.feilong505.LabManage.service.CourseUserService;
import com.ncepu.feilong505.LabManage.vo.CourseVO;
import com.ncepu.feilong505.LabManage.vo.UserVO;

/**
 * TODO
 * 
 * @author xtysummer1121@foxmail.com
 * @date 2019年3月25日
 */
@Service
public class CourseUserServiceImpl implements CourseUserService {

    @Autowired
    CourseUserMapper courseUserMapper;

    @Autowired
    UserMapper usermapper;

    @Autowired
    CourseMapper courseMapper;

    @Resource
    RedisTemplate<String, Object> redisTemplate;

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.ncepu.feilong505.LabManage.service.CourseUserService#addCourseUser(com.
     * ncepu.feilong505.LabManage.pojo.CourseUser)
     */
    @Override
    public ResponseBody addCourseUser(Long userId, Long courseId) {
	ResponseBody responseBody = new ResponseBody();

	try {
	    // 查询用户和课程是否存在
	    if (usermapper.selectByPrimaryKey(userId) == null) {
		responseBody.error("用户不存在");
	    } else if (courseMapper.selectByPrimaryKey(courseId) == null) {
		responseBody.error("班级不存在");
	    } else {
		List<CourseUser> mapedRecords = getCourseUser(userId, courseId);
		// 已经绑定了
		if (mapedRecords != null && mapedRecords.size() != 0) {
		    responseBody.error("用户与课堂已经绑定");
		} else {
		    CourseUser courseUser = new CourseUser().setUserId(userId).setCourseId(courseId);

		    if (courseUserMapper.insert(courseUser) == 1)
			responseBody.success("成功进入课堂");
		}

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
     * com.ncepu.feilong505.LabManage.service.CourseUserService#removeCourseUser(com
     * .ncepu.feilong505.LabManage.pojo.CourseUser)
     */
    @Override
    public ResponseBody removeCourseUser(Long userId, Long courseId) {
	// TODO Auto-generated method stub
	return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.ncepu.feilong505.LabManage.service.CourseUserService#editCourseUser(com.
     * ncepu.feilong505.LabManage.pojo.CourseUser)
     */
    @Override
    public ResponseBody editCourseUser(Long userId, Long courseId) {
	// TODO Auto-generated method stub
	return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.ncepu.feilong505.LabManage.service.CourseUserService#findOneCourseUser(
     * com.ncepu.feilong505.LabManage.pojo.CourseUser)
     */
    @Override
    public ResponseBody findOneCourseUser(CourseUser courseUser) {
	ResponseBody responseBody = new ResponseBody();
	try {
	    responseBody.success(getCourseUser(courseUser.getUserId(), courseUser.getCourseId()).get(0));
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
     * com.ncepu.feilong505.LabManage.service.CourseUserService#findCourseUserList(
     * com.ncepu.feilong505.LabManage.pojo.CourseUser)
     */
//    @Override
    public ResponseBody findUserListWithCourse(CourseUser courseUser) {
	// TODO Auto-generated method stub
	return null;
    }

    /**
     * 
     * TODO 返回对应关系列表（常用查询）
     * 
     * @author xtysummer1121@foxmail.com
     * @date 2019年3月25日
     * @param userId
     * @param courseId
     * @return
     */
    private List<CourseUser> getCourseUser(Long userId, Long courseId) throws Exception {
	CourseUserExample example = new CourseUserExample();
	example.createCriteria().andCourseIdEqualTo(courseId).andUserIdEqualTo(userId);
	return courseUserMapper.selectByExample(example);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.ncepu.feilong505.LabManage.service.CourseUserService#findCourseWithUser(
     * java.lang.Long, java.lang.Integer)
     */
    @Override
    public ResponseBody findCourseWithUser(Long userId, Integer status) {
	ResponseBody responseBody = new ResponseBody();
	try {
//	    CourseUserExample example = new CourseUserExample();
//	    // 查找用户所在的课堂列表
//	    example.createCriteria().andUserIdEqualTo(userId);
//	    List<CourseUser> courseUsers = courseUserMapper.selectByExample(example);
//	    List<CourseVO> courses = new ArrayList<>();
//	    // 检索每个课堂信息，加入结果集
//	    if (courseUsers != null && !courseUsers.isEmpty()) {
//		for (CourseUser cu : courseUsers) {
//		    Course course = courseMapper.selectByPrimaryKey(cu.getCourseId());
//		    if (status != null)
//			if (course == null || course.getCourseStatus() != status)
//			    continue;
//		    CourseVO courseVO = new CourseVO(course);
//		    // 增加教师身份信息
//		    User user = usermapper.selectByPrimaryKey(course.getCourseTeacherId());
//		    courseVO.setTeacherName(user.getUserName()).setTeacherPhone(user.getUserPhone());
//		    courses.add(courseVO);
//		}
//		if (courses.isEmpty())
//		    responseBody.error("本用户未绑定指定状态的课堂");
//		else
//		    responseBody.success(courses);
//	    } else {
//		responseBody.error("本用户未绑定课堂");
//	    }
	    List<CourseVO> courseVOs = courseMapper.selectCourseByUser(userId, status);
	    if (courseVOs != null && !courseVOs.isEmpty()) {
		responseBody.success(courseVOs);
	    } else {
		responseBody.error("未加入课程");
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
     * com.ncepu.feilong505.LabManage.service.CourseUserService#findUserWithCourse(
     * java.lang.Long)
     */
    @Override
    public ResponseBody findUserWithCourse(Long courseId) {
	ResponseBody responseBody = new ResponseBody();
	try {
	    List<UserVO> userVOs = usermapper.selectListByCourse(courseId);
	    if (!userVOs.isEmpty()) {
		responseBody.success(userVOs);
	    } else {
		responseBody.error("未找到用户");
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
     * com.ncepu.feilong505.LabManage.service.CourseUserService#groupin(java.lang.
     * Long, java.lang.Long, java.lang.String)
     */
    @Override
    public ResponseBody groupIn(Long userId, Long courseId, String groupKey) {
	ResponseBody responBody = new ResponseBody();
	try {
	    int groupId;
	    CourseUser courseUser = getCourseUser(userId, courseId).get(0);
	    // 如果缓存中不存在本redis键，则创建一个hashmap，并赋值为第一个
//	    if (redisTemplate.hasKey("courseUser_group__string_key") == false) {
//		redisTemplate.opsForHash().put("labmanage_courseUser_group__string_key",
//			courseUser.getId() + "&" + groupKey, 1);
//		groupId = 1;
//	    }
	    // 如果redis中不存在本hashkey 则创建一个键值对
	    if (redisTemplate.opsForHash().hasKey("labmanage_courseUser_group_string_key",
		    courseUser.getId() + "&" + groupKey) == false) {
		// 从数据库中查找最大的组数并获取n+1
		int maxgroup = courseUserMapper.selectMaxGroupId(courseUser.getId()) + 1;
		redisTemplate.opsForHash().put("labmanage_courseUser_group_string_key",
			courseUser.getId() + "&" + groupKey, maxgroup);
		groupId = maxgroup;
	    }
	    // 已经存在一个键值对，则获取其id值
	    else {
		groupId = (int) redisTemplate.opsForHash().get("labmanage_courseUser_group_string_key",
			courseUser.getId() + "&" + groupKey);
	    }
	    // 将数据存入数据库
	    courseUser.setGroupId(groupId);
	    courseUserMapper.updateByPrimaryKeySelective(courseUser);
	    Map<String, Object> map = new HashMap<String, Object>();
	    map.put("groupId", groupId);
	    responBody.success(map);
	} catch (Exception e) {
	    responBody.error();
	    e.printStackTrace();
	}
	return responBody;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.ncepu.feilong505.LabManage.service.CourseUserService#groupIn(java.lang.
     * Long, java.lang.Long, java.lang.Integer)
     */
    @Override
    public ResponseBody groupIn(Long userId, Long courseId, Integer groupId) {
	ResponseBody responseBody = new ResponseBody();
	try {
	    CourseUser courseUser = getCourseUser(userId, courseId).get(0);
	    if (courseUser != null) {
		if (courseUser.getGroupId() != 0) {
		    responseBody.error("已分组");
		} else {
		    courseUser.setGroupId(groupId);
		    if (courseUserMapper.updateByPrimaryKeySelective(courseUser) == 1) {
			responseBody.success("加入了第" + groupId + "组");
		    }
		}
	    } else {
		responseBody.error("未绑定当前课堂");
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
     * com.ncepu.feilong505.LabManage.service.CourseUserService#groupOut(java.lang.
     * Long, java.lang.Long)
     */
    @Override
    public ResponseBody groupOut(Long userId, Long courseId) {
	ResponseBody responseBody = new ResponseBody();
	try {
	    CourseUser courseUser = getCourseUser(userId, courseId).get(0);
	    if (courseUser == null)
		responseBody.error("未绑定课堂");
	    else {
		courseUser.setGroupId(0);
		if (courseUserMapper.updateByPrimaryKeySelective(courseUser) == 1) {
		    responseBody.success("退出成功");
		} else {
		    responseBody.error("退出失败");
		}
	    }
	} catch (Exception e) {
	    responseBody.error();
	    e.printStackTrace();
	}
	return responseBody;
    }
}
