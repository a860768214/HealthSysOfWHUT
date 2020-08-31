package com.whut.health_sys.service.impl;

import com.whut.health_sys.controller.viewobject.ReservationVO;
import com.whut.health_sys.dao.ReservationDOMapper;
import com.whut.health_sys.dataobject.ReservationDO;
import com.whut.health_sys.dataobject.UserDO;
import com.whut.health_sys.service.ReservationService;
import com.whut.health_sys.utils.ConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        if(reservationDO==null)
        {
            return null;
        }

        reservationDOMapper.insertSelective(reservationDO);
        System.out.println(reservationDO.getRid());
        return reservationDO;
    }

    @Override
    public ReservationDO modifyRes(ReservationDO reservationDO)
    {
        if(reservationDO==null)
        {
            return null;
        }

        reservationDOMapper.updateByPrimaryKeySelective(reservationDO);
        return reservationDO;
    }

    @Override
    public ReservationDO cancelRes(ReservationDO reservationDO)
    {
        if(reservationDO==null)
        {
            return null;
        }

        reservationDOMapper.deleteByPrimaryKey(reservationDO.getRid());
        return reservationDO;
    }

    @Override
    public ReservationDO searchRes(Integer rid)
    {
        return reservationDOMapper.selectByPrimaryKey(rid);
    }

    @Override
    public List<ReservationVO> searchResByUser(UserDO userDO)
    {
        if(userDO==null)
        {
            return null;
        }

        List<ReservationVO> reservationVOS=new ArrayList<>();
        List<ReservationDO> reservationDOS = reservationDOMapper.searchResByUser(userDO.getUid());
        for(ReservationDO t:reservationDOS)
        {
            reservationVOS.add(ConvertUtil.convertToReservationVO(t));
        }

        return reservationVOS;
    }
}
