package com.whut.health_sys.service.impl;

import com.whut.health_sys.config.CodeConfig;
import com.whut.health_sys.controller.viewobject.ClinicVO;
import com.whut.health_sys.dao.ClinicDOMapper;
import com.whut.health_sys.dataobject.ClinicDO;
import com.whut.health_sys.service.ClinicService;
import com.whut.health_sys.utils.ConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public List<ClinicVO> listClinic()
    {
        List<ClinicVO> clinicVOList;

        List<ClinicDO> clinicDOList = clinicDOMapper.listClinic();
        if(clinicDOList!=null)
        {
            clinicVOList=new ArrayList<>();
            for(ClinicDO t:clinicDOList)
            {
                clinicVOList.add(ConvertUtil.convertToClinicVO(t));
            }
            return clinicVOList;
        }
        else
        {
            return null;
        }
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
        return clinicDO;
    }

    @Override
    public ClinicDO updateClinic(ClinicDO clinicDO)
    {
        clinicDOMapper.updateByPrimaryKeySelective(clinicDO);
        return clinicDO;
    }

    @Override
    public ClinicDO CloseClinice(ClinicDO clinicDO)
    {
        ClinicDO selectByPrimaryKey = clinicDOMapper.selectByPrimaryKey(clinicDO.getCid());
        selectByPrimaryKey.setStatus(CodeConfig.STATUS_CLINIC_CLOSED);

        clinicDOMapper.updateByPrimaryKeySelective(selectByPrimaryKey);
        return clinicDOMapper.selectByPrimaryKey(clinicDO.getCid());
    }
}
