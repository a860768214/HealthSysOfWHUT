package com.whut.health_sys;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.whut.health_sys.dao")
public class HealthSysApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(HealthSysApplication.class, args);
    }

}
