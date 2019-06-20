package com.ncepu.feilong505.LabManage.pojo;

import java.util.Date;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourseNotice {
    private Long id;

    private Long userId;

    private Long courseId;

    private String courseNoticeContent;

    private Date publishTime;

    private Date updateTime;
}