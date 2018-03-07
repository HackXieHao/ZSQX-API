package com.selfcreate.qingxie.dao.user;

import com.selfcreate.qingxie.bean.user.UserExperience;
import com.selfcreate.qingxie.bean.user.UserExperienceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserExperienceMapper {
    long countByExample(UserExperienceExample example);

    int deleteByExample(UserExperienceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserExperience record);

    int insertSelective(UserExperience record);

    List<UserExperience> selectByExample(UserExperienceExample example);

    UserExperience selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserExperience record, @Param("example") UserExperienceExample example);

    int updateByExample(@Param("record") UserExperience record, @Param("example") UserExperienceExample example);

    int updateByPrimaryKeySelective(UserExperience record);

    int updateByPrimaryKey(UserExperience record);
}