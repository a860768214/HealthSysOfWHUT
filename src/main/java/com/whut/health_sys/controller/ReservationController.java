package com.whut.health_sys.controller;

import com.whut.health_sys.config.CodeConfig;
import com.whut.health_sys.controller.viewobject.ReservationVO;
import com.whut.health_sys.dataobject.ReservationDO;
import com.whut.health_sys.response.CommonReturnType;
import com.whut.health_sys.service.ClinicService;
import com.whut.health_sys.service.ReservationService;
import com.whut.health_sys.service.impl.ClinicServiceImpl;
import com.whut.health_sys.utils.ConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("healthsys/res")
@CrossOrigin(origins = {"*"},allowCredentials = "true",allowedHeaders = "*")
public class ReservationController
{
    @Autowired
    ReservationService reservationService;

    @Autowired
    ClinicService clinicService;

    @GetMapping
    public CommonReturnType listRes()
    {
        try
        {
            List<ReservationVO> reservationVOS=new ArrayList<>();

            List<ReservationDO> reservationDOS = reservationService.listRes();
            for(ReservationDO t:reservationDOS)
            {
                reservationVOS.add(ConvertUtil.convertToReservationVO(t));
            }

            return CommonReturnType.create(reservationVOS);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return CommonReturnType.create(null, "unknown_error");
        }
    }

    @PostMapping()
    public CommonReturnType addRes(@RequestParam(value = "uid")String Suid,
                                   @RequestParam(value = "cid")String Scid,
                                   @RequestParam(value = "time")String Stime,
                                   @RequestParam(value = "msg")String msg,
                                   @RequestParam(value = "dept")String Sdept)
    {
        try
        {
            if(clinicService.selectClinic(Integer.parseInt(Scid)).getStatus()==0)
            {
                return CommonReturnType.create(null, "clinic_closed");
            }

            ReservationDO reservationDO = new ReservationDO();
            reservationDO.setCid(Integer.parseInt(Scid));
            reservationDO.setUid(Integer.parseInt(Suid));
            reservationDO.setTime(Integer.parseInt(Stime));
            reservationDO.setDept(Integer.parseInt(Sdept));
            reservationDO.setStatus(CodeConfig.STATUS_RES_PROCESSED);

            ReservationDO res = reservationService.addRes(reservationDO);
            return CommonReturnType.create(res);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return CommonReturnType.create(null, "unknown_error");
        }
    }
}
