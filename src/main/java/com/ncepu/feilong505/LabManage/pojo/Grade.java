package com.ncepu.feilong505.LabManage.pojo;

import java.util.Date;

import org.springframework.boot.autoconfigure.domain.EntityScan;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 
 * TODO  得分
 * @author xtysummer1121@foxmail.com
 * @date 2019年5月9日
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EntityScan
@Accessors(chain = true)
public class Grade {
    private Long id;

    private Long courseUserId;

    private Double score;

    private Date updateTime;

}