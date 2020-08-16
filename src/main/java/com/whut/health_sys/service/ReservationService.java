package com.whut.health_sys.service;

import com.whut.health_sys.dataobject.ReservationDO;

import java.util.List;

public interface ReservationService
{
    List<ReservationDO> listRes();

    ReservationDO addRes(ReservationDO reservationDO);

    ReservationDO modifyRes(ReservationDO reservationDO);

    ReservationDO cancelRes(Integer rid);

    ReservationDO searchRes(Integer rid);

    ReservationDO searchResByUser(Integer uid);
}
