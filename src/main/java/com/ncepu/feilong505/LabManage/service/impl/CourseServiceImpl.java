package com.ncepu.feilong505.LabManage.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ncepu.feilong505.LabManage.common.ResponseBody;
import com.ncepu.feilong505.LabManage.mapper.CourseMapper;
import com.ncepu.feilong505.LabManage.mapper.TeacherMapper;
import com.ncepu.feilong505.LabManage.pojo.Course;
import com.ncepu.feilong505.LabManage.pojo.CourseExample;
import com.ncepu.feilong505.LabManage.pojo.CourseExample.Criteria;
import com.ncepu.feilong505.LabManage.pojo.Teacher;
import com.ncepu.feilong505.LabManage.service.CourseService;
import com.ncepu.feilong505.LabManage.service.TeacherService;

/**
 * TODO
 * 
 * @author xtysummer1121@foxmail.com
 * @date 2019年4月12日
 */
@Service
public class CourseServiceImpl implements CourseService {
	@Autowired
	CourseMapper courseMapper;
	@Autowired
	TeacherService teacherService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ncepu.feilong505.LabManage.service.CourseService#addCourse(com.ncepu.
	 * feilong505.LabManage.pojo.Course)
	 */
	@Override
	public ResponseBody addCourse(Course course) {
		ResponseBody responseBody = new ResponseBody();
		try {
			if (teacherService.ifTeacher(course.getCourseTeacherId())
					.getStatus() == 200) {
				course.setId(null);
				if (course.getCourseBuildTime() == null)
					course.setCourseBuildTime(new Date());
				courseMapper.insertSelective(course);
				responseBody.success("添加成功");
			} else {
				responseBody.error("用户非教师");
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
	 * @see com.ncepu.feilong505.LabManage.service.CourseService#editCourse(com.
	 * ncepu. feilong505.LabManage.pojo.Course)
	 */
	@Override
	public ResponseBody editCourse(Course course) {
		ResponseBody responseBody = new ResponseBody();
		try {
			if (courseMapper.updateByPrimaryKeySelective(course) == 1)
				responseBody.success("修改成功");
			else {
				responseBody.error("未修改任何数据");
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
	 * com.ncepu.feilong505.LabManage.service.CourseService#deleteCourse(com.
	 * ncepu. feilong505.LabManage.pojo.Course)
	 */
	@Override
	public ResponseBody deleteCourse(Long courseId) {
		ResponseBody responseBody = new ResponseBody();
		try {
			if (courseMapper.deleteByPrimaryKey(courseId) == 1) {
				responseBody.success("删除成功");
			} else {
				responseBody.error("未删除任何数据");
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
	 * com.ncepu.feilong505.LabManage.service.CourseService#findOneCourse(com.
	 * ncepu. feilong505.LabManage.pojo.Course)
	 */
	@Override
	public ResponseBody findOneCourse(Long courseId) {
		ResponseBody responseBody = new ResponseBody();
		try {
			Course result = courseMapper.selectByPrimaryKey(courseId);
			if (result != null) {
				responseBody.success(result);
			} else {
				responseBody.error("查找无果");
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
	 * com.ncepu.feilong505.LabManage.service.CourseService#findCourseList(com.
	 * ncepu .feilong505.LabManage.pojo.Course)
	 */
	@Override
	public ResponseBody findCourseList(Course course) {
		ResponseBody responseBody = new ResponseBody();
		try {
			CourseExample example = new CourseExample();
			// 条件
			Criteria courseCriteria = example.createCriteria();
			// 按照教师Id查询
			if (course.getCourseTeacherId() != null) {
				courseCriteria
						.andCourseTeacherIdEqualTo(course.getCourseTeacherId());
			}
			// 按照课堂状态查询
			if (course.getCourseStatus() != null) {
				courseCriteria.andCourseStatusEqualTo(course.getCourseStatus());
			}
			// 条件加入实例
			example.or(courseCriteria);
			List<Course> courses = courseMapper.selectByExample(example);
			if (courses != null && !courses.isEmpty()) {
				responseBody.success(courses);
			} else {
				responseBody.error("查询无果");
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
	 * com.ncepu.feilong505.LabManage.service.CourseService#ifGroup(java.lang.
	 * Long)
	 */
	@Override
	public ResponseBody ifGroup(Long courseId) {
		ResponseBody responseBody = new ResponseBody();
		try {
			Course course = courseMapper.selectByPrimaryKey(courseId);
			if (course != null) {
				int flag = course.getCourseIsGroup();
				Map<String, Integer> map = new HashMap<String, Integer>();
				map.put("ifgrouped", flag);
				responseBody.success(map);
			} else {
				responseBody.error("未找到课堂信息");
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
	 * com.ncepu.feilong505.LabManage.service.CourseService#groupConfig(java.
	 * lang. Long, java.lang.Integer)
	 */
	@Override
	public ResponseBody groupConfig(Long courseId, Integer status) {
		ResponseBody responseBody = new ResponseBody();
		try {
			Course course = new Course();
			course.setId(courseId);
			course.setCourseIsGroup(status);
			int row = courseMapper.updateByPrimaryKeySelective(course);
			if (row == 1) {
				responseBody.success("修改成功");
			} else {
				responseBody.error("修改失败");
			}
		} catch (Exception e) {
			responseBody.error();
			e.printStackTrace();
		}
		return responseBody;
	}

}
