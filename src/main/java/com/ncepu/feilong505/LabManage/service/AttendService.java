package com.ncepu.feilong505.LabManage.service;

import org.springframework.stereotype.Service;

import com.ncepu.feilong505.LabManage.common.ResponseBody;
import com.ncepu.feilong505.LabManage.pojo.Attend;
import com.ncepu.feilong505.LabManage.pojo.CourseUser;
import com.rabbitmq.client.RpcClient.Response;

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
     * @return
     */
    public ResponseBody findAttendList(Long courseId, Long id);

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
     * TODO 生成当前签到或者签退key码
     * 
     * @author xtysummer1121@foxmail.com
     * @date 2019年4月7日
     * @param courseId
     * @param flag     0表示签到 1表示签退
     * @return
     */
    public ResponseBody createAttendString(Long courseId, int flag);
}
