package com.ncepu.feilong505.LabManage.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * TODO 成绩系统VO
 *
 * @author xtysummer1121@foxmail.com
 * @className GradeVO
 * @date 2019-06-30
 * @return
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class GradeVO {
	Long userId;//学生用户id
	Integer groupId;//组号
	String userNum;//学号
	String userName;//学生姓名
	String userClass;//学生班级
	Long courseId;//课堂id
	Double score;//得分
}
