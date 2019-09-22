package com.ncepu.feilong505.LabManage.service.impl;

import com.ncepu.feilong505.LabManage.common.ResponseBody;
import com.ncepu.feilong505.LabManage.common.SMSUtil;
import com.ncepu.feilong505.LabManage.mapper.TeacherMapper;
import com.ncepu.feilong505.LabManage.mapper.UserMapper;
import com.ncepu.feilong505.LabManage.pojo.Teacher;
import com.ncepu.feilong505.LabManage.pojo.TeacherExample;
import com.ncepu.feilong505.LabManage.pojo.User;
import com.ncepu.feilong505.LabManage.pojo.UserExample;
import com.ncepu.feilong505.LabManage.service.TeacherService;
import com.ncepu.feilong505.LabManage.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * TODO
 *
 * @author xtysummer1121@foxmail.com
 * @date 2019年3月14日
 */
@Service
public class TeacherServiceImpl implements TeacherService {
	@Autowired
	TeacherMapper teacherMapper;

	@Autowired
	UserMapper userMapper;

	@Autowired
	StringRedisTemplate stringRedisTemplate;

	public static final String bindTeacherHashCodeKey = "labmanage_bindTeacher_sms_";

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.ncepu.feilong505.LabManage.service.TeacherService#addTeacher(com.ncepu.
	 * feilong505.LabManage.pojo.Teacher)
	 */
	@Override
	public ResponseBody addTeacher(Teacher teacher) {
		ResponseBody responseBody = new ResponseBody();
		try {
			TeacherExample example = new TeacherExample();
			example.createCriteria().andTeacherPhoneEqualTo(teacher.getTeacherPhone());
			if (teacherMapper.selectByExample(example).isEmpty()) {
				if (teacherMapper.insertSelective(new Teacher(teacher.getTeacherPhone())) == 1) {
					responseBody.success("添加成功");
				} else {
					responseBody.error("添加失败");
				}
			} else {
				responseBody.error("此号码已存在");
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
	 * com.ncepu.feilong505.LabManage.service.TeacherService#removeTeacher(com.ncepu
	 * .feilong505.LabManage.pojo.Teacher)
	 */
	@Override
	public ResponseBody removeTeacher(Teacher teacher) {
		ResponseBody responseBody = new ResponseBody();
		try {
			if (teacherMapper.deleteByPrimaryKey(teacher.getId()) == 1) {
				responseBody.success();
			} else {
				responseBody.error("删除失败");
			}
		} catch (Exception e) {
			responseBody.error("发生了错误");
		}
		return responseBody;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.ncepu.feilong505.LabManage.service.TeacherService#editTeacher(com.ncepu.
	 * feilong505.LabManage.pojo.Teacher)
	 */
	@Override
	public ResponseBody editTeacher(Teacher teacher) {
		ResponseBody responseBody = new ResponseBody();
		try {
			UserExample example = new UserExample();
			if (teacherMapper.updateByPrimaryKey(teacher) == 1) {
				responseBody.success(teacher);
			} else {
				responseBody.error("修改失败");
			}
		} catch (Exception e) {
			responseBody.error("发生了错误");
		}
		return responseBody;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.ncepu.feilong505.LabManage.service.TeacherService#findOneTeacher(com.
	 * ncepu.feilong505.LabManage.pojo.Teacher)
	 */
	@Override
	public ResponseBody findOneTeacher(Teacher teacher, int flag) {
		ResponseBody responseBody = new ResponseBody();
		try {
			// 查询教师
			Teacher teacherans = teacherMapper.selectByPrimaryKey(teacher.getId());
			if (teacherans != null) {
				// 查询教师的用户信息
				User userAns = userMapper.selectByPrimaryKey(teacher.getTeacherUserId());
				responseBody.success(new UserVO(userAns, flag));
			} else {
				responseBody.error("查无此人");
			}
		} catch (Exception e) {
			responseBody.error("发生了错误");
		}

		return responseBody;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.ncepu.feilong505.LabManage.service.TeacherService#findTeacherList(com.
	 * ncepu.feilong505.LabManage.pojo.Teacher)
	 */
	@Override
	public ResponseBody findTeacherList(Teacher teacher) {
//		ResponseBody responseBody = new ResponseBody();
//		TeacherExample example = new TeacherExample();
//		example.createCriteria()
//		teacherMapper.select
		return null;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.ncepu.feilong505.LabManage.service.TeacherService#ifTeacher(com.ncepu.
	 * feilong505.LabManage.pojo.Teacher)
	 */
	@Override
	public ResponseBody ifTeacher(Long userId) {
		ResponseBody responseBody = new ResponseBody();
		try {
			TeacherExample example = new TeacherExample();
			example.createCriteria().andTeacherUserIdEqualTo(userId);
			if (!teacherMapper.selectByExample(example).isEmpty()) {
				responseBody.success("是教师");
			} else {
				responseBody.error("查无结果");
			}
		} catch (Exception e) {
			responseBody.error("发生了错误");
		}
		return responseBody;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.ncepu.feilong505.LabManage.service.TeacherService#bindTeacher(com.ncepu.
	 * feilong505.LabManage.pojo.Teacher, org.apache.tomcat.jni.User)
	 */
	@Override
	public ResponseBody bindTeacher(String teacherPhone, String code, Long userId) {
		ResponseBody responseBody = new ResponseBody();
		try {
			if (!stringRedisTemplate.hasKey(bindTeacherHashCodeKey + teacherPhone)) {
				responseBody.error("非法操作或号码错误或验证码过期");
				return responseBody;
			}
			String redisCode = stringRedisTemplate.opsForValue().get(bindTeacherHashCodeKey + teacherPhone);
			if (!redisCode.equals(code)) {
				responseBody.error("验证码错误");
			}

			TeacherExample example = new TeacherExample();
			example.createCriteria().andTeacherPhoneEqualTo(teacherPhone);
			Teacher teacherParam = new Teacher();
			teacherParam.setTeacherUserId(userId);
			if (teacherMapper.updateByExampleSelective(teacherParam, example) == 1) {
				responseBody.success("绑定成功");
			} else {
				responseBody.error("绑定失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			responseBody.error("发生了错误");
		}
		return responseBody;
	}

	@Override
	public ResponseBody getBindCode(String teacherPhone, Long userId) {
		ResponseBody responseBody = new ResponseBody();

		try {

			//查找本用户是否已经是教师
			TeacherExample teacherExample = new TeacherExample();
			if (userId != null && userId != 0) {
				teacherExample.createCriteria().andTeacherUserIdEqualTo(userId);
				if (!teacherMapper.selectByExample(teacherExample).isEmpty()) {
					responseBody.error("用户已认证为教师，请勿重复认证");
					return responseBody;
				}
			}

			teacherExample.clear();
			teacherExample.or().andTeacherPhoneEqualTo(teacherPhone);
			//验证教师表是否存在本手机号
			List<Teacher> teacherList = teacherMapper.selectByExample(teacherExample);
			if (teacherList.isEmpty()) {
				responseBody.error("请联系管理员将本手机号标记为教师");
				return responseBody;
			} else if (teacherList.get(0).getTeacherUserId() != null) {
				responseBody.error("本手机号已绑定其他用户");
				return responseBody;
			}

			//生成验证码，并存入redis
			Random random = new Random(new Date().getTime());
			String Code = String.valueOf(random.nextInt(999999));

			//发送短信
			if (SMSUtil.sendMessage(new SMSUtil.MessageParam()
					.setMobile(teacherPhone)
					.setSignId(8724)
					.setTempId(1)
					.setTempPara("\"code\":\"" + Code + "\"")) == 0) {
				responseBody.error("短信发送失败");
				return responseBody;
			}

			stringRedisTemplate.opsForValue().set(bindTeacherHashCodeKey + teacherPhone, Code);
			stringRedisTemplate.expire(bindTeacherHashCodeKey + teacherPhone, 5, TimeUnit.MINUTES);

			responseBody.success("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			responseBody.error();
		}
		return responseBody;
	}

}
