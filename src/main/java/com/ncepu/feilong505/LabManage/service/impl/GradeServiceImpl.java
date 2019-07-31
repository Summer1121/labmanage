package com.ncepu.feilong505.LabManage.service.impl;

import com.ncepu.feilong505.LabManage.common.ResponseBody;
import com.ncepu.feilong505.LabManage.common.TimeFormat;
import com.ncepu.feilong505.LabManage.controller.GradeController.GradeBean;
import com.ncepu.feilong505.LabManage.mapper.CourseMapper;
import com.ncepu.feilong505.LabManage.mapper.CourseUserMapper;
import com.ncepu.feilong505.LabManage.mapper.GradeMapper;
import com.ncepu.feilong505.LabManage.pojo.CourseUser;
import com.ncepu.feilong505.LabManage.pojo.Grade;
import com.ncepu.feilong505.LabManage.service.GradeService;
import com.ncepu.feilong505.LabManage.vo.GradeVO;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * TODO
 *
 * @author xtysummer1121@foxmail.com
 * @date 2019年5月9日
 */
@Service
public class GradeServiceImpl implements GradeService {

	@Autowired
	GradeMapper gradeMapper;

	@Autowired
	CourseUserMapper courseUserMapper;

	@Autowired
	CourseMapper courseMapper;

