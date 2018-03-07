package com.selfcreate.qingxie.dao.activity;

import com.selfcreate.qingxie.bean.activity.ActivityDetailsPicture;
import com.selfcreate.qingxie.bean.activity.ActivityDetailsPictureExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ActivityDetailsPictureMapper {
    long countByExample(ActivityDetailsPictureExample example);

    int deleteByExample(ActivityDetailsPictureExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ActivityDetailsPicture record);

    int insertSelective(ActivityDetailsPicture record);

    List<ActivityDetailsPicture> selectByExample(ActivityDetailsPictureExample example);

    ActivityDetailsPicture selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ActivityDetailsPicture record, @Param("example") ActivityDetailsPictureExample example);

    int updateByExample(@Param("record") ActivityDetailsPicture record, @Param("example") ActivityDetailsPictureExample example);

    int updateByPrimaryKeySelective(ActivityDetailsPicture record);

    int updateByPrimaryKey(ActivityDetailsPicture record);
}