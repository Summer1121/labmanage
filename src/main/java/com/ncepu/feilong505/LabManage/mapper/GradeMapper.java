package com.ncepu.feilong505.LabManage.mapper;

import com.ncepu.feilong505.LabManage.pojo.Grade;
import com.ncepu.feilong505.LabManage.pojo.GradeExample;
import com.ncepu.feilong505.LabManage.vo.GradeVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GradeMapper {
	long countByExample(GradeExample example);

	int deleteByExample(GradeExample example);

	int deleteByPrimaryKey(Long id);

	int insert(Grade record);

	int insertSelective(Grade record);

	List<Grade> selectByExample(GradeExample example);

	Grade selectByPrimaryKey(Long id);

	int updateByExampleSelective(@Param("record") Grade record, @Param("example") GradeExample example);

	int updateByExample(@Param("record") Grade record, @Param("example") GradeExample example);

	int updateByPrimaryKeySelective(Grade record);

	int updateByPrimaryKey(Grade record);

	/**
	 * TODO 获取某个学生的成绩
	 *
	 * @param courseUserId 课堂学生id
	 * @return com.ncepu.feilong505.LabManage.pojo.Grade
	 * @className GradeMapper
	 * @author xtysummer1121@foxmail.com
	 * @methodName selectGradeByCourseUser
	 * @date 2019-06-30
	 */
	@Select("select * from grade where course_user_id = #{courseUserId} limit 1")
	Grade selectGradeByCourseUser(Long courseUserId);

	/**
	 * TODO 获取某个班级的成绩
	 *
	 * @param courseId 课堂id
	 * @return com.ncepu.feilong505.LabManage.pojo.Grade
	 * @className GradeMapper
	 * @author xtysummer1121@foxmail.com
	 * @methodName selectGradeByCourse
	 * @date 2019-06-30
	 */
	List<GradeVO> selectGradeByCourse(Long courseId);
}