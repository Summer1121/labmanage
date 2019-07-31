package com.ncepu.feilong505.LabManage.service.impl;

import com.ncepu.feilong505.LabManage.common.ResponseBody;
import com.ncepu.feilong505.LabManage.mapper.CourseMapper;
import com.ncepu.feilong505.LabManage.mapper.CourseUserMapper;
import com.ncepu.feilong505.LabManage.pojo.Course;
import com.ncepu.feilong505.LabManage.pojo.CourseExample;
import com.ncepu.feilong505.LabManage.pojo.CourseExample.Criteria;
import com.ncepu.feilong505.LabManage.pojo.CourseUser;
import com.ncepu.feilong505.LabManage.pojo.CourseUserExample;
import com.ncepu.feilong505.LabManage.service.CourseService;
import com.ncepu.feilong505.LabManage.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	@Autowired
	CourseUserMapper courseUserMapper;

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
			if (teacherService.ifTeacher(course.getCourseTeacherId()).getStatus() == 200) {
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
	public ResponseBody editCourse(Course course, Long userId) {
		ResponseBody responseBody = new ResponseBody();
		try {
			// 判定当前用户是否是当前课堂教师
			if (userId == null || userId != courseMapper.selectByPrimaryKey(course.getId()).getCourseTeacherId()) {
				responseBody.error("教师身份不匹配");
				return responseBody;
			}

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
	 * @see com.ncepu.feilong505.LabManage.service.CourseService#deleteCourse(com.
	 * ncepu. feilong505.LabManage.pojo.Course)
	 */
	@Override
	public ResponseBody deleteCourse(Course course) {
		ResponseBody responseBody = new ResponseBody();
		try {
			// 判定当前用户是否是当前课堂教师
			if (course.getCourseTeacherId() == null || course.getCourseTeacherId() != courseMapper
					.selectByPrimaryKey(course.getId()).getCourseTeacherId()) {
				responseBody.error("教师身份不匹配");
				return responseBody;
			}

			if (courseMapper.deleteByPrimaryKey(course.getId()) == 1) {
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
	 * @see com.ncepu.feilong505.LabManage.service.CourseService#findOneCourse(com.
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
	 * @see com.ncepu.feilong505.LabManage.service.CourseService#findCourseList(com.
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
				courseCriteria.andCourseTeacherIdEqualTo(course.getCourseTeacherId());
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
	 * @see com.ncepu.feilong505.LabManage.service.CourseService#ifGroup(java.lang.
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
	 * @see com.ncepu.feilong505.LabManage.service.CourseService#groupConfig(java.
	 * lang. Long, java.lang.Integer)
	 */
	@Override
	public ResponseBody groupConfig(Course course, Long userId) {
		ResponseBody responseBody = new ResponseBody();
		try {
			// 判定当前用户是否是当前课堂教师
			if (userId == null || userId != courseMapper.selectByPrimaryKey(course.getId()).getCourseTeacherId()) {
				responseBody.error("教师身份不匹配");
				return responseBody;
			}

			// 修改课堂信息
			Course courseTemp = new Course();
			courseTemp.setId(course.getId());
			courseTemp.setCourseIsGroup(course.getCourseIsGroup());
			int row = courseMapper.updateByPrimaryKeySelective(courseTemp);
			if (row == 1) {
				// 根据当前课堂状态处理分组信息
				switch (course.getCourseIsGroup()) {
					// 关闭分组功能
					case 0:
						responseBody.success("修改成功");
						break;

					// 自主分组模式
					case 1:
						responseBody.success("修改成功");
						break;

					// 试验台序号分组模式
					case 2:
						Map<String, Object> resMap = new HashMap<>();
						// 修改的课堂分组数据数
						resMap.put("changeGroup", groupByPosition(course.getId()));
						responseBody.success(resMap);
						break;

					default:
						break;
				}

			} else {
				responseBody.error("修改失败");
			}
		} catch (

				Exception e) {
			responseBody.error();
			e.printStackTrace();
		}
		return responseBody;
	}

	@Override
	public ResponseBody getCourseWithTeacherId(Long teacherId, Integer status) {
		ResponseBody responseBody = new ResponseBody();
		try {
			responseBody.success(courseMapper.selectCourseByTeacher(teacherId, status));
		} catch (Exception e) {
			e.printStackTrace();
			responseBody.error();
		}
		return responseBody;
	}

	/**
	 * TODO 将某个课堂中的用户按照 试验台号 分组
	 *
	 * @param courseId
	 * @author xtysummer1121@foxmail.com
	 * @date 2019年6月7日
	 * @retrurn 修改的条数
	 */
	Integer groupByPosition(Long courseId) {
		// 修改的记录条数
		int count = 0;
		try {
			Course course = courseMapper.selectByPrimaryKey(courseId);
			if (course == null)
				return count;

			// 查找当前最大组号
//	Integer maxGroupId=courseUserMapper.selectMaxGroupId(courseId);

			// 在courseUser表中查找试验台列表
			List<String> positions = courseUserMapper.selectPositionsList(courseId);

			// 按照列表中的顺序更新本课堂中对应的用户
			CourseUserExample example = new CourseUserExample();
			CourseUser temp = null;

			for (int i = 0; i < positions.size(); i++) {
				example.clear();
				example.or().andCourseIdEqualTo(courseId).andPositionEqualTo(positions.get(i));
				temp = new CourseUser();
				temp.setGroupId(i + 1);
				count += courseUserMapper.updateByExampleSelective(temp, example);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
}
