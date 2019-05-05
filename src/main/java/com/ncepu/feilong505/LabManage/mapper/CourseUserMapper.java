package com.ncepu.feilong505.LabManage.mapper;

import com.ncepu.feilong505.LabManage.pojo.CourseUser;
import com.ncepu.feilong505.LabManage.pojo.CourseUserExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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
    
    int selectMaxGroupId(Long id);
}