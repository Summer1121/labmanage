package com.ncepu.feilong505.LabManage.service;

import org.springframework.stereotype.Service;

import com.ncepu.feilong505.LabManage.common.ResponseBody;
import com.ncepu.feilong505.LabManage.pojo.Course;

/**
 * TODO
 * 
 * @author xtysummer1121@foxmail.com
 * @date 2019年3月12日
 */
@Service
public interface CourseService {
    /**
     * 
     * TODO 添加课堂
     * 
     * @author xtysummer1121@foxmail.com
     * @date 2019年3月14日
     * @return
     */
    public ResponseBody addCourse(Course course);

    /**
     * 
     * TODO 修改课堂信息
     * 
     * @author xtysummer1121@foxmail.com
     * @date 2019年3月14日
     * @return
     */
    public ResponseBody editCourse(Course course);

    /**
     * 
     * TODO 删除课堂
     * 
     * @author xtysummer1121@foxmail.com
     * @date 2019年3月14日
     * @param course
     * @return
     */
    public ResponseBody deleteCourse(Long courseId);

    /**
     * 
     * TODO 查找一个课堂
     * 
     * @author xtysummer1121@foxmail.com
     * @date 2019年3月14日
     * @param course
     * @return
     */
    public ResponseBody findOneCourse(Long courseId);

    /**
     * 
     * TODO 获得课堂列表
     * 
     * @author xtysummer1121@foxmail.com
     * @date 2019年3月14日
     * @param course
     * @return
     */
    public ResponseBody findCourseList(Course course);

    /**
     * 
     * TODO 查询本课堂是否开启分组
     * 
     * @author xtysummer1121@foxmail.com
     * @date 2019年4月23日
     * @param courseId
     * @return 课堂分组状态 0为未分组，1为自行结合
     */
    public ResponseBody ifGroup(Long courseId);

    /**
     * 
     * TODO 修改课堂分组状态
     * 
     * @author xtysummer1121@foxmail.com
     * @date 2019年4月23日
     * @param courseId 课堂ID
     * @param status 课堂分组状态 0为关闭分组，1为开启分组（不更改模式），2为自行结合(默认)，其他分组方式待定
     * @return
     */
    public ResponseBody groupConfig(Long courseId, Integer status);

}
