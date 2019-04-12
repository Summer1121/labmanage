package com.ncepu.feilong505.LabManage.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.ncepu.feilong505.LabManage.common.ResponseBody;
import com.ncepu.feilong505.LabManage.pojo.User;

/**
 * TODO 用户模块
 * 
 * @author xtysummer1121@foxmail.com
 * @date 2019年3月12日
 */
@Service
public interface UserService {
    /**
     * 
     * TODO 使用微信用户登录
     * 
     * @author xtysummer1121@foxmail.com
     * @date 2019年3月12日
     * @param user (包含用户微信号)
     * @return 成功返回用户ID 失败返回-1
     */
    ResponseBody WXLogin(User user, HttpServletRequest request);

    /**
     * 
     * TODO 使用手机和密码用户登录
     * 
     * @author xtysummer1121@foxmail.com
     * @date 2019年3月12日
     * @param user (包含用户id or 手机号 and 密码)
     * @return 成功返回用户ID 失败返回-1
     */
    ResponseBody Login(User user, HttpServletRequest request);

    /**
     * 
     * TODO 用户注册
     * 
     * @author xtysummer1121@foxmail.com
     * @date 2019年3月13日
     * @param user
     * @return 新用户ID
     */

    ResponseBody register(User user);

    /**
     * 
     * TODO 删除用户
     * 
     * @author xtysummer1121@foxmail.com
     * @date 2019年3月12日
     * @param user
     * @return 影响用户数
     */
    ResponseBody removeUser(User user);

    /**
     * 
     * TODO 修改用户
     * 
     * @author xtysummer1121@foxmail.com
     * @date 2019年3月12日
     * @param user
     * @return 修改用户数
     */
    ResponseBody editUser(User user);

    /**
     * 
     * TODO 查找一个用户
     * 
     * @author xtysummer1121@foxmail.com
     * @date 2019年4月8日
     * @param user
     * @param flag 用户信息级别
     * @return
     */
    ResponseBody findOneUser(User user, int flag);

    /**
     * 
     * TODO 获取用户列表
     * 
     * @author xtysummer1121@foxmail.com
     * @date 2019年4月8日
     * @param userId   有本参数时为精确查询 后两个限定条件无效
     * @param courseId 查询一个班级内的所有用户
     * @param groupId  在传递courseId的基础上 查询分组用户
     * @param flag     信息级别（0~3，级别越高，信息越多）默认为0
     * @return
     */
    ResponseBody findUserList(Long userId, Long courseId, Integer groupId, Integer flag);

    
}
