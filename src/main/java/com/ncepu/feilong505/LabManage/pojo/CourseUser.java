package com.ncepu.feilong505.LabManage.pojo;

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
public class CourseUser {
	private Long id;

	private Long courseId;

	private Long userId;

	private Integer groupId;
}