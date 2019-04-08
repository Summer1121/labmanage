package com.ncepu.feilong505.LabManage.mapper;

import com.ncepu.feilong505.LabManage.pojo.Attend;
import com.ncepu.feilong505.LabManage.pojo.AttendExample;
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
}