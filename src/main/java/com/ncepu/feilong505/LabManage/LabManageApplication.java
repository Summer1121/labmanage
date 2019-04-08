package com.ncepu.feilong505.LabManage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ncepu.feilong505.LabManage.mapper")
public class LabManageApplication {

	public static void main(String[] args) {
		SpringApplication.run(LabManageApplication.class, args);
	}

}
