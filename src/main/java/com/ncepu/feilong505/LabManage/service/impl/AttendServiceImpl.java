package com.ncepu.feilong505.LabManage.service.impl;

import com.ncepu.feilong505.LabManage.common.ResponseBody;
import com.ncepu.feilong505.LabManage.common.TimeFormat;
import com.ncepu.feilong505.LabManage.controller.AttendController.AttendCondition;
import com.ncepu.feilong505.LabManage.mapper.AttendMapper;
import com.ncepu.feilong505.LabManage.mapper.CourseMapper;
import com.ncepu.feilong505.LabManage.mapper.CourseUserMapper;
import com.ncepu.feilong505.LabManage.pojo.*;
import com.ncepu.feilong505.LabManage.service.AttendService;
import com.ncepu.feilong505.LabManage.service.CourseUserService;
import com.ncepu.feilong505.LabManage.vo.AttendFileOut;
import com.ncepu.feilong505.LabManage.vo.AttendFileOutWithCondition;
import com.ncepu.feilong505.LabManage.vo.AttendStatis;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * TODO
 *
 * @author xtysummer1121@foxmail.com
 * @date 2019年3月25日
 */
@Service
public class AttendServiceImpl implements AttendService {

	@Autowired
	AttendMapper attendMapper;

	@Autowired
	CourseUserMapper courseUserMapper;

	@Autowired
	CourseUserService courseUserServce;

