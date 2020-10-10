package com.whut.health_sys.service;

import com.whut.health_sys.controller.viewobject.ReservationVO;
import com.whut.health_sys.dataobject.AppointmentDO;
import com.whut.health_sys.dataobject.UserDO;

import java.util.List;

public interface AppointmentService
{
    List<AppointmentDO> listAppo();

    AppointmentDO addAppo(AppointmentDO appointmentDO);

    AppointmentDO modifyAppo(AppointmentDO appointmentDO);

    AppointmentDO cancelAppo(AppointmentDO appointmentDO);

    AppointmentDO searchAppo(Integer rid);

    List<AppointmentDO> searchResByUser(UserDO userDO);
}
