package com.ncepu.feilong505.LabManage.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ncepu.feilong505.LabManage.common.ResponseBody;
import com.ncepu.feilong505.LabManage.mapper.CourseMapper;
import com.ncepu.feilong505.LabManage.mapper.CourseNoticeMapper;
import com.ncepu.feilong505.LabManage.mapper.CourseUserMapper;
import com.ncepu.feilong505.LabManage.pojo.CourseNotice;
import com.ncepu.feilong505.LabManage.pojo.CourseNoticeExample;
import com.ncepu.feilong505.LabManage.service.CourseNoticeService;
import com.ncepu.feilong505.LabManage.vo.CourseNoticeVO;
import com.rabbitmq.client.AMQP.Basic.Return;

/**
 * TODO
 * 
 * @author xtysummer1121@foxmail.com
 * @date 2019年5月4日
 */
@Service
public class CourseNoticeImpl implements CourseNoticeService {

    @Autowired
    CourseMapper courseMapper;

    @Autowired
    CourseNoticeMapper courseNoticeMapper;

    /*
     * (non-Javadoc)
     * 
     * @see com.ncepu.feilong505.LabManage.service.CourseNoticeService#addNotice(
     * java. lang.Long, java.lang.Long, java.lang.String)
     */
    @Override
    public ResponseBody addNotice(Long userId, Long courseId, String content) {
	ResponseBody responseBody = new ResponseBody();
	try {
	    // 验证本用户是否是本课堂的教师
	    if (userId == courseMapper.selectByPrimaryKey(courseId).getCourseTeacherId()) {
		Date date=new Date();
		CourseNotice courseNotice = new CourseNotice().setCourseNoticeContent(content).setCourseId(courseId)
			.setPublishTime(date).setUpdateTime(date).setUserId(userId);
		if (courseNoticeMapper.insert(courseNotice) == 1) {
		    responseBody.success("发布成功");
		} else {
		    responseBody.error("发布失败");
		}
	    } else {
		responseBody.error("教师与课堂不匹配");
	    }
	} catch (Exception e) {
	    responseBody.error();
	    e.printStackTrace();
	}
	return responseBody;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ncepu.feilong505.LabManage.service.CourseNoticeService#removeNotice(
     * java. lang.Long, java.lang.Long)
     */
    @Override
    public ResponseBody removeNotice(Long userId, Long courseId, Long noticeId) {
	ResponseBody responseBody = new ResponseBody();
	try {
	    // 验证本用户是否是本课堂的教师
	    if (userId == courseMapper.selectByPrimaryKey(courseId).getCourseTeacherId()) {
		// 验证本公告是否属于本课堂
		CourseNotice courseNotice = courseNoticeMapper.selectByPrimaryKey(noticeId);
		if (courseNotice != null && courseNotice.getCourseId() == courseId) {
		    if (courseNoticeMapper.deleteByPrimaryKey(noticeId) == 1)
			responseBody.success("删除成功");
		    else {
			responseBody.error("删除失败");
		    }
		} else {
		    responseBody.error("课堂与公告不匹配");
		}
	    } else {
		responseBody.error("课堂与教师不匹配");
	    }
	} catch (Exception e) {
	    responseBody.error();
	    e.printStackTrace();
	}
	return responseBody;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.ncepu.feilong505.LabManage.service.CourseNoticeService#updateNotice(java.
     * lang.Long, java.lang.Long, java.lang.Long, java.lang.String)
     */
    @Override
    public ResponseBody updateNotice(Long userId, Long courseId, Long noticeId, String content) {
	ResponseBody responseBody = new ResponseBody();
	try {
	    // 验证本用户是否是本课堂的教师
	    if (userId == courseMapper.selectByPrimaryKey(courseId).getCourseTeacherId()) {
		// 验证本公告是否属于本课堂
		CourseNotice courseNotice = courseNoticeMapper.selectByPrimaryKey(noticeId);
		if (courseNotice != null && courseNotice.getCourseId() == courseId) {
		    if (courseNoticeMapper.updateByPrimaryKeySelective(courseNotice.setCourseNoticeContent(content)
			    .setUserId(userId).setUpdateTime(new Date())) == 1)
			responseBody.success("修改成功");
		    else {
			responseBody.error("修改失败");
		    }
		} else {
		    responseBody.error("课堂与公告不匹配");
		}
	    } else {
		responseBody.error("课堂与教师不匹配");
	    }
	} catch (Exception e) {
	    responseBody.error();
	    e.printStackTrace();
	}
	return responseBody;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.ncepu.feilong505.LabManage.service.CourseNoticeService#getNotice(java.
     * lang.Long)
     */
    @Override
    public ResponseBody getNotice(Long noticeId) {
	ResponseBody responseBody = new ResponseBody();
	try {
	    CourseNoticeVO courseNoticeVO = courseNoticeMapper.selectVOByPrimaryKey(noticeId);
	    if (courseNoticeVO != null) {
		responseBody.success(courseNoticeVO);
	    } else {
		responseBody.error("查询无果");
	    }
	} catch (Exception e) {
	    responseBody.error();
	    e.printStackTrace();
	}
	return responseBody;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.ncepu.feilong505.LabManage.service.CourseNoticeService#getNoticeList(java
     * .lang.Long)
     */
    @Override
    public ResponseBody getNoticeList(Long courseId) {
	ResponseBody responseBody = new ResponseBody();
	try {
	    List<CourseNoticeVO> courseNoticeVOs = courseNoticeMapper.selectNoticeVOList(courseId);
	    if (courseNoticeVOs != null && !courseNoticeVOs.isEmpty()) {
		responseBody.success(courseNoticeVOs);
	    } else {
		responseBody.error("查询无果");
	    }
	} catch (Exception e) {
	    responseBody.error();
	    e.printStackTrace();
	}
	return responseBody;
    }
}
