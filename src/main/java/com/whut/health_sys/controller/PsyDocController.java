package com.whut.health_sys.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("healthsys/psy")
@CrossOrigin(origins = {"*"},allowCredentials = "true",allowedHeaders = "*")
public class PsyDocController
{
}
