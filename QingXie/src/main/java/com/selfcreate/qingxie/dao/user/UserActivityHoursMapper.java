package com.selfcreate.qingxie.dao.user;

import com.selfcreate.qingxie.bean.user.UserActivityHours;
import com.selfcreate.qingxie.bean.user.UserActivityHoursExample;
import com.selfcreate.qingxie.bean.user.UserActivityHoursKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserActivityHoursMapper {
    long countByExample(UserActivityHoursExample example);

    int deleteByExample(UserActivityHoursExample example);

    int deleteByPrimaryKey(UserActivityHoursKey key);

    int insert(UserActivityHours record);

    int insertSelective(UserActivityHours record);

    List<UserActivityHours> selectByExample(UserActivityHoursExample example);

    UserActivityHours selectByPrimaryKey(UserActivityHoursKey key);

    int updateByExampleSelective(@Param("record") UserActivityHours record, @Param("example") UserActivityHoursExample example);

    int updateByExample(@Param("record") UserActivityHours record, @Param("example") UserActivityHoursExample example);

    int updateByPrimaryKeySelective(UserActivityHours record);

    int updateByPrimaryKey(UserActivityHours record);
}