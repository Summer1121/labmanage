package com.ncepu.feilong505.LabManage.service.impl;

import java.util.List;

import org.mybatis.generator.codegen.mybatis3.model.ExampleGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ncepu.feilong505.LabManage.common.ResponseBody;
import com.ncepu.feilong505.LabManage.mapper.CourseMapper;
import com.ncepu.feilong505.LabManage.mapper.CourseUserMapper;
import com.ncepu.feilong505.LabManage.mapper.UserMapper;
import com.ncepu.feilong505.LabManage.pojo.CourseUser;
import com.ncepu.feilong505.LabManage.pojo.CourseUserExample;
import com.ncepu.feilong505.LabManage.service.CourseUserService;


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

	ResponseBody responseBody;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ncepu.feilong505.LabManage.service.CourseUserService#addCourseUser(com.
	 * ncepu.feilong505.LabManage.pojo.CourseUser)
	 */
	@Override
	public ResponseBody addCourseUser(Long userId, Long courseId) {
		responseBody = new ResponseBody();

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
		responseBody = new ResponseBody();
		try {
			responseBody.setData(getCourseUser(courseUser.getUserId(), courseUser.getCourseId()).get(0));
		} catch (Exception e) {
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
	@Override
	public ResponseBody findCourseUserList(CourseUser courseUser) {
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
}
