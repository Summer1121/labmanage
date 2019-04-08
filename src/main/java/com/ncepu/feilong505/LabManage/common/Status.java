package com.ncepu.feilong505.LabManage.common;

import lombok.Getter;

/**
 * TODO 封装状态码和状态说明的枚举类型
 * 
 * @author xtysummer1121@foxmail.com
 * @date 2019年3月13日
 */
@Getter
public enum Status {
	SUCCESS(200), WARN(400), ERROR(500);

	private int state;

	Status(int state) {
        this.state = state;
    }
}
