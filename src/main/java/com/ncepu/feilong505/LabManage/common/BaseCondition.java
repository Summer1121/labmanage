package com.ncepu.feilong505.LabManage.common;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * TODO 自定义的用于条件操作的基础封装
 * 
 * @author xtysummer1121@foxmail.com
 * @date 2019年4月30日
 */
@Data
public class BaseCondition {
    // 开始时间
    @JsonFormat(pattern = TimeFormat.JsonTime)
    Date startTime;
    // 结束时间
    @JsonFormat(pattern = TimeFormat.JsonTime)
    Date endTime;
    // 页码
    Integer pageNum;
    // 页大小
    Integer pageSize;
}
