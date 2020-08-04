package com.whut.health_sys.controller;

import com.whut.health_sys.dataobject.ClinicDO;
import com.whut.health_sys.response.CommonReturnType;
import com.whut.health_sys.service.ClinicService;
import com.whut.health_sys.utils.ConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("healthsys/clinic")
@CrossOrigin(origins = {"*"},allowCredentials = "true",allowedHeaders = "*")
public class ClinicController
{
    @Autowired
    private ClinicService clinicService;

    @GetMapping
    public CommonReturnType searchClinic(@RequestParam(value = "name",required = false,defaultValue = "") String name,
                                         @RequestParam(value = "cid",required = false,defaultValue = "") String cid)
    {
        try
        {
            ClinicDO clinicDO;
            if(!"".equals(name))
            {
                clinicDO=clinicService.searchClinic(name);
            }
            else if (!"".equals(cid))
            {
                clinicDO=clinicService.selectClinic(Integer.parseInt(cid));
            }
            else
            {
                return CommonReturnType.create(null,"invalid_parameters");
            }

            return CommonReturnType.create(ConvertUtil.convertToClinicVO(clinicDO));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return CommonReturnType.create(null, "unknown_error");
        }
    }


}
