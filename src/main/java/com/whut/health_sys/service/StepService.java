package com.whut.health_sys.service;

import com.whut.health_sys.dataobject.StepDO;
import com.whut.health_sys.dataobject.UserDO;

import java.util.List;

public interface StepService
{
    List<StepDO> selectByUid(Integer uid);

    StepDO insertSelective(StepDO record);

    boolean check(UserDO userDO);
}
