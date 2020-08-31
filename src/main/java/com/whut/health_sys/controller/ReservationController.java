package com.whut.health_sys.controller;

import com.whut.health_sys.config.CodeConfig;
import com.whut.health_sys.controller.viewobject.ReservationVO;
import com.whut.health_sys.dataobject.ReservationDO;
import com.whut.health_sys.dataobject.UserDO;
import com.whut.health_sys.response.CommonReturnType;
import com.whut.health_sys.service.ClinicService;
import com.whut.health_sys.service.ReservationService;
import com.whut.health_sys.service.UserService;
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
    private ReservationService reservationService;

    @Autowired
    private ClinicService clinicService;

    @Autowired
    private UserService userService;

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

    @GetMapping("/info")
    public CommonReturnType getInfo(@RequestParam(value = "uid")String Suid,
                                    @RequestParam(value = "rid")String Srid)
    {
        try
        {
            UserDO userDO = userService.selectByUserId(Integer.parseInt(Suid));
            ReservationDO res = reservationService.searchRes(Integer.parseInt(Srid));


            if(res==null)
            {
                return CommonReturnType.create(null, "no_info_acquired");
            }

            if (userDO.getUid() != res.getUid())
            {
                return CommonReturnType.create(null, "user_not_match");
            }

            return CommonReturnType.create(ConvertUtil.convertToReservationVO(res));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return CommonReturnType.create(null, "unknown_error");
        }
    }

    @GetMapping("/mine")
    public CommonReturnType getInfo(@RequestParam(value = "uid")String Suid)
    {
        try
        {
            UserDO userDO = userService.selectByUserId(Integer.parseInt(Suid));

            return CommonReturnType.create(reservationService.searchResByUser(userDO));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return CommonReturnType.create(null, "unknown_error");
        }
    }

    @PostMapping
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
            reservationDO.setMsg(msg);

            ReservationDO res = reservationService.addRes(reservationDO);
            return CommonReturnType.create(ConvertUtil.convertToReservationVO(res));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return CommonReturnType.create(null, "unknown_error");
        }
    }

    @PutMapping
    public CommonReturnType editRes(@RequestParam(value = "uid")String Suid,
                                    @RequestParam(value = "rid")String Srid,
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

            ReservationDO reservationDO = reservationService.searchRes(Integer.parseInt(Srid));
            reservationDO.setCid(Integer.parseInt(Scid));
            reservationDO.setTime(Integer.parseInt(Stime));
            reservationDO.setDept(Integer.parseInt(Sdept));
            reservationDO.setMsg(msg);
            reservationDO.setStatus(CodeConfig.STATUS_RES_PROCESSED);

            ReservationDO res = reservationService.modifyRes(reservationDO);
            return CommonReturnType.create(ConvertUtil.convertToReservationVO(res));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return CommonReturnType.create(null, "unknown_error");
        }
    }

    @DeleteMapping
    public CommonReturnType deleteRes(@RequestParam(value = "uid")String Suid,
                                    @RequestParam(value = "rid")String Srid)
    {
        try
        {
            UserDO userDO = userService.selectByUserId(Integer.parseInt(Suid));
            ReservationDO res = reservationService.searchRes(Integer.parseInt(Srid));


            if (userDO.getUid() != res.getUid())
            {
                return CommonReturnType.create(null, "user_not_match");
            }

            ReservationDO returnRes = reservationService.cancelRes(res);
            return CommonReturnType.create(ConvertUtil.convertToReservationVO(returnRes));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return CommonReturnType.create(null, "unknown_error");
        }
    }

    //admin
    @PutMapping("/operate")
    public CommonReturnType agree(@RequestParam(value = "uid")String Suid,
                                  @RequestParam(value = "rid")String Srid,
                                  @RequestParam(value = "msg",required = false,defaultValue = "预约成功:")String msg,
                                  @RequestParam(value = "operation")String operation)
    {
        try
        {
            UserDO userDO = userService.selectByUserId(Integer.parseInt(Suid));
            if(userDO.getStatus()!=CodeConfig.STATUS_USER_ADMIN&&userDO.getStatus()!=CodeConfig.STATUS_USER_DOC)
            {
                return CommonReturnType.create(null,"unauthorized_user");
            }

            ReservationDO res = reservationService.searchRes(Integer.parseInt(Srid));

            if("agree".equals(operation))
            {
                res.setStatus(CodeConfig.STATUS_RES_ACCESSED);
                res.setMsg(msg+res.getMsg());
            }
            else if("reject".equals(operation))
            {
                res.setStatus(CodeConfig.STATUS_RES_DENIED);
                res.setMsg(msg);
            }
            else if("finish".equals(operation))
            {
                res.setStatus(CodeConfig.STATUS_RES_DENIED);
                res.setMsg("已完成");
            }

            ReservationDO returnRes = reservationService.modifyRes(res);
            return CommonReturnType.create(ConvertUtil.convertToReservationVO(returnRes));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return CommonReturnType.create(null, "unknown_error");
        }
    }
}
