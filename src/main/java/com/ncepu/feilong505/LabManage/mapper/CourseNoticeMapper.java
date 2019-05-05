package com.ncepu.feilong505.LabManage.mapper;

import com.ncepu.feilong505.LabManage.pojo.CourseNotice;
import com.ncepu.feilong505.LabManage.pojo.CourseNoticeExample;
import com.ncepu.feilong505.LabManage.vo.CourseNoticeVO;

import java.util.List;
import org.apache.ibatis.annotations.Param;

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
    
    /**
     * 
     * TODO 获取公告详情  
     * @author xtysummer1121@foxmail.com
     * @date 2019年5月4日
     * @param id
     * @return
     */
    CourseNoticeVO selectVOByPrimaryKey(Long id);
    
    /**
     * 
     * TODO  获取公告列表
     * @author xtysummer1121@foxmail.com
     * @date 2019年5月4日
     * @param courseId
     * @return
     */
    List<CourseNoticeVO> selectNoticeVOList(Long courseId);

}