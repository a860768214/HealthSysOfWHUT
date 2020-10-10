package com.whut.health_sys.dao;

import com.whut.health_sys.dataobject.AppointmentDO;
import com.whut.health_sys.dataobject.ReservationDO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentDOMapper {
    int deleteByPrimaryKey(Integer aid);

    int insert(AppointmentDO record);

    int insertSelective(AppointmentDO record);

    AppointmentDO selectByPrimaryKey(Integer aid);

    int updateByPrimaryKeySelective(AppointmentDO record);

    int updateByPrimaryKey(AppointmentDO record);

    List<AppointmentDO> listAppo();

    List<AppointmentDO> searchAppoByUser(Integer uid);
}