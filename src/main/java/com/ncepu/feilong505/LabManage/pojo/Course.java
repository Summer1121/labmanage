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
public class Course {
	private Long id;

	private String courseName;

	private Long courseTeacherId;

	private Integer courseStatus;

	private Date courseBuildTime;

	private String courseDetail;

	private Integer courseIsGroup;

	private Integer courseAttendSum;
}