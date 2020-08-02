package com.whut.health_sys.controller;

import com.whut.health_sys.response.CommonReturnType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("healthsys")
@CrossOrigin(origins = {"*"},allowCredentials = "true",allowedHeaders = "*")
public class IndexController
{
    @RequestMapping("/index")
    public CommonReturnType index()
    {
        return CommonReturnType.create("HealthSystemOfWHUT");
    }
}
