package com.ncepu.feilong505.LabManage.mapper;

import com.ncepu.feilong505.LabManage.pojo.Course;
import com.ncepu.feilong505.LabManage.pojo.CourseExample;
import com.ncepu.feilong505.LabManage.vo.CourseVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CourseMapper {
	long countByExample(CourseExample example);

	int deleteByExample(CourseExample example);

	int deleteByPrimaryKey(Long id);

	int insert(Course record);

	int insertSelective(Course record);

	List<Course> selectByExample(CourseExample example);

	Course selectByPrimaryKey(Long id);

	int updateByExampleSelective(@Param("record") Course record, @Param("example") CourseExample example);

	int updateByExample(@Param("record") Course record, @Param("example") CourseExample example);

	int updateByPrimaryKeySelective(Course record);

	int updateByPrimaryKey(Course record);

	/**
	 * TODO 获取某用户的课堂列表
	 *
	 * @param userId
	 * @param status
	 * @return
	 * @author xtysummer1121@foxmail.com
	 * @date 2019年4月18日
	 */
	List<CourseVO> selectCourseByUser(Long userId, Integer status);

	/**
	 * TODO 获取教师创建的课堂列表
	 *
	 * @param teacherId
	 * @return java.util.List<com.ncepu.feilong505.LabManage.vo.CourseVO>
	 * @className CourseMapper
	 * @author xtysummer1121@foxmail.com
	 * @methodName selectCourseByTeacher
	 * @date 2019-07-10
	 */
	List<CourseVO> selectCourseByTeacher(Long teacherId, Integer status);
}