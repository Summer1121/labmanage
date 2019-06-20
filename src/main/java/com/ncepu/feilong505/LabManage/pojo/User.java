package com.ncepu.feilong505.LabManage.pojo;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
	public User(long id) {
		this.id = id;
	}

	private Long id;//唯一主键ID

	private String userName;//用户姓名

	private String userClass;//用户班级（可选项，注册时教师不用，学生暂时可选（之后会强制学生必须））

	private String userPhone;//手机号码（唯一号码）

	private String userWxid;//微信号（唯一号码）

	private String userNum;//学号/工号（暂时是可选）

	private String userPassword;//用户密码

	private String userDetail;//用户简介（可选）

	private String userMajor;//专业（可选）

}