package com.whut.health_sys.service;

import com.whut.health_sys.dataobject.UserDO;

public interface UserService
{
    UserDO selectByMail(String mail);

    UserDO insertSelective(UserDO record);

    UserDO selectByUserIdAndStatus(Integer userid,Integer status);

    UserDO selectByUserId(Integer userid);

    UserDO update(UserDO userDO);
}
