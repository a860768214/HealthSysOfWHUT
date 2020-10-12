package com.whut.health_sys.controller;

import com.whut.health_sys.controller.viewobject.DailyVO;
import com.whut.health_sys.controller.viewobject.StepVO;
import com.whut.health_sys.dao.DailyDOMapper;
import com.whut.health_sys.dataobject.DailyDO;
import com.whut.health_sys.dataobject.StepDO;
import com.whut.health_sys.dataobject.UserDO;
import com.whut.health_sys.response.CommonReturnType;
import com.whut.health_sys.service.StepService;
import com.whut.health_sys.service.UserService;
import com.whut.health_sys.utils.ConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("healthsys/daily")
@CrossOrigin(origins = {"*"},allowCredentials = "true",allowedHeaders = "*")
public class DailyController
{
    @Autowired
    private DailyDOMapper dailyDOMapper;

    @Autowired
    private UserService userService;


    @PostMapping
    public CommonReturnType save(@RequestParam("schmail")String schmail,
                                 @RequestParam("temperature")String temperature,
                                 @RequestParam("location")String location,
                                 @RequestParam("infection")String infection,
                                 @RequestParam("familyinfection")String familyinfection
                                 )
    {
        try
        {
            UserDO userDO = userService.selectByMail(schmail);
            if(userDO==null)
            {
                return CommonReturnType.create(null,"user_havent_registered");
            }


            if(check(userDO))
            {
                return CommonReturnType.create(null,"already_upload_today");
            }

            DailyDO dailyDO = new DailyDO();
            dailyDO.setUid(userDO.getUid());
            dailyDO.setRecordtime(System.currentTimeMillis()+"");
            dailyDO.setTemperature(temperature);
            dailyDO.setLocation(location);
            dailyDO.setInfection(Integer.parseInt(infection));
            dailyDO.setFamilyinfection(Integer.parseInt(familyinfection));

            dailyDOMapper.insertSelective(dailyDO);
            return CommonReturnType.create(dailyDO);
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

            List<DailyDO> dailyDOS = dailyDOMapper.selectByUser(userDO.getUid());

            if(dailyDOS.size()==0)
            {
                return CommonReturnType.create(null,"never_upload");
            }

            List<DailyVO> dailyVOS=new ArrayList<>();
            for(DailyDO t:dailyDOS)
            {
                dailyVOS.add(ConvertUtil.convertToDailyVO(t));
            }

            return CommonReturnType.create(dailyVOS);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return CommonReturnType.create(null,"unknown_error");
        }
    }

    private boolean check(UserDO userDO)
    {
        List<DailyDO> dailyDOS = dailyDOMapper.selectByUser(userDO.getUid());
        if(dailyDOS.size()==0)
        {
            return false;
        }

        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String now = dateformat.format(System.currentTimeMillis());
        String last =dateformat.format(Long.parseLong(dailyDOS.get(dailyDOS.size()-1).getRecordtime()));

        if(now.split(" ")[0].equals(last.split(" ")[0]))
        {
            return true;
        }
        return false;
    }
}
