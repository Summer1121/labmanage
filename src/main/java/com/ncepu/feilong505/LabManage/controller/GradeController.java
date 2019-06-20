package com.ncepu.feilong505.LabManage.controller;

import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.ncepu.feilong505.LabManage.service.GradeService;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * TODO 成绩前端控制器
 * 
 * @author xtysummer1121@foxmail.com
 * @date 2019年5月9日
 */
@RestController
@RequestMapping(path = "/grade", method = RequestMethod.POST)
public class GradeController {

    @Autowired
    GradeService gradeService;

    /**
     * 
     * TODO 对某个课堂的某个用户添加成绩 如果已经存在，不做修改
     * 
     * @author xtysummer1121@foxmail.com
     * @date 2019年6月13日
     * @param bean
     * @return
     */
    @RequestMapping("/user/add")
    public String addUserGrade(@RequestBody GradeBean bean, HttpServletRequest request) {
	Long teacherId = (Long) request.getSession().getAttribute("userId");
	return JSONObject.toJSONString(
		gradeService.addUserGrade(bean.getUserId(), bean.getCourseId(), bean.getScore(), teacherId));
    }

    /**
     * 
     * TODO 对某个小组统一给分 如果有人已经存在，不做修改
     * 
     * @author xtysummer1121@foxmail.com
     * @date 2019年6月13日
     * @param bean
     * @return
     */
    @RequestMapping("/group/add")
    public String addGroupGrade(@RequestBody GradeBean bean, HttpServletRequest request) {
	Long teacherId = (Long) request.getSession().getAttribute("userId");
	return JSONObject.toJSONString(
		gradeService.addGroupGrade(bean.getGroupId(), bean.getCourseId(), bean.getScore(), teacherId));
    }

    /**
     * 
     * TODO 使用列表给分，重复则修改
     * 
     * @author xtysummer1121@foxmail.com
     * @date 2019年6月13日
     * @param beans
     * @return
     */
    @RequestMapping("/list/add")
    public String addGradeByList(@RequestBody List<GradeBean> beans, HttpServletRequest request) {
	Long teacherId = (Long) request.getSession().getAttribute("userId");
	return JSONObject.toJSONString(gradeService.addGradeByList(beans, teacherId));
    }

    /**
     * 
     * TODO 修改某用户在某课堂的得分
     * 
     * @author xtysummer1121@foxmail.com
     * @date 2019年6月14日
     * @return
     */
    @RequestMapping("/update")
    public String updateGrade(@RequestBody GradeBean bean, HttpServletRequest request) {
	Long teacherId = (Long) request.getSession().getAttribute("userId");
	return JSONObject.toJSONString(
		gradeService.updateUserGrade(bean.getUserId(), bean.getCourseId(), bean.getScore(), teacherId));
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Accessors(chain = true)
    public static class GradeBean {
	Long userId;
	Long courseId;
	Integer groupId;
	Double score;
    }
}
