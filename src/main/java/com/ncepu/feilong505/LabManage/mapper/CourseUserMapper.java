package com.ncepu.feilong505.LabManage.mapper;

import com.ncepu.feilong505.LabManage.pojo.CourseUser;
import com.ncepu.feilong505.LabManage.pojo.CourseUserExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CourseUserMapper {
    long countByExample(CourseUserExample example);

    int deleteByExample(CourseUserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CourseUser record);

    int insertSelective(CourseUser record);

    List<CourseUser> selectByExample(CourseUserExample example);

    CourseUser selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CourseUser record, @Param("example") CourseUserExample example);

    int updateByExample(@Param("record") CourseUser record, @Param("example") CourseUserExample example);

    int updateByPrimaryKeySelective(CourseUser record);

    int updateByPrimaryKey(CourseUser record);

    @Select("select * from course_user where user_id = #{userId} and course_id = #{courseId} limit 1")
    CourseUser selectOneCourseUserByUserIdAndCourseId(Long userId, Long courseId);

    /**
     * 
     * TODO 获取某课堂最大组号
     * 
     * @author xtysummer1121@foxmail.com
     * @date 2019年5月23日
     * @param id 课堂id
     * @return
     */
    int selectMaxGroupId(Long courseId);

    /**
     * 
     * TODO 获取某课堂的分组列表
     * @author xtysummer1121@foxmail.com
     * @date 2019年6月11日
     * @param courseId
     * @return
     */
    @Select("select group_id from course_user where course_id = #{courseId}  group by group_id  order by group_id  ASC ")
    List<Integer> selectGroupList(Long courseId);

    /**
     * 
     * TODO 获取本课堂中的试验台号信息
     * 
     * @author xtysummer1121@foxmail.com
     * @date 2019年6月7日
     * @param courseId
     * @return
     */
    @Select("SELECT distinct position  FROM course_user WHERE course_id = #{courseId} order by position")
    List<String> selectPositionsList(Long courseId);
    
    @Select("select id from course_user where group_id = #{groupId}")
    List<Long> selectIdWithGroup(Integer groupId);
    
}