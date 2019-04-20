package com.ncepu.feilong505.LabManage.mapper;

import com.ncepu.feilong505.LabManage.pojo.Attend;
import com.ncepu.feilong505.LabManage.pojo.AttendExample;
import com.ncepu.feilong505.LabManage.vo.AttendStatis;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface AttendMapper {
    long countByExample(AttendExample example);

    int deleteByExample(AttendExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Attend record);

    int insertSelective(Attend record);

    List<Attend> selectByExample(AttendExample example);

    Attend selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Attend record, @Param("example") AttendExample example);

    int updateByExample(@Param("record") Attend record, @Param("example") AttendExample example);

    int updateByPrimaryKeySelective(Attend record);

    int updateByPrimaryKey(Attend record);
    
    /**
     * 
     * TODO 通过课堂ID与签到id查询签到列表  
     * @author xtysummer1121@foxmail.com
     * @date 2019年4月20日
     * @param courseId
     * @return
     */
    List<Attend> selectAttendByCourse(Long courseId,Integer id);
    
    /**
     * 
     * TODO 查询某用户在某课堂的签到列表  
     * @author xtysummer1121@foxmail.com
     * @date 2019年4月20日
     * @param userId
     * @return
     */
    List<Attend> selectAttendByUser(Long userId,Long courseId);
    
    /**
     * 
     * TODO 获取班级签到统计列表  
     * @author xtysummer1121@foxmail.com
     * @date 2019年4月20日
     * @param courseId
     * @return
     */
    List<AttendStatis> selectAttendStatis(Long courseId);
}