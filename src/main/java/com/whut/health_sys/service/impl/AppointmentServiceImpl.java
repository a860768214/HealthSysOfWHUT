package com.whut.health_sys.service.impl;

import com.whut.health_sys.dao.AppointmentDOMapper;
import com.whut.health_sys.dataobject.AppointmentDO;
import com.whut.health_sys.dataobject.UserDO;
import com.whut.health_sys.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService
{
    @Autowired
    private AppointmentDOMapper appointmentDOMapper;

    @Override
    public List<AppointmentDO> listAppo()
    {
        return appointmentDOMapper.listAppo();
    }

    @Override
    public AppointmentDO addAppo(AppointmentDO appointmentDO)
    {
        if(appointmentDO==null)
        {
            return null;
        }

        appointmentDOMapper.insertSelective(appointmentDO);

        return appointmentDO;
    }

    @Override
    public AppointmentDO modifyAppo(AppointmentDO appointmentDO)
    {
        if(appointmentDO==null)
        {
            return null;
        }

        appointmentDOMapper.updateByPrimaryKey(appointmentDO);
        return appointmentDO;
    }

    @Override
    public AppointmentDO cancelAppo(AppointmentDO appointmentDO)
    {
        if(appointmentDO==null)
        {
            return null;
        }

        appointmentDOMapper.deleteByPrimaryKey(appointmentDO.getAid());
        return appointmentDO;
    }

    @Override
    public AppointmentDO searchAppo(Integer rid)
    {
        if(rid==null)
        {
            return null;
        }

        return appointmentDOMapper.selectByPrimaryKey(rid);
    }

    @Override
    public List<AppointmentDO> searchResByUser(UserDO userDO)
    {
        if(userDO==null)
        {
            return null;
        }

        return appointmentDOMapper.searchAppoByUser(userDO.getUid());
    }
}
