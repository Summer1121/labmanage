package com.ncepu.feilong505.LabManage.vo;

import com.ncepu.feilong505.LabManage.pojo.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO user信息的前端展示信息
 * 
 * @author xtysummer1121@foxmail.com
 * @date 2019年3月18日
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVO {
    private Long id;
    private String userName;
    private String userClass;
    private String userMajor;
    private String userPhone;
    private String userWxid;
    private String userNum;
    private String userDetail;
    private Integer groupId;

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
