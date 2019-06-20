package com.ncepu.feilong505.LabManage.service;

import java.util.List;

import com.ncepu.feilong505.LabManage.common.ResponseBody;
import com.ncepu.feilong505.LabManage.controller.GradeController.GradeBean;

/**
 * TODO 得分服务
 * 
 * @author xtysummer1121@foxmail.com
 * @date 2019年5月9日
 */
public interface GradeService {

    /**
     * 
     * TODO 对某个课堂的某个用户添加成绩 如果已经存在，不做修改
     * 
     * @author xtysummer1121@foxmail.com
     * @date 2019年5月9日
     * @param userId
     * @param courseId
     * @param score
     * @return
     */
    public ResponseBody addUserGrade(Long userId, Long courseId, Double score, Long teacherId);

    /**
     * 
     * TODO 对某个小组统一给分 如果有人已经存在，不做修改
     * 
     * @author xtysummer1121@foxmail.com
     * @date 2019年5月9日
     * @param groupId
     * @param courseId
     * @param score
     * @return
     */
    public ResponseBody addGroupGrade(Integer groupId, Long courseId, Double score, Long teacherId);

    /**
     * 
     * TODO 使用列表给分，重复则修改
     * 
     * @author xtysummer1121@foxmail.com
     * @date 2019年5月9日
     * @param grades
     * @return
     */
    public ResponseBody addGradeByList(List<GradeBean> grades, Long teacherId);

    /**
     * 
     * TODO 修改某用户的 得分
     * 
     * @author xtysummer1121@foxmail.com
     * @date 2019年6月13日
     * @param userId
     * @param score
     * @return
     */
    public ResponseBody updateUserGrade(Long userId, Long courseId, Double score, Long teacherId);
}
