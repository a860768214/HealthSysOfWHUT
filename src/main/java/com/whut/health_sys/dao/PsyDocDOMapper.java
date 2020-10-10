package com.whut.health_sys.dao;

import com.whut.health_sys.dataobject.ClinicDO;
import com.whut.health_sys.dataobject.PsyDocDO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PsyDocDOMapper {
    int deleteByPrimaryKey(Integer did);

    int insert(PsyDocDO record);

    int insertSelective(PsyDocDO record);

    PsyDocDO selectByPrimaryKey(Integer did);

    PsyDocDO selectByName(String name);

    int updateByPrimaryKeySelective(PsyDocDO record);

    int updateByPrimaryKey(PsyDocDO record);

    List<PsyDocDO> listPsyDoc();
}