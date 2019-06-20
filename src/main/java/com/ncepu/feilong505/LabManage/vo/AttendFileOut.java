package com.ncepu.feilong505.LabManage.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * TODO 用于输出文件的签到记录表
 * 
 * @author xtysummer1121@foxmail.com
 * @date 2019年6月12日
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class AttendFileOut {
    Long userId;
    String userName;
    String userClass;
    String userNum;
    Long attendId;
}
