package com.ncepu.feilong505.LabManage.pojo;

import java.util.Date;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * TODO 课堂信息
 * 
 * @author xtysummer1121@foxmail.com
 * @date 2019年5月23日
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EntityScan
@Accessors(chain = true)
public class GroupInfo {
    private Long id;

    private Long courseId;

    private String position;

}