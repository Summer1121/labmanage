package com.ncepu.feilong505.LabManage.mapper;

import com.ncepu.feilong505.LabManage.pojo.GroupInfo;
import com.ncepu.feilong505.LabManage.pojo.GroupInfoExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GroupInfoMapper {
    long countByExample(GroupInfoExample example);

    int deleteByExample(GroupInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(GroupInfo record);

    int insertSelective(GroupInfo record);

    List<GroupInfo> selectByExample(GroupInfoExample example);

    GroupInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") GroupInfo record, @Param("example") GroupInfoExample example);

    int updateByExample(@Param("record") GroupInfo record, @Param("example") GroupInfoExample example);

    int updateByPrimaryKeySelective(GroupInfo record);

    int updateByPrimaryKey(GroupInfo record);
}