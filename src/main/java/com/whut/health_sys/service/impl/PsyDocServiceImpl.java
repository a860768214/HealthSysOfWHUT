package com.whut.health_sys.service.impl;

import com.whut.health_sys.config.CodeConfig;
import com.whut.health_sys.controller.viewobject.ClinicVO;
import com.whut.health_sys.controller.viewobject.PsyDocVO;
import com.whut.health_sys.dao.PsyDocDOMapper;
import com.whut.health_sys.dataobject.ClinicDO;
import com.whut.health_sys.dataobject.PsyDocDO;
import com.whut.health_sys.service.PsyDocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PsyDocServiceImpl implements PsyDocService
{
    @Autowired
    private PsyDocDOMapper psyDocDOMapper;

    @Override
    public PsyDocDO searchPsyDoc(String name)
    {
        if("".equals(name)||name==null)
        {
            return null;
        }
        return psyDocDOMapper.selectByName(name);
    }

    @Override
    public List<PsyDocDO> listPsyDoc()
    {
        return psyDocDOMapper.listPsyDoc();
    }

    @Override
    public PsyDocDO selectPsyDoc(Integer did)
    {
        if(did==null)
        {
            return null;
        }
        return psyDocDOMapper.selectByPrimaryKey(did);
    }

    @Override
    public PsyDocDO savePsyDoc(PsyDocDO psyDocDO)
    {
        if(psyDocDO==null)
        {
            return null;
        }
        psyDocDOMapper.insertSelective(psyDocDO);
        return psyDocDO;
    }

    @Override
    public PsyDocDO updatePsyDoc(PsyDocDO psyDocDO)
    {
        if(psyDocDO==null)
        {
            return null;
        }
        psyDocDOMapper.updateByPrimaryKeySelective(psyDocDO);
        return psyDocDO;
    }

    @Override
    public PsyDocDO ClosePsyDoc(PsyDocDO psyDocDO)
    {
        if(psyDocDO==null)
        {
            return null;
        }
        PsyDocDO selectByPrimaryKey = psyDocDOMapper.selectByPrimaryKey(psyDocDO.getDid());
        selectByPrimaryKey.setStatus(CodeConfig.STATUS_DOC_CLOSED);

        psyDocDOMapper.updateByPrimaryKeySelective(selectByPrimaryKey);
        return psyDocDOMapper.selectByPrimaryKey(psyDocDO.getDid());
    }
}
