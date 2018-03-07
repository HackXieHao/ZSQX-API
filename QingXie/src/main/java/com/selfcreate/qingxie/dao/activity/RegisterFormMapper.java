package com.selfcreate.qingxie.dao.activity;

import com.selfcreate.qingxie.bean.activity.RegisterForm;
import com.selfcreate.qingxie.bean.activity.RegisterFormExample;
import com.selfcreate.qingxie.bean.activity.RegisterFormKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RegisterFormMapper {
    long countByExample(RegisterFormExample example);

    int deleteByExample(RegisterFormExample example);

    int deleteByPrimaryKey(RegisterFormKey key);

    int insert(RegisterForm record);

    int insertSelective(RegisterForm record);

    List<RegisterForm> selectByExample(RegisterFormExample example);

    RegisterForm selectByPrimaryKey(RegisterFormKey key);

    int updateByExampleSelective(@Param("record") RegisterForm record, @Param("example") RegisterFormExample example);

    int updateByExample(@Param("record") RegisterForm record, @Param("example") RegisterFormExample example);

    int updateByPrimaryKeySelective(RegisterForm record);

    int updateByPrimaryKey(RegisterForm record);
}