	/**
	 * TODO 判断是否是本课堂教师
	 *
	 * @param teacherUserId
	 * @param courseId
	 * @return
	 * @author xtysummer1121@foxmail.com
	 * @date 2019年6月17日
	 */
	private boolean ifTheTeacher(Long teacherUserId, Long courseId) {
		try {
			if (teacherUserId == courseMapper.selectByPrimaryKey(courseId).getCourseTeacherId())
				return true;
		} catch (Exception e) {
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.ncepu.feilong505.LabManage.service.GradeService#addUserGrade(java.lang.
	 * Long, java.lang.Long, java.lang.Double)
	 */
	@Override
	public ResponseBody addUserGrade(Long userId, Long courseId, Double score, Long teacherId) {
		ResponseBody responseBody = new ResponseBody();
		try {
			if (ifTheTeacher(teacherId, courseId) == false) {
				responseBody.error("身份与本课堂教师不匹配");
				return responseBody;
			}

			CourseUser courseUser = courseUserMapper.selectOneCourseUserByUserIdAndCourseId(userId, courseId);

			// 用户与课堂未绑定
			if (courseUser == null) {
				responseBody.error("用户未绑定本课堂");
				return responseBody;
			}
			// 用户成绩已存在
			if (gradeMapper.selectGradeByCourseUser(courseUser.getId()) != null) {
				responseBody.error("成绩已存在");
				return responseBody;
			}
			// 新建成绩记录
			Grade grade = new Grade();
			grade.setCourseUserId(courseUser.getId()).setScore(score).setUpdateTime(new Date());
			if (gradeMapper.insertSelective(grade) == 1) {
				responseBody.success("添加成绩成功");
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
	 * com.ncepu.feilong505.LabManage.service.GradeService#addGroupGrade(java.lang.
	 * Integer, java.lang.Long, java.lang.Double)
	 */
	@Override
	public ResponseBody addGroupGrade(Integer groupId, Long courseId, Double score, Long teacherId) {
		ResponseBody responseBody = new ResponseBody();
		int count = 0;
		try {
			if (ifTheTeacher(teacherId, courseId) == false) {
				responseBody.error("身份与本课堂教师不匹配");
				return responseBody;
			}

			List<Long> courseUsers = courseUserMapper.selectIdWithGroup(groupId);
			if (courseUsers.isEmpty()) {
				responseBody.error("没有找到相应用户");
				return responseBody;
			}
			Grade grade = new Grade();
			grade.setUpdateTime(new Date());

			for (Long cu : courseUsers) {
				// 如果已经存在，跳过
				if (gradeMapper.selectGradeByCourseUser(cu) != null) {
					continue;
				} else {
					// 否则新增记录
					grade.setScore(score).setCourseUserId(cu);
					count += gradeMapper.insertSelective(grade);
				}
			}
			responseBody.success();
		} catch (Exception e) {
			responseBody.error();
			e.printStackTrace();
		}
		responseBody.setData("处理了" + count + "条成绩");
		return responseBody;

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.ncepu.feilong505.LabManage.service.GradeService#addGradeByList(java.util.
	 * List)
	 */
	@Override
	public ResponseBody addGradeByList(List<GradeBean> grades, Long teacherId) {
		ResponseBody responseBody = new ResponseBody();
		try {
			int count = 0;
			Grade grade = new Grade().setUpdateTime(new Date());
			for (GradeBean gb : grades) {
				// 如果不是本课堂教师，继续
				if (ifTheTeacher(teacherId, gb.getCourseId()) == false) {
					continue;
				}

				// 判断是否存在本用户与课堂绑定
				CourseUser cu = courseUserMapper.selectOneCourseUserByUserIdAndCourseId(gb.getUserId(),
						gb.getCourseId());
				if (cu == null) {
					continue;
				}

				Grade temp = gradeMapper.selectGradeByCourseUser(cu.getId());
				// 如果不存在本成绩，添加
				if (temp == null) {
					grade.setCourseUserId(cu.getId()).setScore(gb.getScore()).setUpdateTime(new Date());
					count += gradeMapper.insertSelective(grade);
				}
				// 存在本课堂，修改
				else {
					temp.setCourseUserId(cu.getId()).setScore(gb.getScore());
					count += gradeMapper.updateByPrimaryKeySelective(temp);
				}
			}
			responseBody.success("处理了" + count + "条成绩");

		} catch (Exception e) {
			responseBody.error();
			e.printStackTrace();
		}
		return responseBody;
	}

	@Override
	public ResponseBody updateUserGrade(Long userId, Long courseId, Double score, Long teacherId) {
		ResponseBody responseBody = new ResponseBody();
		try {
			if (!ifTheTeacher(teacherId, courseId)) {
				responseBody.error("身份与本课堂教师不匹配");
				return responseBody;
			}
			CourseUser courseUser = courseUserMapper.selectOneCourseUserByUserIdAndCourseId(userId, courseId);
			// 用户与课堂未绑定
			if (courseUser == null) {
				responseBody.error("用户未绑定本课堂");
				return responseBody;
			}
			Grade temp = gradeMapper.selectGradeByCourseUser(courseUser.getId());
			// 用户成绩不存在
			if (temp == null) {
				responseBody.error("成绩不存在");
				return responseBody;
			}
			// 用户成绩已存在
			temp.setScore(score).setUpdateTime(new Date());
			if (gradeMapper.updateByPrimaryKey(temp.setScore(score).setUpdateTime(new Date())) == 1)
				responseBody.success("修改成功");
		} catch (Exception e) {
			responseBody.error();
			e.printStackTrace();
		}
		return responseBody;
	}

	@Override
	public ResponseBody downloadUserGrade(Long courseId, HttpServletResponse response) {
		ResponseBody responseBody = new ResponseBody();
		HSSFWorkbook workbook = null;
		String fileName = null;
		try {
			List<GradeVO> grades = gradeMapper.selectGradeByCourse(courseId);
			//如果为空，表示课堂没有学生
			if (grades.isEmpty()) {
				responseBody.error("未查询到课堂用户信息");
				return responseBody;
			}

			workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet(courseId + "课堂成绩单");
			Date now = new Date();
			fileName = "grade-" + TimeFormat.formateWithoutSpace(now) + ".xls";

			// 设置表头
			List<String> headers = new ArrayList<>();
			headers.add("学号");
			headers.add("班级");
			headers.add("姓名");
			headers.add("分数");
			// 在excel表中添加表头
			HSSFRow row = sheet.createRow(0);
			for (int i = 0; i < headers.size(); i++) {
				HSSFCell cell = row.createCell(i);
				HSSFRichTextString text = new HSSFRichTextString(headers.get(i));
				cell.setCellValue(text);
			}
			int rowNum = 1;
			// 在表中存放查询到的数据放入对应的列
			for (GradeVO e : grades) {
				HSSFRow row1 = sheet.createRow(rowNum);
				row1.createCell(0).setCellValue(e.getUserNum());
				row1.createCell(1).setCellValue(e.getUserClass());
				row1.createCell(2).setCellValue(e.getUserName());
				row1.createCell(3).setCellValue(e.getScore());
				rowNum++;
			}
		} catch (Exception e) {
			responseBody.error();
			e.printStackTrace();
		}
		try {
			response.setContentType("application/octet-stream");
			response.setHeader("Content-disposition", "attachment;filename=" + fileName);
			response.flushBuffer();
			workbook.write(response.getOutputStream());
			responseBody.success();
		} catch (IOException e) {
			responseBody.error();
			e.printStackTrace();
		}
		return responseBody;
	}

	@Override
	public ResponseBody getUserGrade(Long courseId) {
		ResponseBody responseBody = new ResponseBody();
		try {
			List<GradeVO> grades = gradeMapper.selectGradeByCourse(courseId);
			//如果为空，表示课堂没有学生
			if (grades.isEmpty()) {
				responseBody.error("未查询到课堂用户信息");
				return responseBody;
			}
			responseBody.success(grades);
		} catch (Exception e) {
			e.printStackTrace();
			responseBody.error();
		}
		return responseBody;
	}
}
