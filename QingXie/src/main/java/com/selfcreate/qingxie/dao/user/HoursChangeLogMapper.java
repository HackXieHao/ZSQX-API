package com.selfcreate.qingxie.dao.user;

import com.selfcreate.qingxie.bean.user.HoursChangeLog;
import com.selfcreate.qingxie.bean.user.HoursChangeLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HoursChangeLogMapper {
    long countByExample(HoursChangeLogExample example);

    int deleteByExample(HoursChangeLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(HoursChangeLog record);

    int insertSelective(HoursChangeLog record);

    List<HoursChangeLog> selectByExample(HoursChangeLogExample example);

    HoursChangeLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") HoursChangeLog record, @Param("example") HoursChangeLogExample example);

    int updateByExample(@Param("record") HoursChangeLog record, @Param("example") HoursChangeLogExample example);

    int updateByPrimaryKeySelective(HoursChangeLog record);

    int updateByPrimaryKey(HoursChangeLog record);
}