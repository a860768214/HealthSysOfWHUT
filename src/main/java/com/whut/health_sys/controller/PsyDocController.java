package com.whut.health_sys.controller;

import com.whut.health_sys.config.CodeConfig;
import com.whut.health_sys.controller.viewobject.ClinicVO;
import com.whut.health_sys.controller.viewobject.PsyDocVO;
import com.whut.health_sys.dataobject.ClinicDO;
import com.whut.health_sys.dataobject.PsyDocDO;
import com.whut.health_sys.response.CommonReturnType;
import com.whut.health_sys.service.ClinicService;
import com.whut.health_sys.service.PsyDocService;
import com.whut.health_sys.service.UserService;
import com.whut.health_sys.utils.ConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("healthsys/psy")
@CrossOrigin(origins = {"*"},allowCredentials = "true",allowedHeaders = "*")
public class PsyDocController
{
    @Autowired
    private PsyDocService psyDocService;

    @Autowired
    private UserService userService;

    @GetMapping()
    public CommonReturnType listPsyDoc()
    {
        try
        {
            List<PsyDocVO> psyDocVOList=new ArrayList<>();

            List<PsyDocDO> psyDocDOS = psyDocService.listPsyDoc();

            for(PsyDocDO t:psyDocDOS)
            {
                psyDocVOList.add(ConvertUtil.convertToDocVO(t));
            }

            return CommonReturnType.create(psyDocVOList);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return CommonReturnType.create(null, "unknown_error");
        }
    }

    @GetMapping("/search")
    public CommonReturnType searchPsyDoc(@RequestParam(value = "name",required = false,defaultValue = "") String name,
                                         @RequestParam(value = "did",required = false,defaultValue = "") String did)
    {
        try
        {
            PsyDocDO psyDocDO;
            if(!"".equals(name))
            {
                psyDocDO=psyDocService.searchPsyDoc(name);
            }
            else if (!"".equals(did))
            {
                psyDocDO=psyDocService.selectPsyDoc(Integer.parseInt(did));
            }
            else
            {
                return CommonReturnType.create(null,"invalid_parameters");
            }

            return CommonReturnType.create(ConvertUtil.convertToDocVO(psyDocDO));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return CommonReturnType.create(null, "unknown_error");
        }
    }

    @PostMapping("/add")
    public CommonReturnType addPsyDoc(@RequestParam(value = "name") String name,
                                        @RequestParam(value = "gender") String gender,
                                      @RequestParam(value = "qq") String qq,
                                      @RequestParam(value = "phone") String phone,
                                      @RequestParam(value = "desc") String desc,
                                      @RequestParam(value = "age") String age,
                                      @RequestParam(value = "uid") String Suid)
    {
        try
        {
            if(userService.selectByUserId(Integer.parseInt(Suid)).getStatus()==-1)
            {
                PsyDocDO psyDocDO = new PsyDocDO();

                psyDocDO.setAge(Integer.parseInt(age));
                psyDocDO.setCretime(System.currentTimeMillis()+"");
                psyDocDO.setDesc(desc);
                psyDocDO.setName(name);
                psyDocDO.setPhone(phone);
                psyDocDO.setQq(qq);
                psyDocDO.setStatus(CodeConfig.STATUS_DOC_NORMAL);
                psyDocDO.setGender(Integer.parseInt(gender));

                PsyDocDO savePsyDoc = psyDocService.savePsyDoc(psyDocDO);
                return CommonReturnType.create(ConvertUtil.convertToDocVO(savePsyDoc));
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
    public CommonReturnType modifyPsyDoc(@RequestParam(value = "name") String name,
                                      @RequestParam(value = "gender") String gender,
                                      @RequestParam(value = "status") String status,
                                      @RequestParam(value = "did") String did,
                                      @RequestParam(value = "qq") String qq,
                                      @RequestParam(value = "phone") String phone,
                                      @RequestParam(value = "desc") String desc,
                                      @RequestParam(value = "age") String age,
                                      @RequestParam(value = "uid") String Suid)
    {
        try
        {
            if(userService.selectByUserId(Integer.parseInt(Suid)).getStatus()==-1)
            {
                PsyDocDO psyDocDO = psyDocService.selectPsyDoc(Integer.parseInt(did));

                psyDocDO.setAge(Integer.parseInt(age));
                psyDocDO.setDesc(desc);
                psyDocDO.setName(name);
                psyDocDO.setPhone(phone);
                psyDocDO.setQq(qq);
                psyDocDO.setStatus(Integer.parseInt(status));
                psyDocDO.setGender(Integer.parseInt(gender));

                PsyDocDO savePsyDoc = psyDocService.updatePsyDoc(psyDocDO);
                return CommonReturnType.create(ConvertUtil.convertToDocVO(savePsyDoc));
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
