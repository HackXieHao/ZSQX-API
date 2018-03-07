package com.selfcreate.qingxie.dao.user;

import com.selfcreate.qingxie.bean.user.Icon;
import com.selfcreate.qingxie.bean.user.IconExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IconMapper {
    long countByExample(IconExample example);

    int deleteByExample(IconExample example);

    int deleteByPrimaryKey(Integer iconId);

    int insert(Icon record);

    int insertSelective(Icon record);

    List<Icon> selectByExample(IconExample example);

    Icon selectByPrimaryKey(Integer iconId);

    int updateByExampleSelective(@Param("record") Icon record, @Param("example") IconExample example);

    int updateByExample(@Param("record") Icon record, @Param("example") IconExample example);

    int updateByPrimaryKeySelective(Icon record);

    int updateByPrimaryKey(Icon record);
}