package com.ncepu.feilong505.LabManage.service;

import org.springframework.stereotype.Service;

import com.ncepu.feilong505.LabManage.common.ResponseBody;
import com.ncepu.feilong505.LabManage.pojo.Course;

/**
 * TODO
 * 
 * @author xtysummer1121@foxmail.com
 * @date 2019年3月12日
 */
@Service
public interface CourseService {
	/**
	 * 
	 * TODO 添加课堂
	 * 
	 * @author xtysummer1121@foxmail.com
	 * @date 2019年3月14日
	 * @return
	 */
	public ResponseBody addCourse(Course course);

	/**
	 * 
	 * TODO 修改课堂信息
	 * 
	 * @author xtysummer1121@foxmail.com
	 * @date 2019年3月14日
	 * @return
	 */
	public ResponseBody editCourse(Course course);

	/**
	 * 
	 * TODO 删除课堂
	 * 
	 * @author xtysummer1121@foxmail.com
	 * @date 2019年3月14日
	 * @param course
	 * @return
	 */
	public ResponseBody deleteCourse(Course course);

	/**
	 * 
	 * TODO 查找一个课堂
	 * 
	 * @author xtysummer1121@foxmail.com
	 * @date 2019年3月14日
	 * @param course
	 * @return
	 */
	public ResponseBody findOneCourse(Course course);

	/**
	 * 
	 * TODO 获得课堂列表
	 * 
	 * @author xtysummer1121@foxmail.com
	 * @date 2019年3月14日
	 * @param course
	 * @return
	 */
	public ResponseBody findCourseList(Course course);
}
