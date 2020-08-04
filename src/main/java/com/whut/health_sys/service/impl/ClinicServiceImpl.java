package com.whut.health_sys.service.impl;

import com.whut.health_sys.dao.ClinicDOMapper;
import com.whut.health_sys.dataobject.ClinicDO;
import com.whut.health_sys.service.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClinicServiceImpl implements ClinicService
{
    @Autowired
    private ClinicDOMapper clinicDOMapper;

    @Override
    public ClinicDO searchClinic(String name)
    {
        ClinicDO clinicDO = clinicDOMapper.selectByName(name);

        return clinicDO;
    }

    @Override
    public ClinicDO selectClinic(Integer cid)
    {
        return clinicDOMapper.selectByPrimaryKey(cid);
    }

    @Override
    public ClinicDO saveClinic(ClinicDO clinicDO)
    {
        clinicDOMapper.insertSelective(clinicDO);
        return clinicDOMapper.selectByPrimaryKey(clinicDO.getCid());
    }

    @Override
    public ClinicDO updateClinic(ClinicDO clinicDO)
    {
        clinicDOMapper.updateByPrimaryKeySelective(clinicDO);
        return clinicDOMapper.selectByPrimaryKey(clinicDO.getCid());
    }

    @Override
    public ClinicDO CloseClinice(ClinicDO clinicDO)
    {
        ClinicDO selectByPrimaryKey = clinicDOMapper.selectByPrimaryKey(clinicDO.getCid());
        selectByPrimaryKey.setStatus(0);

        clinicDOMapper.updateByPrimaryKeySelective(selectByPrimaryKey);
        return clinicDOMapper.selectByPrimaryKey(clinicDO.getCid());
    }
}
