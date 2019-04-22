package com.ncepu.feilong505.LabManage.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.javassist.expr.NewArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ncepu.feilong505.LabManage.common.ResponseBody;
import com.ncepu.feilong505.LabManage.mapper.CourseUserMapper;
import com.ncepu.feilong505.LabManage.mapper.UserMapper;
import com.ncepu.feilong505.LabManage.pojo.CourseUser;
import com.ncepu.feilong505.LabManage.pojo.CourseUserExample;
import com.ncepu.feilong505.LabManage.pojo.User;
import com.ncepu.feilong505.LabManage.pojo.UserExample;
import com.ncepu.feilong505.LabManage.service.UserService;
import com.ncepu.feilong505.LabManage.vo.UserVO;

/**
 * TODO
 * 
 * @author xtysummer1121@foxmail.com
 * @date 2019年3月12日
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    CourseUserMapper courseUserMapper;

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.ncepu.feilong505.LabManage.service.UserService#Login(com.ncepu.feilong505
     * .LabManage.pojo.User)
     */
    @Override
    public ResponseBody WXLogin(User user, HttpServletRequest request) {
	ResponseBody responseBody = new ResponseBody();
	try {
	    UserExample example = new UserExample();
	    // 直接使用微信号登陆
	    if (user.getUserWxid() != null) {
		example.createCriteria().andUserWxidEqualTo(user.getUserWxid());
		List<User> result = userMapper.selectByExample(example);
		if (result == null || result.size() == 0) {
		    responseBody.error("账户不存在");
		} else {
		    request.getSession().setAttribute("logined", 1);
		    responseBody.success(new User(result.get(0).getId()));
		}
	    } else {
		responseBody.error("缺少参数");
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	    responseBody.error("发生了错误");
	}
	return responseBody;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.ncepu.feilong505.LabManage.service.UserService#Login(com.ncepu.feilong505
     * .LabManage.pojo.User)
     */
    @Override
    public ResponseBody Login(User user, HttpServletRequest request) {
	ResponseBody responseBody = new ResponseBody();
	try {
	    UserExample example = new UserExample();
	    if (user.getUserPhone() != null && user.getUserPassword() != null) {
		example.or().andUserPhoneEqualTo(user.getUserPhone()).andUserPasswordEqualTo(user.getUserPassword());
		List<User> result = userMapper.selectByExample(example);
		if (result == null || result.size() == 0) {
		    responseBody.error("账号或密码错误");
		} else {
		    request.getSession().setAttribute("logined", 1);
		    responseBody.success(new User(result.get(0).getId()));
		}
	    } else {
		responseBody.error("缺少参数");
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	    responseBody.error("发生了错误");
	}
	return responseBody;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ncepu.feilong505.LabManage.service.UserService#register(com.ncepu.
     * feilong505.LabManage.pojo.User)
     */
    @Override
    public ResponseBody register(User user) {
	ResponseBody responseBody = new ResponseBody();
	try {
	    UserExample example = new UserExample();
	    // 查找当前微信号是否已存在
	    example.createCriteria().andUserWxidEqualTo(user.getUserWxid());
	    List<User> users = userMapper.selectByExample(example);
	    if (users.isEmpty()) {

		// 只保存传入的值
		userMapper.insertSelective(user);
		// 返回新用户ID
		responseBody.success(new User(user.getId()));

	    } else {
		responseBody.error("该微信号已注册");
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	    responseBody.error("发生了错误");
	}
	return responseBody;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ncepu.feilong505.LabManage.service.UserService#removeUser(com.ncepu.
     * feilong505.LabManage.pojo.User)
     */
    @Override
    public ResponseBody removeUser(User user) {
	ResponseBody responseBody = new ResponseBody();
	try {
	    if (userMapper.deleteByPrimaryKey(user.getId()) == 1) {
		responseBody.success();
	    } else {
		responseBody.error("删除失败");
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	    responseBody.error("发生了错误");
	}
	return responseBody;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ncepu.feilong505.LabManage.service.UserService#editUser(com.ncepu.
     * feilong505.LabManage.pojo.User)
     */
    @Override
    public ResponseBody editUser(User user) {
	ResponseBody responseBody = new ResponseBody();
	try {
	    if (userMapper.updateByPrimaryKeySelective(user) == 1) {
		responseBody.success("修改成功");
	    } else {
		responseBody.error("修改失败");
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	    responseBody.error("发生了错误");
	}
	return responseBody;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.ncepu.feilong505.LabManage.service.UserService#findOneUser(com.ncepu.
     * feilong505.LabManage.pojo.User)
     */
    @Override
    public ResponseBody findOneUser(User user, int flag) {
	ResponseBody responseBody = new ResponseBody();
	try {
	    User usertemp = userMapper.selectByPrimaryKey(user.getId());
	    UserVO result = new UserVO(usertemp, 0);
	    if (usertemp != null) {
		responseBody.success(result);
	    } else {
		responseBody.error("查无此人");
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	    responseBody.error("发生了错误");
	}
	return responseBody;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.ncepu.feilong505.LabManage.service.UserService#FindUserWithPage(java.lang
     * .Long, java.lang.Long, java.lang.Integer)
     */
    @Override
    public ResponseBody findUserList(Long userId, Long courseId, Integer groupId, Integer flag)
	    throws InterruptedException {
	ResponseBody responseBody = new ResponseBody();
	if (flag == null)
	    flag = 0;
	if (userId != null) {
	    User user = userMapper.selectByPrimaryKey(userId);
	    if (user != null) {
		responseBody.success(new UserVO(user, flag));
	    } else {
		responseBody.error("查无此人");
	    }

	} else if (courseId != null) {
	    CourseUserExample example = new CourseUserExample();
	    example.createCriteria().andCourseIdEqualTo(courseId);
	    if (groupId != null) {
		example.createCriteria().andGroupIdEqualTo(groupId);
	    }
	    List<CourseUser> courseUsers = courseUserMapper.selectByExample(example);
	    List<UserVO> userVO = new ArrayList<UserVO>();
	    for (CourseUser courseUser : courseUsers) {
		userVO.add(new UserVO(userMapper.selectByPrimaryKey(courseUser.getUserId()), flag));
	    }
	    if (!userVO.isEmpty()) {
		responseBody.success(userVO);
	    } else {
		responseBody.error("查询无果");
	    }
	} else
	    responseBody.error("缺少参数");
	return responseBody;
    }

}
