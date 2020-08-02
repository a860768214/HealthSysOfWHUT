package com.whut.health_sys.dao;

import com.whut.health_sys.dataobject.UserDO;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDOMapper {
    int deleteByPrimaryKey(Integer uid);

    int insert(UserDO record);

    int insertSelective(UserDO record);

    UserDO selectByPrimaryKey(Integer uid);

    UserDO selectByUserIdAndStatus(Integer uid,Integer status);

    UserDO selectByMail(String mail);

    int updateByPrimaryKeySelective(UserDO record);

    int updateByPrimaryKey(UserDO record);
}