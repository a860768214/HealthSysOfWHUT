package com.whut.health_sys.dao;

import com.whut.health_sys.dataobject.AppointmentDO;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentDOMapper {
    int deleteByPrimaryKey(Integer aid);

    int insert(AppointmentDO record);

    int insertSelective(AppointmentDO record);

    AppointmentDO selectByPrimaryKey(Integer aid);

    int updateByPrimaryKeySelective(AppointmentDO record);

    int updateByPrimaryKey(AppointmentDO record);
}