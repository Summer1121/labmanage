package com.ncepu.feilong505.LabManage.service;

import org.springframework.stereotype.Service;

import com.ncepu.feilong505.LabManage.common.ResponseBody;

/**
 * TODO 课堂公告服务 发布、修改、删除的操作者必须是课堂绑定的教师，可以在课堂系统中改变， 而公告中的用户只代表操作时的用户相同
 * 
 * @author xtysummer1121@foxmail.com
 * @date 2019年3月25日
 */
@Service
public interface CourseNoticeService {
    /**
     * 
     * TODO 发布公告（教师）
     * 
     * @author xtysummer1121@foxmail.com
     * @date 2019年5月4日
     * @param userId
     * @param courseId
     * @param content
     * @return
     */
    public ResponseBody addNotice(Long userId, Long courseId, String content);

    /**
     * 
     * TODO 删除公告（教师）
     * 
     * @author xtysummer1121@foxmail.com
     * @date 2019年5月4日
     * @param userId
     * @param courseId
     * @param noticeId
     * @return
     */
    public ResponseBody removeNotice(Long userId, Long courseId, Long noticeId);

    /**
     * 
     * TODO 修改公告
     * 
     * @author xtysummer1121@foxmail.com
     * @date 2019年5月4日
     * @param userId
     * @param courseId
     * @param noticeId
     * @param content
     * @return
     */
    public ResponseBody updateNotice(Long userId, Long courseId, Long noticeId, String content);

    /**
     * 
     * TODO 获取某公告详情
     * 
     * @author xtysummer1121@foxmail.com
     * @date 2019年5月4日
     * @return
     */
    public ResponseBody getNotice(Long noticeId);

    /**
     * 
     * TODO 获取一个课堂的公告列表
     * 
     * @author xtysummer1121@foxmail.com
     * @date 2019年5月4日
     * @param courseId
     * @return
     */
    public ResponseBody getNoticeList(Long courseId);
}
