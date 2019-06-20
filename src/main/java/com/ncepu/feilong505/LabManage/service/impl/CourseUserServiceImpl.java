package com.ncepu.feilong505.LabManage.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.ncepu.feilong505.LabManage.common.ResponseBody;
import com.ncepu.feilong505.LabManage.mapper.CourseMapper;
import com.ncepu.feilong505.LabManage.mapper.CourseUserMapper;
import com.ncepu.feilong505.LabManage.mapper.UserMapper;
import com.ncepu.feilong505.LabManage.pojo.CourseUser;
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

//    @Autowired
//    GroupInfoMapper groupInfoMapper;

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
		CourseUser mapedRecords = getCourseUser(userId, courseId);
		// 已经绑定了
		if (mapedRecords != null) {
		    responseBody.error("用户与课堂已经绑定");
		} else {
		    CourseUser courseUser = new CourseUser().setUserId(userId).setCourseId(courseId);

		    if (courseUserMapper.insertSelective(courseUser) == 1)
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
	    responseBody.success(getCourseUser(courseUser.getUserId(), courseUser.getCourseId()));
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
    private CourseUser getCourseUser(Long userId, Long courseId) throws Exception {
	return courseUserMapper.selectOneCourseUserByUserIdAndCourseId(userId, courseId);
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
		responseBody.setTotal(userVOs.size());
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

    private final static String groupRedisKey = "labmanage_courseUser_group_string_key";

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

	// 因为在获取最大组数的时候有可能在高并发下导致其他线程意外获取相同最大值，所以对数据库操作进行锁定
	synchronized (courseUserMapper) {
	    try {
		int groupId;
		CourseUser courseUser = getCourseUser(userId, courseId);
		// 如果redis中不存在本hashkey 则创建一个键值对
		if (redisTemplate.opsForHash().hasKey(groupRedisKey,
			courseUser.getCourseId() + "&" + groupKey) == false) {
		    // 从数据库中查找本课堂最大的组数并获取n+1
		    Integer maxGroup = courseUserMapper.selectMaxGroupId(courseUser.getCourseId()) + 1;
		    redisTemplate.opsForHash().put(groupRedisKey, courseUser.getCourseId() + "&" + groupKey, maxGroup);
		    // 设置300秒过期
		    redisTemplate.expire(groupRedisKey, 300, TimeUnit.SECONDS);
		    groupId = maxGroup;
		}
		// 已经存在一个键值对，则获取其id值
		else {
		    groupId = (int) redisTemplate.opsForHash().get(groupRedisKey,
			    courseUser.getCourseId() + "&" + groupKey);
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
	    CourseUser courseUser = getCourseUser(userId, courseId);
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
	    CourseUser courseUser = getCourseUser(userId, courseId);
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

    @Override
    public ResponseBody setPosition(Long userId, Long courseId, String position) {
	ResponseBody responseBody = new ResponseBody();

	try {
	    CourseUser courseUser = courseUserMapper.selectOneCourseUserByUserIdAndCourseId(userId, courseId);
	    if (courseUser == null) {
		responseBody.error("查询无果");
		return responseBody;
	    }
	    courseUser.setPosition(position);

	    if (courseUserMapper.updateByPrimaryKeySelective(courseUser) == 1) {
		responseBody.success("试验台号修改为" + position);
	    } else {
		responseBody.error("修改失败");
	    }
	} catch (Exception e) {
	    responseBody.error();
	    e.printStackTrace();
	}

	return responseBody;
    }

    @Override
    public ResponseBody getGroupList(Long courseId) {
	ResponseBody responseBody = new ResponseBody();
	try {
	    List<Integer> groups = courseUserMapper.selectGroupList(courseId);
	    responseBody.success(groups);
	} catch (Exception e) {
	    responseBody.error();
	    e.printStackTrace();
	}
	return responseBody;
    }

    @Override
    public ResponseBody getMyGroup(Long courseId, Long userId) {
	ResponseBody responseBody = new ResponseBody();

	try {
	    responseBody.success(getCourseUser(userId,courseId).getGroupId());
	} catch (Exception e) {
	    responseBody.error();
	    e.printStackTrace();
	}

	return responseBody;
    }
}
