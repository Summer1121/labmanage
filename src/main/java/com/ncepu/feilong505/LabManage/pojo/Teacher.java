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
public class Teacher {
	public Teacher(String teacherPhone) {
		this.teacherPhone=teacherPhone;
	}

	private Long id;

	private Long teacherUserId;

	private String teacherPhone;

}