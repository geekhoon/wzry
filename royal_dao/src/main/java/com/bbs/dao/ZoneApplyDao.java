package com.bbs.dao;

import com.bbs.domain.ZoneApply;
import com.bbs.domain.ZoneApplyExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ZoneApplyDao {
    int countByExample(ZoneApplyExample example);

    int deleteByExample(ZoneApplyExample example);

    int deleteByPrimaryKey(Integer applyzoneid);

    int insert(ZoneApply record);

    int insertSelective(ZoneApply record);

    List<ZoneApply> selectByExample(ZoneApplyExample example);

    ZoneApply selectByPrimaryKey(Integer applyzoneid);

    int updateByExampleSelective(@Param("record") ZoneApply record, @Param("example") ZoneApplyExample example);

    int updateByExample(@Param("record") ZoneApply record, @Param("example") ZoneApplyExample example);

    int updateByPrimaryKeySelective(ZoneApply record);

    int updateByPrimaryKey(ZoneApply record);
}