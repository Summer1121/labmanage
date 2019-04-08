package com.ncepu.feilong505.LabManage.vo;

import com.ncepu.feilong505.LabManage.pojo.User;

import lombok.Data;

/**
 * TODO user信息的前端展示信息
 * 
 * @author xtysummer1121@foxmail.com
 * @date 2019年3月18日
 */
@Data
public class UserVO {
    Long id;
    String userName;
    String userClass;
    String userMajor;
    String userPhone;
    String userWxid;
    String userNum;
    String userDetail;

    /**
     * 
     * @param user 用户对象（数据来源）
     * @param flag 获取用户信息级别标志 值越大信息越多 0~n
     */
    public UserVO(User user, int flag) {
	// 简单信息
	if (flag >= 0) {
	    this.id = user.getId();
	    this.userName = user.getUserName();
	}
	// 个人详细信息
	if (flag >= 1) {
	    this.userDetail = user.getUserDetail();
	}
	// 校园身份信息
	if (flag >= 2) {
	    this.userClass = user.getUserClass();
	    this.userMajor = user.getUserMajor();
	    this.userNum = user.getUserNum();
	}

	// 用户隐私信息
	if (flag >= 3) {
	    this.userPhone = user.getUserPhone();
	}

    }
}
