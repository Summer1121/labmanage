package com.ncepu.feilong505.LabManage.service.impl;

import org.apache.ibatis.javassist.expr.NewArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ncepu.feilong505.LabManage.common.ResponseBody;
import com.ncepu.feilong505.LabManage.mapper.TeacherMapper;
import com.ncepu.feilong505.LabManage.mapper.UserMapper;
import com.ncepu.feilong505.LabManage.pojo.Teacher;
import com.ncepu.feilong505.LabManage.pojo.TeacherExample;
import com.ncepu.feilong505.LabManage.pojo.TeacherExample.Criteria;
import com.ncepu.feilong505.LabManage.pojo.User;
import com.ncepu.feilong505.LabManage.pojo.UserExample;
import com.ncepu.feilong505.LabManage.service.TeacherService;
import com.ncepu.feilong505.LabManage.vo.UserVO;

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

    ResponseBody responseBody;

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.ncepu.feilong505.LabManage.service.TeacherService#addTeacher(com.ncepu.
     * feilong505.LabManage.pojo.Teacher)
     */
    @Override
    public ResponseBody addTeacher(Teacher teacher) {
	responseBody = new ResponseBody();
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
	responseBody = new ResponseBody();
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
	responseBody = new ResponseBody();
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
	public ResponseBody findOneTeacher(Teacher teacher,int flag) {
		responseBody = new ResponseBody();
		try {
			// 查询教师
			Teacher teacherans = teacherMapper.selectByPrimaryKey(teacher.getId());
			if (teacherans != null) {
				// 查询教师的用户信息
				User userAns = userMapper.selectByPrimaryKey(teacher.getTeacherUserId());
				responseBody.success(new UserVO(userAns,flag));
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
//		responseBody = new ResponseBody();
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
	responseBody = new ResponseBody();
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
    public ResponseBody bindTeacher(String teacherPhone, Long userId) {
	responseBody = new ResponseBody();
	try {
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

}
