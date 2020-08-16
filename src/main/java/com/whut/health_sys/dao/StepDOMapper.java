package com.whut.health_sys.dao;

import com.whut.health_sys.dataobject.StepDO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StepDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StepDO record);

    int insertSelective(StepDO record);

    StepDO selectByPrimaryKey(Integer id);

    List<StepDO> selectByUid(Integer uid);

    int updateByPrimaryKeySelective(StepDO record);

    int updateByPrimaryKey(StepDO record);
}