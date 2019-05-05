package com.ncepu.feilong505.LabManage.vo;

import java.util.Date;

import com.ncepu.feilong505.LabManage.pojo.CourseNotice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO 用于显示课堂公告
 * 
 * @author xtysummer1121@foxmail.com
 * @date 2019年5月4日
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseNoticeVO {
    Long id;// 公告id
    Long userId;// 发布、修改者id
    String userName;// 发布、修改者姓名
    Date publishTime;// 发布时间
    Date updateTime;// 更新时间
    String content;// 公告内容

    public CourseNoticeVO(CourseNotice notice) {
	this.id = notice.getId();
	this.userId = notice.getUserId();
	this.content = notice.getCourseNoticeContent();
	this.publishTime = notice.getPublishTime();// 发布时间
	this.updateTime = notice.getUpdateTime();// 更新时间
    }
}