	@Autowired
	CourseMapper courseMapper;

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.ncepu.feilong505.LabManage.service.AttendServiceImpl#attend(com.ncepu.
	 * feilong505.LabManage.pojo.CourseUser)
	 */
	@Override
	public ResponseBody arrive(Long userId, Long courseId, Long id) {
		ResponseBody responseBody = new ResponseBody();
		try {
			// 本课堂和用户是否已经绑定
			CourseUser courseUser = new CourseUser();
			courseUser.setUserId(userId);
			courseUser.setCourseId(courseId);
			courseUser = (CourseUser) courseUserServce.findOneCourseUser(courseUser).getData();
			if (courseUser != null) {// 如果已经绑定
				// 查看是否已经签到
				AttendExample attendExample = new AttendExample();
				attendExample.createCriteria().andAttendCourseUserIdEqualTo(courseUser.getId()).andIdEqualTo(id);
				if (attendMapper.selectByExample(attendExample).isEmpty()) {// 如果未签到
					// 创建签到信息
					Attend record = new Attend().setId(id).setAttendCourseUserId(courseUser.getId())
							.setAttendArriveTime(new Date()).setAttendStatus(0);
					attendMapper.insert(record);
					responseBody.success("签到成功");
				} else {
					responseBody.error("请勿重复签到");
				}
			} else {
				responseBody.error("本课堂和用户未绑定");
			}
		} catch (Exception e) {
			responseBody.error("发生了错误");
			e.printStackTrace();
		}
		return responseBody;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.ncepu.feilong505.LabManage.service.AttendServiceImpl#leave(com.ncepu.
	 * feilong505.LabManage.pojo.CourseUser)
	 */
	@Override
	public ResponseBody leave(Long userId, Long courseId, Long id) {
		ResponseBody responseBody = new ResponseBody();
		try {
			// 本课堂和用户是否已经绑定
			CourseUser courseUser = new CourseUser();
			courseUser.setUserId(userId);
			courseUser.setCourseId(courseId);
			courseUser = (CourseUser) courseUserServce.findOneCourseUser(courseUser).getData();
			if (courseUser != null) {// 如果已经绑定
				// 设置要修改的签到信息的条件（具有相同签到id，具有相同绑定id、状态为未签退、3个小时内的签到）
				// 获取三个小时前的时间
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(new Date());
				calendar.add(calendar.HOUR, -3);
				Date arriveTimeLimit = calendar.getTime();

				AttendExample example = new AttendExample();
				example.createCriteria().andIdEqualTo(id).andAttendCourseUserIdEqualTo(courseUser.getId())
						.andAttendStatusEqualTo(0).andAttendArriveTimeGreaterThanOrEqualTo(arriveTimeLimit);
				// 设置修改的签退信息
				Attend record = new Attend().setAttendStatus(1).setAttendLeaveTime(new Date());
				if (attendMapper.updateByExampleSelective(record, example) >= 1)
					responseBody.success("签退成功");
				else {
					responseBody.error("没有成功签退");
				}
			} else {
				responseBody.error("本课堂和用户未绑定");
			}
		} catch (Exception e) {
			responseBody.error("发生了错误");
			e.printStackTrace();
		}
		return responseBody;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.ncepu.feilong505.LabManage.service.AttendServiceImpl#findListWithCourse(
	 * com.ncepu.feilong505.LabManage.pojo.CourseUser)
	 */
	@Override
	public ResponseBody findListWithCourse(Long courseId) {
		ResponseBody responseBody = new ResponseBody();
		try {
			List<AttendStatis> attendStatis = attendMapper.selectAttendStatis(courseId);
			if (attendStatis != null && !attendStatis.isEmpty()) {
				responseBody.success(attendStatis);
				responseBody.setTotal(attendStatis.size());
			} else {
				responseBody.error("未查询到签到信息");
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
	 * com.ncepu.feilong505.LabManage.service.AttendService#findListWithUser(java.
	 * lang.Long)
	 */
	@Override
	public ResponseBody findListWithUser(Long userId, Long courseId) {
		ResponseBody responseBody = new ResponseBody();
		try {
			CourseUserExample courseUserExample = new CourseUserExample();
			courseUserExample.createCriteria().andUserIdEqualTo(userId).andCourseIdEqualTo(courseId);
			CourseUser courseUser = courseUserMapper.selectByExample(courseUserExample).get(0);
			if (courseUser != null) {
				AttendExample attendExample = new AttendExample();
				attendExample.createCriteria().andAttendCourseUserIdEqualTo(courseUser.getId());
				List<Attend> attends = attendMapper.selectByExample(attendExample);
				if (attends != null && !attends.isEmpty()) {
					responseBody.success(attends);
				} else {
					responseBody.error("查无结果");
				}
			} else {
				responseBody.error("查无结果");
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
	 * com.ncepu.feilong505.LabManage.service.AttendService#findAttendList(java.lang
	 * .Long, java.lang.Integer)
	 */
	@Override
	public ResponseBody findAttendList(Long courseId, Long id, Integer flag) {
		ResponseBody responseBody = new ResponseBody();
		try {
			if (flag == null)
				flag = 0;
			List<AttendStatis> attendStatis = attendMapper.selectAttendByCourse(courseId, id, flag);
			if (attendStatis != null && !attendStatis.isEmpty()) {
				responseBody.success(attendStatis);
				responseBody.setTotal(attendStatis.size());
			} else {
				responseBody.error("查无结果");
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
	 * com.ncepu.feilong505.LabManage.service.AttendService#getAttendCount(java.lang
	 * .Long, java.lang.Long)
	 */
	@Override
	public ResponseBody getAttendCount(Long courseId, Long id) {
		Map<String, Integer> result = new HashMap<String, Integer>();
		ResponseBody responseBody = new ResponseBody();
		try {
			// 获取签到人数
			int arrive = attendMapper.selectAttendCount(courseId, id);

			// 获取课堂总人数
			CourseUserExample courseUserExample = new CourseUserExample();
			courseUserExample.createCriteria().andCourseIdEqualTo(courseId);
			int total = (int) courseUserMapper.countByExample(courseUserExample);
			// 获取未签到人数
			int notArrive = total - arrive;

			result.put("arrive", arrive);
			result.put("total", total);
			result.put("notArrive", notArrive);

			if (total != 0)
				responseBody.success(result);
			else {
				responseBody.error("查无结果");
			}
		} catch (Exception e) {
			responseBody.error();
			e.printStackTrace();
		}
		return responseBody;
	}

	@Override
	public ResponseBody getAttendCountList(Long courseId) {
		ResponseBody responseBody = new ResponseBody();
		// 获取最大签到次数
		int maxId = courseMapper.selectByPrimaryKey(courseId).getCourseAttendSum();
		List<Map<String, Object>> resList = new ArrayList<>();
		for (long i = maxId; i >= 1; i--) {

			// 获取签到人数
			int arrive = attendMapper.selectAttendCount(courseId, i);

			// 获取课堂总人数
			CourseUserExample courseUserExample = new CourseUserExample();
			courseUserExample.createCriteria().andCourseIdEqualTo(courseId);
			int total = (int) courseUserMapper.countByExample(courseUserExample);
			Date attendTime = attendMapper.selectAttendTime(courseId, i);
			// 获取未签到人数
			int notArrive = total - arrive;

			if (total != 0) {
				Map<String, Object> m = new HashMap<>();
				m.put("attendId", i);
				m.put("arrive", arrive);
				m.put("total", total);
				m.put("notArrive", notArrive);
				m.put("time", attendTime);
				resList.add(m);
			}
		}
		if (resList.isEmpty()) {
			responseBody.error("查无结果");
		} else {
			responseBody.success(resList);
		}
		return responseBody;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.ncepu.feilong505.LabManage.service.AttendService#createAttendString(java.
	 * lang.Long, int)
	 */
	@Override
	public ResponseBody createAttendString(Long userId, Long courseId, int flag) {
		ResponseBody responseBody = new ResponseBody();
		Course course = courseMapper.selectByPrimaryKey(courseId);
		if (course == null) {
			responseBody.error("课堂不存在");
			return responseBody;
		}
		if (course.getCourseTeacherId() != userId) {
			responseBody.error("教师与课堂不匹配");
			return responseBody;
		}

		try {
			if (flag == 0)// 签到
			{
				int id = courseMapper.selectByPrimaryKey(courseId).getCourseAttendSum() + 1;// 签到次数+1

				// 更新数据库总签到次数
				course.setCourseAttendSum(id);
				courseMapper.updateByPrimaryKeySelective(course);
				Long time = new Date().getTime();
				responseBody.success(courseId + "&" + id + "&" + time + "&" + flag);
			} else if (flag == 1)// 签退
			{
				int id = courseMapper.selectByPrimaryKey(courseId).getCourseAttendSum();
				Long time = new Date().getTime();
				responseBody.success(courseId + "&" + id + "&" + time + "&" + flag);
			}
		} catch (Exception e) {
			responseBody.error("发生了错误");
			e.printStackTrace();
		}
		return responseBody;
	}

	@Override
	public ResponseBody fileOut(Long courseId, HttpServletResponse response) {
		ResponseBody responseBody = new ResponseBody();
		HSSFWorkbook workbook = null;
		String fileName = null;

		// 获取本课堂的签到记录
		List<AttendFileOut> source = attendMapper.selectAttendCourseRecord(courseId);

		try {
			workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet(courseId + "签到记录表");

			Date now = new Date();
			fileName = "attendRecord-" + TimeFormat.formateWithoutSpace(now) + ".xls";

			int maxAttendId = courseMapper.selectByPrimaryKey(courseId).getCourseAttendSum();

			// 设置表头
			List<String> headers = new ArrayList<String>();
			headers.add("班级");
			headers.add("学号");
			headers.add("姓名");
			// 头部长度，用来计算后续内容偏移量
			int headersLength = headers.size();
			for (int i = 1; i <= maxAttendId; i++) {
				headers.add("第" + i + "次");
			}
			headers.add("签到次数");

			HSSFRow row = sheet.createRow(0);

			// 在excel表中添加表头
			for (int i = 0; i < headers.size(); i++) {
				HSSFCell cell = row.createCell(i);
				HSSFRichTextString text = new HSSFRichTextString(headers.get(i));
				cell.setCellValue(text);
			}

			// 正文从1行开始（在循环开始时+1）
			int rowNum = 0;
			long nowUserId = 0;// 当前正在处理的用户ID
			int count = 0;// 记录当前用户的签到次数
			HSSFRow row1 = null;// 本行

			// 在表中存放查询到的数据放入对应的列
			for (AttendFileOut e : source) {
				// 如果本记录为新的用户记录，写入下一行
				if (!e.getUserId().equals(nowUserId)) {
					// 一行运算结束，本行加上数量单元格
					if (row1 != null)
						row1.createCell(maxAttendId + headersLength).setCellValue(count);
					// 计数清零
					count = 0;

					nowUserId = e.getUserId();
					rowNum++;
					row1 = sheet.createRow(rowNum);
					row1.createCell(0).setCellValue(e.getUserClass());
					row1.createCell(1).setCellValue(e.getUserNum());
					row1.createCell(2).setCellValue(e.getUserName());
				}
				// 获取用户的id，在对应位置填上标记
				// 如果本用户有签到记录
				if (e.getAttendId() != null) {
					row1.createCell(e.getAttendId().intValue() + headersLength - 1).setCellValue("√");
					count++;
				}
			} // 最后一行缺少签到次数，补上
			if (row1 != null)
				row1.createCell(maxAttendId + headersLength).setCellValue(count);
		} catch (Exception e1) {
			e1.printStackTrace();
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
	public ResponseBody findWithCondition(AttendCondition condition) {
		ResponseBody responseBody = new ResponseBody();
		try {
			// 获取总数
			int count = attendMapper.selectAttendWithConditionCount(condition.getUserNum(), condition.getUserName(),
					condition.getUserClass(), condition.getCourseId(), condition.getStartTime(),
					condition.getEndTime());
			responseBody.setTotal(count);
			if (condition.getPageNum() != null)
				responseBody.setCurPage(condition.getPageNum());
			if (condition.getPageSize() != null)
				responseBody.setPageSize(condition.getPageSize());

			if (count != 0) {
				// 获取结果
				List<AttendFileOutWithCondition> res = attendMapper.selectAttendWithCondition(condition.getUserNum(),
						condition.getUserName(), condition.getUserClass(), condition.getCourseId(),
						condition.getStartTime(), condition.getEndTime(),
						(condition.getPageNum() != null && condition.getPageSize() != null)
								? (condition.getPageNum() - 1) * condition.getPageSize()
								: null,
						condition.getPageSize());
				responseBody.success(res);
			}
		} catch (Exception e) {
			responseBody.error();
			e.printStackTrace();
		}
		return responseBody;
	}

	@Override
	public ResponseBody fileOutWithCondition(AttendCondition condition, HttpServletResponse response) {
		ResponseBody responseBody = new ResponseBody();
		HSSFWorkbook workbook = null;
		String fileName = null;

		List<AttendFileOutWithCondition> res = attendMapper.selectAttendWithCondition(condition.getUserNum(),
				condition.getUserName(), condition.getUserClass(), condition.getCourseId(), condition.getStartTime(),
				condition.getEndTime(),
				(condition.getPageNum() != null && condition.getPageSize() != null)
						? (condition.getPageNum() - 1) * condition.getPageSize()
						: null,
				condition.getPageSize());

		try {
			workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet("签到记录");

			Date now = new Date();
			fileName = "attendRecord" + TimeFormat.formateWithoutSpace(now) + ".xls";

			// 设置表头
			List<String> headers = new ArrayList<String>();
			headers.add("班级");
			headers.add("学号");
			headers.add("姓名");
			headers.add("课程编号");
			headers.add("实验时间");

			HSSFRow row = sheet.createRow(0);

			// 在excel表中添加表头
			for (int i = 0; i < headers.size(); i++) {
				HSSFCell cell = row.createCell(i);
				HSSFRichTextString text = new HSSFRichTextString(headers.get(i));
				cell.setCellValue(text);
			}

			// 正文从1行开始
			int rowNum = 1;

			// 在表中存放查询到的数据放入对应的列
			for (AttendFileOutWithCondition e : res) {
				HSSFRow row1 = sheet.createRow(rowNum);
				row1.createCell(0).setCellValue(e.getUserClass());
				row1.createCell(1).setCellValue(e.getUserNum());
				row1.createCell(2).setCellValue(e.getUserName());
				row1.createCell(3).setCellValue(e.getCourseId());
				row1.createCell(4).setCellValue(TimeFormat.formate(e.getExamDate()));
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

}
