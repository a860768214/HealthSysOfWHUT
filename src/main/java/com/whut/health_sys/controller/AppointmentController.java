package com.whut.health_sys.controller;

import com.whut.health_sys.config.CodeConfig;
import com.whut.health_sys.controller.viewobject.AppointmentVO;
import com.whut.health_sys.controller.viewobject.ReservationVO;
import com.whut.health_sys.dataobject.AppointmentDO;
import com.whut.health_sys.dataobject.ReservationDO;
import com.whut.health_sys.dataobject.UserDO;
import com.whut.health_sys.response.CommonReturnType;
import com.whut.health_sys.service.AppointmentService;
import com.whut.health_sys.service.PsyDocService;
import com.whut.health_sys.service.UserService;
import com.whut.health_sys.service.impl.AppointmentServiceImpl;
import com.whut.health_sys.utils.ConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("healthsys/appo")
@CrossOrigin(origins = {"*"},allowCredentials = "true",allowedHeaders = "*")
public class AppointmentController
{
    @Autowired
    private UserService userService;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private PsyDocService psyDocService;

    @GetMapping
    public CommonReturnType listRes()
    {
        try
        {
            List<AppointmentVO> appointmentVOS=new ArrayList<>();

            List<AppointmentDO> appointmentDOS = appointmentService.listAppo();
            for(AppointmentDO t:appointmentDOS)
            {
                appointmentVOS.add(ConvertUtil.convertToAppointmentVO(t));
            }

            return CommonReturnType.create(appointmentVOS);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return CommonReturnType.create(null, "unknown_error");
        }
    }

    @GetMapping("/info")
    public CommonReturnType getInfo(@RequestParam(value = "uid")String Suid,
                                    @RequestParam(value = "aid")String Said)
    {
        try
        {
            UserDO userDO = userService.selectByUserId(Integer.parseInt(Suid));
            AppointmentDO appo = appointmentService.searchAppo(Integer.parseInt(Said));


            if(appo==null)
            {
                return CommonReturnType.create(null, "no_info_acquired");
            }

            if (!userDO.getUid().equals(appo.getUid()))
            {
                return CommonReturnType.create(null, "user_not_match");
            }

            return CommonReturnType.create(ConvertUtil.convertToAppointmentVO(appo));
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

            return CommonReturnType.create(appointmentService.searchResByUser(userDO));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return CommonReturnType.create(null, "unknown_error");
        }
    }

    @PostMapping
    public CommonReturnType addRes(@RequestParam(value = "uid")String Suid,
                                   @RequestParam(value = "did")String Sdid,
                                   @RequestParam(value = "time")String Stime,
                                   @RequestParam(value = "msg")String msg
                                   )
    {
        try
        {
            if(psyDocService.selectPsyDoc(Integer.parseInt(Sdid)).getStatus()==CodeConfig.STATUS_DOC_CLOSED)
            {
                return CommonReturnType.create(null, "doc_closed");
            }

            AppointmentDO appointmentDO = new AppointmentDO();
            appointmentDO.setDid(Integer.parseInt(Sdid));
            appointmentDO.setUid(Integer.parseInt(Suid));
            appointmentDO.setTime(Integer.parseInt(Stime));
            appointmentDO.setStatus(CodeConfig.STATUS_PROCESSED);
            appointmentDO.setMsg(msg);

            AppointmentDO addAppo = appointmentService.addAppo(appointmentDO);
            return CommonReturnType.create(ConvertUtil.convertToAppointmentVO(addAppo));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return CommonReturnType.create(null, "unknown_error");
        }
    }

    @PutMapping
    public CommonReturnType editRes(@RequestParam(value = "uid")String Suid,
                                    @RequestParam(value = "aid")String Said,
                                    @RequestParam(value = "did")String Sdid,
                                    @RequestParam(value = "time")String Stime,
                                    @RequestParam(value = "msg")String msg)
    {
        try
        {
            if(psyDocService.selectPsyDoc(Integer.parseInt(Sdid)).getStatus()==CodeConfig.STATUS_DOC_CLOSED)
            {
                return CommonReturnType.create(null, "doc_closed");
            }

            AppointmentDO appointmentDO = appointmentService.searchAppo(Integer.parseInt(Said));
            appointmentDO.setDid(Integer.parseInt(Sdid));
            appointmentDO.setUid(Integer.parseInt(Suid));
            appointmentDO.setTime(Integer.parseInt(Stime));
            appointmentDO.setStatus(CodeConfig.STATUS_PROCESSED);
            appointmentDO.setMsg(msg);

            AppointmentDO saveAppo = appointmentService.modifyAppo(appointmentDO);
            return CommonReturnType.create(ConvertUtil.convertToAppointmentVO(saveAppo));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return CommonReturnType.create(null, "unknown_error");
        }
    }

    @DeleteMapping
    public CommonReturnType deleteRes(@RequestParam(value = "uid")String Suid,
                                      @RequestParam(value = "aid")String Said)
    {
        try
        {
            UserDO userDO = userService.selectByUserId(Integer.parseInt(Suid));
            AppointmentDO appo = appointmentService.searchAppo(Integer.parseInt(Said));


            if (!userDO.getUid().equals(appo.getUid()))
            {
                return CommonReturnType.create(null, "user_not_match");
            }

            AppointmentDO returnAppo = appointmentService.cancelAppo(appo);
            return CommonReturnType.create(ConvertUtil.convertToAppointmentVO(returnAppo));
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
                                  @RequestParam(value = "aid")String Said,
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

            AppointmentDO appo = appointmentService.searchAppo(Integer.parseInt(Said));

            if("agree".equals(operation))
            {
                appo.setStatus(CodeConfig.STATUS_ACCESSED);
                appo.setMsg(msg+appo.getMsg());
            }
            else if("reject".equals(operation))
            {
                appo.setStatus(CodeConfig.STATUS_DENIED);
                appo.setMsg(msg);
            }
            else if("finish".equals(operation))
            {
                appo.setStatus(CodeConfig.STATUS_FINISHED);
                appo.setMsg("已完成");
            }

            AppointmentDO returnAppo = appointmentService.modifyAppo(appo);
            return CommonReturnType.create(ConvertUtil.convertToAppointmentVO(returnAppo));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return CommonReturnType.create(null, "unknown_error");
        }
    }
}
