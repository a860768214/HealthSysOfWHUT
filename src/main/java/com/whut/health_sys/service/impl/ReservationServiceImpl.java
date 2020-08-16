package com.whut.health_sys.service.impl;

import com.whut.health_sys.dao.ReservationDOMapper;
import com.whut.health_sys.dataobject.ReservationDO;
import com.whut.health_sys.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService
{
    @Autowired
    ReservationDOMapper reservationDOMapper;

    @Override
    public List<ReservationDO> listRes()
    {
        return reservationDOMapper.listRes();
    }

    @Override
    public ReservationDO addRes(ReservationDO reservationDO)
    {
        reservationDOMapper.insertSelective(reservationDO);
        System.out.println(reservationDO.getRid());
        return reservationDO;
    }

    @Override
    public ReservationDO modifyRes(ReservationDO reservationDO)
    {
        reservationDOMapper.updateByPrimaryKeySelective(reservationDO);
        return reservationDO;
    }

    @Override
    public ReservationDO cancelRes(Integer rid)
    {
        return null;
    }

    @Override
    public ReservationDO searchRes(Integer rid)
    {
        return null;
    }

    @Override
    public ReservationDO searchResByUser(Integer uid)
    {
        return null;
    }
}
