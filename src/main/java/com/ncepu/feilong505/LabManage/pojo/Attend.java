package com.ncepu.feilong505.LabManage.pojo;

import java.util.Date;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@EntityScan
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Attend {
	private Long id;//用于标记某个课堂中签到序号（防止多次签到）

	private Long attendCourseUserId;

	private Date attendArriveTime;

	private Date attendLeaveTime;

	private Integer attendStatus;

}