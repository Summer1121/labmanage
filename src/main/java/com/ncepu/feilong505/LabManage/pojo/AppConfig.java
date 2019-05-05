package com.ncepu.feilong505.LabManage.pojo;

import java.io.Serializable;
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
public class AppConfig  implements Serializable{
    private Integer id;

    private String appId;

    private String secret;
}