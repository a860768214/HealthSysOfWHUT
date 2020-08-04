package com.whut.health_sys.service;

import com.whut.health_sys.controller.viewobject.ClinicVO;
import com.whut.health_sys.dataobject.ClinicDO;

public interface ClinicService
{
    ClinicDO searchClinic(String name);

    ClinicDO selectClinic(Integer cid);

    ClinicDO saveClinic(ClinicDO clinicDO);

    ClinicDO updateClinic(ClinicDO clinicDO);

    ClinicDO CloseClinice(ClinicDO clinicDO);
}
