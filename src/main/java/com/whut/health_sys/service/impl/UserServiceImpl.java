package com.whut.health_sys.service.impl;

import com.whut.health_sys.dao.UserDOMapper;
import com.whut.health_sys.dataobject.UserDO;
import com.whut.health_sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService
{
    @Autowired
    private UserDOMapper userDOMapper;

    @Override
    public UserDO selectByMail(String mail)
    {
        if("".equals(mail))
        {
            return null;
        }

        return userDOMapper.selectByMail(mail);
    }

    @Override
    public int insertSelective(UserDO record)
    {
        userDOMapper.insertSelective(record);
        return 0;
    }

    @Override
    public UserDO selectByUserIdAndStatus(Integer userid, Integer status)
    {
        return userDOMapper.selectByUserIdAndStatus(userid, status);
    }

    @Override
    public UserDO selectByUserId(Integer userid)
    {
        return userDOMapper.selectByPrimaryKey(userid);
    }

    @Override
    public UserDO update(UserDO userDO)
    {
        if(userDO==null)
        {
            return null;
        }
        Integer uid = userDOMapper.selectByMail(userDO.getSchmail()).getUid();
        userDO.setUid(uid);

        userDOMapper.updateByPrimaryKeySelective(userDO);
        return userDO;
    }
}
