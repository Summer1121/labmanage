package com.ncepu.feilong505.LabManage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@MapperScan("com.ncepu.feilong505.LabManage.mapper")
@EnableCaching
public class LabManageApplication {

    public static void main(String[] args) {
	SpringApplication.run(LabManageApplication.class, args);
    }

}
