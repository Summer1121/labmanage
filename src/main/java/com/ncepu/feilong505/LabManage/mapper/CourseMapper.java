package com.ncepu.feilong505.LabManage.mapper;

import com.ncepu.feilong505.LabManage.controller.CourseUserController.courseUserBean;
import com.ncepu.feilong505.LabManage.pojo.Course;
import com.ncepu.feilong505.LabManage.pojo.CourseExample;
import com.ncepu.feilong505.LabManage.vo.CourseVO;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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
     * 
     * TODO 获取某用户的课堂列表  
     * @author xtysummer1121@foxmail.com
     * @date 2019年4月18日
     * @param userId
     * @param status
     * @return
     */
    List<CourseVO> selectCourseByUser(Long userId,Integer status);
}