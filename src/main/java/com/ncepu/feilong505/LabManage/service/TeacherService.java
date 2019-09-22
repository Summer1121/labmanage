package com.ncepu.feilong505.LabManage.service;

import com.ncepu.feilong505.LabManage.common.ResponseBody;
import com.ncepu.feilong505.LabManage.pojo.Teacher;
import org.springframework.stereotype.Service;

/**
 * TODO
 *
 * @author xtysummer1121@foxmail.com
 * @date 2019年3月12日
 */
@Service
public interface TeacherService {
	/**
	 * TODO 添加教师(管理员功能)
	 *
	 * @param teacher
	 * @return
	 * @author xtysummer1121@foxmail.com
	 * @date 2019年3月14日
	 */
	public ResponseBody addTeacher(Teacher teacher);

	/**
	 * TODO 删除教师(管理员功能)
	 *
	 * @param teacher
	 * @return
	 * @author xtysummer1121@foxmail.com
	 * @date 2019年3月14日
	 */
	public ResponseBody removeTeacher(Teacher teacher);

	/**
	 * TODO 修改教师(管理员功能)
	 *
	 * @param teacher
	 * @return
	 * @author xtysummer1121@foxmail.com
	 * @date 2019年3月14日
	 */
	public ResponseBody editTeacher(Teacher teacher);

	/**
	 * TODO 将用户与教师身份绑定
	 *
	 * @param teacherPhone
	 * @param userId
	 * @return
	 * @author xtysummer1121@foxmail.com
	 * @date 2019年4月3日
	 */
	public ResponseBody bindTeacher(String teacherPhone, String code, Long userId);


	/**
     * TODO 获取教师绑定用的验证码，并向教师发送
     *
     * @className TeacherService
     * @author xtysummer1121@foxmail.com
     * @methodName getbindCode
     * @date 2019-09-14
     * @param teacherPhone
     * @param userId
     * @return com.ncepu.feilong505.LabManage.common.ResponseBody
     */
    public ResponseBody getBindCode(String teacherPhone,Long userId);

    /**
	 * TODO 查找一个教师
	 *
	 * @param teacher
	 * @param flag    用户信息级别
	 * @return
	 * @author xtysummer1121@foxmail.com
	 * @date 2019年3月14日
	 */
	public ResponseBody findOneTeacher(Teacher teacher, int flag);

	/**
	 * TODO 获得教师列表
	 *
	 * @param teacher
	 * @return
	 * @author xtysummer1121@foxmail.com
	 * @date 2019年3月14日
	 */
	public ResponseBody findTeacherList(Teacher teacher);

	/**
	 * TODO 验证用户是否是教师
	 *
	 * @param userID
	 * @return
	 * @author xtysummer1121@foxmail.com
	 * @date 2019年4月3日
	 */
	public ResponseBody ifTeacher(Long userId);


}
