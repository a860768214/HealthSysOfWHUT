package com.whut.health_sys.controller;

import com.whut.health_sys.controller.viewobject.ClinicVO;
import com.whut.health_sys.dataobject.ClinicDO;
import com.whut.health_sys.response.CommonReturnType;
import com.whut.health_sys.service.ClinicService;
import com.whut.health_sys.service.UserService;
import com.whut.health_sys.utils.ConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("healthsys/clinic")
@CrossOrigin(origins = {"*"},allowCredentials = "true",allowedHeaders = "*")
public class ClinicController
{
    @Autowired
    private ClinicService clinicService;

    @Autowired
    private UserService userService;

    @GetMapping()
    public CommonReturnType listClinic()
    {
        try
        {
            List<ClinicVO> clinicVOList = clinicService.listClinic();
            return CommonReturnType.create(clinicVOList);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return CommonReturnType.create(null, "unknown_error");
        }
    }

    @GetMapping("/search")
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

    @PostMapping("/add")
    public CommonReturnType addClinic(@RequestParam(value = "name") String name,
                                      @RequestParam(value = "phone") String phone,
                                      @RequestParam(value = "address") String address,
                                      @RequestParam(value = "uid") String Suid)
    {
        try
        {
            if(userService.selectByUserId(Integer.parseInt(Suid)).getStatus()==-1)
            {
                ClinicDO clinicDO = new ClinicDO();
                clinicDO.setName(name);
                clinicDO.setPhone(phone);
                clinicDO.setAddress(address);
                clinicDO.setStatus(1);
                ClinicDO saveClinic = clinicService.saveClinic(clinicDO);
                return CommonReturnType.create(ConvertUtil.convertToClinicVO(saveClinic));
            }else
            {
                return CommonReturnType.create(null,"unauthorized_user");
            }
        }catch (Exception e)
        {
            e.printStackTrace();
            return CommonReturnType.create(null, "unknown_error");
        }
    }

    @PostMapping("/modify")
    public CommonReturnType modifyClinic(@RequestParam(value = "name") String name,
                                      @RequestParam(value = "phone") String phone,
                                      @RequestParam(value = "address") String address,
                                         @RequestParam(value = "status") String status,
                                      @RequestParam(value = "uid") String Suid)
    {
        try
        {
            if(userService.selectByUserId(Integer.parseInt(Suid)).getStatus()==-1)
            {
                ClinicDO clinicDO = new ClinicDO();
                clinicDO.setName(name);
                clinicDO.setPhone(phone);
                clinicDO.setAddress(address);
                clinicDO.setStatus(Integer.parseInt(status));
                ClinicDO saveClinic = clinicService.updateClinic(clinicDO);
                return CommonReturnType.create(ConvertUtil.convertToClinicVO(saveClinic));
            }else
            {
                return CommonReturnType.create(null,"unauthorized_user");
            }
        }catch (Exception e)
        {
            e.printStackTrace();
            return CommonReturnType.create(null, "unknown_error");
        }
    }


}
