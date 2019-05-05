package com.ncepu.feilong505.LabManage.mapper;

import com.ncepu.feilong505.LabManage.pojo.Attend;
import com.ncepu.feilong505.LabManage.pojo.AttendExample;
import com.ncepu.feilong505.LabManage.vo.AttendStatis;

import java.util.List;

import javax.annotation.sql.DataSourceDefinition;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.boot.context.properties.ConfigurationProperties;
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
    List<AttendStatis> selectAttendByCourse(Long courseId,Long id,int flag);
    
    /**
     * 
     * TODO 获取某课堂某次签到的签到人数统计  
     * @author xtysummer1121@foxmail.com
     * @date 2019年4月27日
     * @param courseId
     * @param id
     * @param flag
     * @return
     */
    int selectAttendCount(Long courseId,Long id);
    
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