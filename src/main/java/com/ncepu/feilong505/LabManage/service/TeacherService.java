package com.ncepu.feilong505.LabManage.service;

import org.springframework.stereotype.Service;

import com.ncepu.feilong505.LabManage.common.ResponseBody;
import com.ncepu.feilong505.LabManage.pojo.Teacher;
import com.ncepu.feilong505.LabManage.pojo.User;
import com.rabbitmq.client.RpcClient.Response;

/**
 * TODO
 * 
 * @author xtysummer1121@foxmail.com
 * @date 2019年3月12日
 */
@Service
public interface TeacherService {
    /**
     * 
     * TODO 添加教师(管理员功能)
     * 
     * @author xtysummer1121@foxmail.com
     * @date 2019年3月14日
     * @param teacher
     * @return
     */
    public ResponseBody addTeacher(Teacher teacher);

    /**
     * 
     * TODO 删除教师(管理员功能)
     * 
     * @author xtysummer1121@foxmail.com
     * @date 2019年3月14日
     * @param teacher
     * @return
     */
    public ResponseBody removeTeacher(Teacher teacher);

    /**
     * 
     * TODO 修改教师(管理员功能)
     * 
     * @author xtysummer1121@foxmail.com
     * @date 2019年3月14日
     * @param teacher
     * @return
     */
    public ResponseBody editTeacher(Teacher teacher);

    /**
     * 
     * TODO 将用户与教师身份绑定
     * 
     * @author xtysummer1121@foxmail.com
     * @date 2019年4月3日
     * @param teacherPhone
     * @param userId
     * @return
     */
    public ResponseBody bindTeacher(String teacherPhone, Long userId);

    /**
     * 
     * TODO 查找一个教师
     * 
     * @author xtysummer1121@foxmail.com
     * @date 2019年3月14日
     * @param teacher
     * @param flag   用户信息级别
     * @return
     */
    public ResponseBody findOneTeacher(Teacher teacher, int flag);

    /**
     * 
     * TODO 获得教师列表
     * 
     * @author xtysummer1121@foxmail.com
     * @date 2019年3月14日
     * @param teacher
     * @return
     */
    public ResponseBody findTeacherList(Teacher teacher);

    /**
     * 
     * TODO 验证用户是否是教师
     * 
     * @author xtysummer1121@foxmail.com
     * @date 2019年4月3日
     * @param userID
     * @return
     */
    public ResponseBody ifTeacher(Long userId);
}
