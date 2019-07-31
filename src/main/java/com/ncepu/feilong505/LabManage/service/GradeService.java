package com.ncepu.feilong505.LabManage.service;

import com.ncepu.feilong505.LabManage.common.ResponseBody;
import com.ncepu.feilong505.LabManage.controller.GradeController.GradeBean;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * TODO 得分服务
 *
 * @author xtysummer1121@foxmail.com
 * @date 2019年5月9日
 */
public interface GradeService {

	/**
	 * TODO 对某个课堂的某个用户添加成绩 如果已经存在，不做修改
	 *
	 * @param userId
	 * @param courseId
	 * @param score
	 * @return
	 * @author xtysummer1121@foxmail.com
	 * @date 2019年5月9日
	 */
	public ResponseBody addUserGrade(Long userId, Long courseId, Double score, Long teacherId);

	/**
	 * TODO 对某个小组统一给分 如果有人已经存在，不做修改
	 *
	 * @param groupId
	 * @param courseId
	 * @param score
	 * @return
	 * @author xtysummer1121@foxmail.com
	 * @date 2019年5月9日
	 */
	public ResponseBody addGroupGrade(Integer groupId, Long courseId, Double score, Long teacherId);

	/**
	 * TODO 使用列表给分，重复则修改
	 *
	 * @param grades
	 * @return
	 * @author xtysummer1121@foxmail.com
	 * @date 2019年5月9日
	 */
	public ResponseBody addGradeByList(List<GradeBean> grades, Long teacherId);

	/**
	 * TODO 修改某用户的 得分
	 *
	 * @param userId
	 * @param score
	 * @return
	 * @author xtysummer1121@foxmail.com
	 * @date 2019年6月13日
	 */
	public ResponseBody updateUserGrade(Long userId, Long courseId, Double score, Long teacherId);

	/**
	 * TODO 下载某班级按照学号排序的得分情况
	 *
	 * @param courseId 课堂id
	 * @return com.ncepu.feilong505.LabManage.common.ResponseBody
	 * @className GradeService
	 * @author xtysummer1121@foxmail.com
	 * @methodName downloadUserGrade
	 * @date 2019-06-30
	 */
	public ResponseBody downloadUserGrade(Long courseId, HttpServletResponse response);

	/**
	 * TODO 获取某班级按照学号排序的得分情况
	 *
	 * @param courseId 课堂Id
	 * @return com.ncepu.feilong505.LabManage.common.ResponseBody
	 * @className GradeService
	 * @author xtysummer1121@foxmail.com
	 * @methodName getUserGrade
	 * @date 2019-07-09
	 */
	public ResponseBody getUserGrade(Long courseId);
}
