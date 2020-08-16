package com.whut.health_sys.controller;

import com.whut.health_sys.controller.viewobject.StepVO;
import com.whut.health_sys.dataobject.StepDO;
import com.whut.health_sys.dataobject.UserDO;
import com.whut.health_sys.response.CommonReturnType;
import com.whut.health_sys.service.StepService;
import com.whut.health_sys.service.UserService;
import com.whut.health_sys.utils.ConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("healthsys/step")
@CrossOrigin(origins = {"*"},allowCredentials = "true",allowedHeaders = "*")
public class StepController
{
    @Autowired
    private StepService stepService;

    @Autowired
    private UserService userService;


    @PostMapping
    public CommonReturnType save(@RequestParam("schmail")String schmail,
                                 @RequestParam("steps")String steps)
    {
        try
        {
            UserDO userDO = userService.selectByMail(schmail);
            if(userDO==null)
            {
                return CommonReturnType.create(null,"user_havent_registered");
            }


            stepService.selectByUid(userDO.getUid());

            if(stepService.check(userDO))
            {
                return CommonReturnType.create(null,"already_upload_today");
            }

            StepDO stepDO = new StepDO();
            stepDO.setUid(userDO.getUid());
            stepDO.setSteps(steps);
            stepDO.setDate(System.currentTimeMillis()+"");

            StepDO save = stepService.insertSelective(stepDO);
            return CommonReturnType.create(save);
        }catch (Exception e)
        {
            e.printStackTrace();
            return CommonReturnType.create(null,"unknown_error");
        }
    }

    @GetMapping
    public CommonReturnType info(@RequestParam("schmail")String schmail)
    {
        try
        {
            UserDO userDO = userService.selectByMail(schmail);
            if(userDO==null)
            {
                return CommonReturnType.create(null,"user_havent_registered");
            }

            List<StepDO> stepDOS = stepService.selectByUid(userDO.getUid());

            if(stepDOS.size()==0)
            {
                return CommonReturnType.create(null,"never_upload");
            }

            StepVO stepVO = ConvertUtil.convertToStepVO(stepDOS);

            if(stepService.check(userDO))
            {
                stepVO.setTodaysUpload(true);
            }

            return CommonReturnType.create(stepVO);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return CommonReturnType.create(null,"unknown_error");
        }
    }
}
