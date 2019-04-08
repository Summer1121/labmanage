package com.ncepu.feilong505.LabManage.mapper;

import com.ncepu.feilong505.LabManage.pojo.CourseNotice;
import com.ncepu.feilong505.LabManage.pojo.CourseNoticeExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface CourseNoticeMapper {
    long countByExample(CourseNoticeExample example);

    int deleteByExample(CourseNoticeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CourseNotice record);

    int insertSelective(CourseNotice record);

    List<CourseNotice> selectByExample(CourseNoticeExample example);

    CourseNotice selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CourseNotice record, @Param("example") CourseNoticeExample example);

    int updateByExample(@Param("record") CourseNotice record, @Param("example") CourseNoticeExample example);

    int updateByPrimaryKeySelective(CourseNotice record);

    int updateByPrimaryKey(CourseNotice record);
}