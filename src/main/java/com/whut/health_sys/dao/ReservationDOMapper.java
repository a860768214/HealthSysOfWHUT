package com.whut.health_sys.dao;

import com.whut.health_sys.dataobject.ReservationDO;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationDOMapper {
    int deleteByPrimaryKey(Integer rid);

    int insert(ReservationDO record);

    int insertSelective(ReservationDO record);

    ReservationDO selectByPrimaryKey(Integer rid);

    int updateByPrimaryKeySelective(ReservationDO record);

    int updateByPrimaryKey(ReservationDO record);
}