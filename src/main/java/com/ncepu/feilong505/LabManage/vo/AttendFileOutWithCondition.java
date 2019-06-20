package com.ncepu.feilong505.LabManage.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * TODO 通过条件查询获取的封装
 * 
 * @author xtysummer1121@foxmail.com
 * @date 2019年6月12日
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class AttendFileOutWithCondition extends AttendFileOut {
    Date examDate;// 实验日期
    Long courseId;// 课程序号
}
