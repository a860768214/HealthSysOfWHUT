package com.whut.health_sys.utils;

import com.whut.health_sys.controller.viewobject.*;
import com.whut.health_sys.dataobject.*;
import org.springframework.beans.BeanUtils;

import java.util.List;

public class ConvertUtil
{

    //UserDO转UserVO
    public static UserVO convertToUserVO(UserDO userDO)
    {
        if(userDO==null)
        {
            return null;
        }

        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userDO,userVO );

        if((userDO.getGender()!=null))
        {
            switch (userDO.getGender())
            {
                case 1:
                {
                    userVO.setGender("男");
                    break;
                }
                case 2:
                {
                    userVO.setGender("女");
                    break;
                }
            }
        }

        if((userDO.getStatus()!=null))
        {
            switch (userDO.getStatus())
            {
                case 1:
                {
                    userVO.setStatus("师生");
                    break;
                }
                case 2:
                {
                    userVO.setStatus("医生");
                    break;
                }
                case 0:
                {
                    userVO.setStatus("已注销");
                    break;
                }
                case -1:
                {
                    userVO.setStatus("管理员");
                    break;
                }
            }
        }
        return userVO;
    }

    //PsyDocDO转PsyDocVO
    public static PsyDocVO convertToDocVO(PsyDocDO psyDocDO)
    {
        if(psyDocDO==null)
        {
            return null;
        }

        PsyDocVO psyDocVO = new PsyDocVO();
        BeanUtils.copyProperties(psyDocDO,psyDocVO );

        if(psyDocDO.getGender()!=null)
        {
            switch (psyDocDO.getGender())
            {
                case 1:
                {
                    psyDocVO.setGender("男");
                    break;
                }
                case 2:
                {
                    psyDocVO.setGender("女");
                    break;
                }
            }
        }

        if(psyDocDO.getStatus()==1)
        {
            psyDocVO.setStatus("营业中");
        }
        else
        {
            psyDocVO.setStatus("休息中");
        }
        return psyDocVO;
    }

    //AppointmentDO转AppointmentVO
    public static AppointmentVO convertToAppointmentVO(AppointmentDO appointmentDO)
    {
        if(appointmentDO==null)
        {
            return null;
        }

        AppointmentVO appointmentVO = new AppointmentVO();
        BeanUtils.copyProperties(appointmentDO,appointmentVO );
        switch (appointmentDO.getTime())
        {
            case 1:
            {
                appointmentVO.setTime("8:30-10:00");
                break;
            }
            case 2:
            {
                appointmentVO.setTime("10:00-11:30");
                break;
            }
            case 3:
            {
                appointmentVO.setTime("14:00-15:30");
                break;
            }
            case 4:
            {
                appointmentVO.setTime("15:30-17:00");
                break;
            }
        }

        switch (appointmentDO.getStatus())
        {
            case 1:
            {
                appointmentVO.setStatus("预约成功");
                break;
            }
            case 0:
            {
                appointmentVO.setStatus("预约失败");
                break;
            }
            case -1:
            {
                appointmentVO.setStatus("处理中");
                break;
            }
        }

        return appointmentVO;
    }

    public static ReservationVO convertToReservationVO(ReservationDO reservationDO)
    {
        if(reservationDO==null)
        {
            return null;
        }

        ReservationVO reservationVO = new ReservationVO();
        BeanUtils.copyProperties(reservationDO,reservationVO );
        switch (reservationDO.getTime())
        {
            case 1:
            {
                reservationVO.setTime("8:30-10:00");
                break;
            }
            case 2:
            {
                reservationVO.setTime("10:00-11:30");
                break;
            }
            case 3:
            {
                reservationVO.setTime("14:00-15:30");
                break;
            }
            case 4:
            {
                reservationVO.setTime("15:30-17:00");
                break;
            }
        }

        switch (reservationDO.getStatus())
        {
            case 1:
            {
                reservationVO.setStatus("预约成功");
                break;
            }
            case 0:
            {
                reservationVO.setStatus("预约失败");
                break;
            }
            case 2:
            {
                reservationVO.setStatus("处理中");
                break;
            }
            case -1:
            {
                reservationVO.setStatus("已完成");
                break;
            }
        }

        switch (reservationDO.getDept())
        {
            case 0:
            {
                reservationVO.setDept("其他科室");
                break;
            }
            case 1:
            {
                reservationVO.setDept("骨科");
                break;
            }
            case 2:
            {
                reservationVO.setDept("外科");
                break;
            }
            case 3:
            {
                reservationVO.setDept("五官科");
                break;
            }
            case 4:
            {
                reservationVO.setDept("内科");
                break;
            }
        }
        
        return reservationVO;
    }

    public static ClinicVO convertToClinicVO(ClinicDO clinicDO)
    {
        if(clinicDO==null)
        {
            return null;
        }
        ClinicVO clinicVO = new ClinicVO();
        BeanUtils.copyProperties(clinicDO, clinicVO);

        if(clinicDO.getStatus()==1)
        {
            clinicVO.setStatus("营业中");
        }
        else
        {
            clinicVO.setStatus("休息中");
        }

        return clinicVO;
    }

    public static StepVO convertToStepVO(List<StepDO> stepDOS)
    {
        if(stepDOS==null)
        {
            return null;
        }

        StepVO stepVO = new StepVO();
        for(StepDO t:stepDOS)
        {
            stepVO.getDates().add(t.getDate());
            stepVO.getSteps().add(t.getSteps());
        }
        stepVO.setUid(stepDOS.get(0).getUid());

        return stepVO;
    }

    public static DailyVO convertToDailyVO(DailyDO dailyDO)
    {
        if(dailyDO==null)
        {
            return null;
        }

        DailyVO dailyVO = new DailyVO();
        BeanUtils.copyProperties(dailyDO, dailyVO);

        switch (dailyDO.getInfection())
        {
            case 1:
            {

                break;
            }
            case 2:
            {

                break;
            }
            case 3:
            {

                break;
            }
            case 4:
            {

                break;
            }
        }

        switch (dailyDO.getFamilyinfection())
        {
            case 1:
            {

                break;
            }
            case 2:
            {

                break;
            }
            case 3:
            {

                break;
            }
            case 4:
            {

                break;
            }
        }

        return dailyVO;
    }
}
