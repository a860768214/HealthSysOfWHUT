package com.whut.health_sys.dao;

import com.whut.health_sys.dataobject.ClinicDO;
import org.springframework.stereotype.Repository;

@Repository
public interface ClinicDOMapper {
    int deleteByPrimaryKey(Integer cid);

    int insert(ClinicDO record);

    int insertSelective(ClinicDO record);

    ClinicDO selectByPrimaryKey(Integer cid);

    int updateByPrimaryKeySelective(ClinicDO record);

    int updateByPrimaryKey(ClinicDO record);
}