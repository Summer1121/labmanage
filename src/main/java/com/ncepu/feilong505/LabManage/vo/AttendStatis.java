package com.ncepu.feilong505.LabManage.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.NoArgsConstructor;

/**
 * TODO 用于某课堂用户签到统计
 * 
 * @author xtysummer1121@foxmail.com
 * @date 2019年4月20日
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class AttendStatis {
    private Long userId;// 用户id
    private String userName; // 用户姓名
    private Integer attendTime;// 签到次数
    private String userClass;//学生班级
    private String userNum;//学号
}
