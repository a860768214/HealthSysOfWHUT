package com.whut.health_sys.dao;

import com.whut.health_sys.dataobject.DailyDO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DailyDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DailyDO record);

    int insertSelective(DailyDO record);

    DailyDO selectByPrimaryKey(Integer id);

    List<DailyDO> selectByUser(Integer uid);

    int updateByPrimaryKeySelective(DailyDO record);

    int updateByPrimaryKey(DailyDO record);
}