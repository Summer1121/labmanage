package com.ncepu.feilong505.LabManage.service;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.ncepu.feilong505.LabManage.common.ResponseBody;
import com.ncepu.feilong505.LabManage.controller.AttendController.AttendCondition;
import com.ncepu.feilong505.LabManage.pojo.Attend;
import com.ncepu.feilong505.LabManage.pojo.CourseUser;
import com.rabbitmq.client.RpcClient.Response;

import ch.qos.logback.core.joran.conditional.Condition;

/**
 * TODO 考勤服务
 *
 * @author xtysummer1121@foxmail.com
 * @date 2019年3月25日
 */
@Service
public interface AttendService {
    /**
     * 
     * TODO 签到
     * 
     * @author xtysummer1121@foxmail.com
     * @date 2019年3月25日
     * @param userId, courseId
     * @return
     */
    public ResponseBody arrive(Long userId, Long courseId, Long id);

    /**
     * 
     * TODO 签退
     * 
     * @author xtysummer1121@foxmail.com
     * @date 2019年3月25日
     * @param attend
     * @return
     */
    public ResponseBody leave(Long userId, Long courseId, Long id);

    /**
     * 
     * TODO 查询某个课堂的某次签到情况
     * 
     * @author xtysummer1121@foxmail.com
     * @date 2019年4月20日
     * @param courseId
     * @param id
     * @param flag     0表示签到用户 1表示未签到的用户
     * @return
     */
    public ResponseBody findAttendList(Long courseId, Long id, Integer flag);

    /**
     * 
     * TODO 某班级某次签到人数统计
     * 
     * @author xtysummer1121@foxmail.com
     * @date 2019年4月27日
     * @param courseId
     * @param id
     * @return
     */
    public ResponseBody getAttendCount(Long courseId, Long id);

    /**
     * 
     * TODO 获取某个班级按次数签到列表
     * 
     * @author xtysummer1121@foxmail.com
     * @date 2019年6月11日
     * @param courseId
     * @param id
     * @return
     */
    public ResponseBody getAttendCountList(Long courseId);

    /**
     * 
     * TODO 获取某个班级的学生考勤情况统计
     * 
     * @author xtysummer1121@foxmail.com
     * @date 2019年3月25日
     * @param courseId
     * @return
     */
    public ResponseBody findListWithCourse(Long courseId);

    /**
     * 
     * TODO 获取某用户在某个班级的签到记录
     * 
     * @author xtysummer1121@foxmail.com
     * @date 2019年4月21日
     * @param userId
     * @param courseId
     * @return
     */
    public ResponseBody findListWithUser(Long userId, Long courseId);

    /**
     * 
     * TODO  生成当前签到或者签退key码
     * @author xtysummer1121@foxmail.com
     * @date 2019年6月17日
     * @param userId 本数值来源于session
     * @param courseId 
     * @param flag 0为签到，1为签退
     * @return
     */
    public ResponseBody createAttendString(Long userId, Long courseId, int flag);

    /**
     * 
     * TODO 输出某个班级的签到记录列表
     * 
     * @author xtysummer1121@foxmail.com
     * @date 2019年6月11日
     * @param courseId
     * @return
     */
    public ResponseBody fileOut(Long courseId, HttpServletResponse response);

    /**
     * 
     * TODO 条件查询签到记录
     * 
     * @author xtysummer1121@foxmail.com
     * @date 2019年6月12日
     * @param condition
     * @return
     */
    public ResponseBody findWithCondition(AttendCondition condition);

    /**
     * 
     * TODO 文件输出条件查询签到记录结果
     * 
     * @author xtysummer1121@foxmail.com
     * @date 2019年6月12日
     * @param condition
     * @return
     */
    public ResponseBody fileOutWithCondition(AttendCondition condition, HttpServletResponse response);

}
