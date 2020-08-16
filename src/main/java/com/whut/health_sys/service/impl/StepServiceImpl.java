package com.whut.health_sys.service.impl;

import com.whut.health_sys.dao.StepDOMapper;
import com.whut.health_sys.dataobject.StepDO;
import com.whut.health_sys.dataobject.UserDO;
import com.whut.health_sys.service.StepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class StepServiceImpl implements StepService
{
    @Autowired
    private StepDOMapper stepDOMapper;

    @Override
    public List<StepDO> selectByUid(Integer uid)
    {
        if(uid==null)
        {
            return null;
        }
        return stepDOMapper.selectByUid(uid);
    }

    @Override
    public StepDO insertSelective(StepDO record)
    {
        if(record==null)
        {
            return null;
        }

        stepDOMapper.insertSelective(record);
        return record;
    }

    @Override
    public boolean check(UserDO userDO)
    {
        List<StepDO> stepDOS = selectByUid(userDO.getUid());
        if(stepDOS.size()==0)
        {
            return false;
        }

        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String now = dateformat.format(System.currentTimeMillis());
        String last =dateformat.format(Long.parseLong(stepDOS.get(stepDOS.size()-1).getDate()));

        if(now.split(" ")[0].equals(last.split(" ")[0]))
        {
            return true;
        }
        return false;
    }
}
