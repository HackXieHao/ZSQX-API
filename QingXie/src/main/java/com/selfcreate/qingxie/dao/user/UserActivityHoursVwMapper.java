package com.selfcreate.qingxie.dao.user;

import com.selfcreate.qingxie.bean.user.UserActivityHoursVw;
import com.selfcreate.qingxie.bean.user.UserActivityHoursVwExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserActivityHoursVwMapper {
    long countByExample(UserActivityHoursVwExample example);

    int deleteByExample(UserActivityHoursVwExample example);

    int insert(UserActivityHoursVw record);

    int insertSelective(UserActivityHoursVw record);

    List<UserActivityHoursVw> selectByExample(UserActivityHoursVwExample example);

    int updateByExampleSelective(@Param("record") UserActivityHoursVw record, @Param("example") UserActivityHoursVwExample example);

    int updateByExample(@Param("record") UserActivityHoursVw record, @Param("example") UserActivityHoursVwExample example);
}