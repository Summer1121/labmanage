package com.ncepu.feilong505.LabManage.controller;

import com.alibaba.fastjson.JSONObject;
import com.ncepu.feilong505.LabManage.pojo.Course;
import com.ncepu.feilong505.LabManage.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * TODO 课堂信息前端控制器
 *
 * @author xtysummer1121@foxmail.com
 * @date 2019年4月6日
 */
@RestController
@RequestMapping(path = "/course", method = RequestMethod.POST)
public class CourseController {
	@Autowired
	CourseService courseService;

	/**
	 * TODO 添加课堂
	 *
	 * @param course
	 * @return
	 * @author xtysummer1121@foxmail.com
	 * @date 2019年4月12日
	 */
	@RequestMapping("/add")
	public String addCourse(@RequestBody Course course) {
		return JSONObject.toJSONString(courseService.addCourse(course));
	}

	/**
	 * TODO 修改课堂信息
	 *
	 * @param course
	 * @return
	 * @author xtysummer1121@foxmail.com
	 * @date 2019年4月12日
	 */
	@RequestMapping("/update")
	public String updateCourse(@RequestBody Course course, HttpServletRequest request) {
		Long userId = (Long) request.getSession().getAttribute("userId");
		return JSONObject.toJSONString(courseService.editCourse(course, userId));
	}

	/**
	 * TODO 查找一个课堂
	 *
	 * @param course
	 * @return
	 * @author xtysummer1121@foxmail.com
	 * @date 2019年4月12日
	 */
	@RequestMapping("/find")
	public String findOneCourse(@RequestBody Course course) {
		return JSONObject.toJSONString(courseService.findOneCourse(course.getId()));
	}

	/**
	 * TODO 根据条件查找课堂
	 *
	 * @param course
	 * @return
	 * @author xtysummer1121@foxmail.com
	 * @date 2019年4月12日
	 */
	@RequestMapping("/findlist")
	public String findCourseList(@RequestBody Course course) {
		return JSONObject.toJSONString(courseService.findCourseList(course));
	}

	/**
	 * TODO 获取教师创建的课堂列表
	 *
	 * @param course
	 * @return java.lang.String
	 * @className CourseController
	 * @author xtysummer1121@foxmail.com
	 * @methodName getCourseListByTeacher
	 * @date 2019-07-10
	 */
	@RequestMapping("/listwithteacher")
	public String getCourseListByTeacher(@RequestBody Course course) {
		return JSONObject.toJSONString(courseService.getCourseWithTeacherId(course.getCourseTeacherId(),
				course.getCourseStatus()));
	}

	/**
	 * TODO 查询课堂分组状态
	 *
	 * @param course
	 * @return
	 * @author xtysummer1121@foxmail.com
	 * @date 2019年5月3日
	 */
	@RequestMapping("/getgroupconfig")
	public String isGrouped(@RequestBody Course course) {
		return JSONObject.toJSONString(courseService.ifGroup(course.getId()));
	}

	/**
	 * TODO 修改课堂分组状态
	 *
	 * @param param Long id,Integer status
	 * @return
	 * @author xtysummer1121@foxmail.com
	 * @date 2019年5月3日
	 */
	@RequestMapping("/putgroupconfig")
	public String groupConfig(@RequestBody Map<String, Object> param, HttpServletRequest request) {
		Course course = new Course();
		Long userId = (Long) request.getSession().getAttribute("userId");
		course.setId(Long.valueOf((param.get("id").toString()))).setCourseIsGroup((int) param.get("status"));
		return JSONObject.toJSONString(courseService.groupConfig(course, userId));
	}
}
