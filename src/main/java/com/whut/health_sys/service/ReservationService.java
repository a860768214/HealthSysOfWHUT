package com.whut.health_sys.service;

import com.whut.health_sys.controller.viewobject.ReservationVO;
import com.whut.health_sys.dataobject.ReservationDO;
import com.whut.health_sys.dataobject.UserDO;

import java.util.List;

public interface ReservationService
{
    List<ReservationDO> listRes();

    ReservationDO addRes(ReservationDO reservationDO);

    ReservationDO modifyRes(ReservationDO reservationDO);

    ReservationDO cancelRes(ReservationDO reservationDO);

    ReservationDO searchRes(Integer rid);

    List<ReservationVO> searchResByUser(UserDO userDO);
}
