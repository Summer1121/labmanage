package com.ncepu.feilong505.LabManage.vo;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.ncepu.feilong505.LabManage.mapper.TeacherMapper;
import com.ncepu.feilong505.LabManage.mapper.UserMapper;
import com.ncepu.feilong505.LabManage.pojo.Course;
import com.ncepu.feilong505.LabManage.pojo.User;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * TODO 用于前端展示的课堂信息,其中teacherName和teacherPhone需要手动检索
 * 
 * @author xtysummer1121@foxmail.com
 * @date 2019年4月12日
 */
@Data
@Accessors(chain = true)
public class CourseVO {
    @Autowired
    UserMapper userMapper;
    private Long id;
    private String courseName;// 课堂名称
    private Long teacherUserId;// 教师userid
    private String teacherName;// 教师姓名
    private String teacherPhone;// 教师手机号
    private Integer courseStatus;// 课堂状态
    private Date courseBuildTime;// 课堂创建时间
    private String courseDetail;// 课堂详情

    public CourseVO(Course course) {
	this.id = course.getId();
	this.courseName = course.getCourseName();
	this.teacherUserId = course.getCourseTeacherId();
	this.courseStatus = course.getCourseStatus();
	this.courseBuildTime = course.getCourseBuildTime();
	this.courseDetail = course.getCourseDetail();
    }
}
