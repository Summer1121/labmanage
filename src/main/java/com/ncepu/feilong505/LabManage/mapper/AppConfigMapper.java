package com.ncepu.feilong505.LabManage.mapper;

import com.ncepu.feilong505.LabManage.pojo.AppConfig;
import com.ncepu.feilong505.LabManage.pojo.AppConfigExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.Cacheable;
@Mapper
public interface AppConfigMapper {
    long countByExample(AppConfigExample example);

    int deleteByExample(AppConfigExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AppConfig record);

    int insertSelective(AppConfig record);

    List<AppConfig> selectByExample(AppConfigExample example);

    AppConfig selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AppConfig record, @Param("example") AppConfigExample example);

    int updateByExample(@Param("record") AppConfig record, @Param("example") AppConfigExample example);

    int updateByPrimaryKeySelective(AppConfig record);

    int updateByPrimaryKey(AppConfig record);
}