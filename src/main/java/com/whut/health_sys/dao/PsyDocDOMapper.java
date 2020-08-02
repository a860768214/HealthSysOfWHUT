package com.whut.health_sys.dao;

import com.whut.health_sys.dataobject.PsyDocDO;
import org.springframework.stereotype.Repository;

@Repository
public interface PsyDocDOMapper {
    int deleteByPrimaryKey(Integer did);

    int insert(PsyDocDO record);

    int insertSelective(PsyDocDO record);

    PsyDocDO selectByPrimaryKey(Integer did);

    int updateByPrimaryKeySelective(PsyDocDO record);

    int updateByPrimaryKey(PsyDocDO record);
}