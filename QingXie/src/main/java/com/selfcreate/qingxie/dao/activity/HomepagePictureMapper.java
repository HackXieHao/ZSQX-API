package com.selfcreate.qingxie.dao.activity;

import com.selfcreate.qingxie.bean.activity.HomepagePicture;
import com.selfcreate.qingxie.bean.activity.HomepagePictureExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HomepagePictureMapper {
    long countByExample(HomepagePictureExample example);

    int deleteByExample(HomepagePictureExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(HomepagePicture record);

    int insertSelective(HomepagePicture record);

    List<HomepagePicture> selectByExample(HomepagePictureExample example);

    HomepagePicture selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") HomepagePicture record, @Param("example") HomepagePictureExample example);

    int updateByExample(@Param("record") HomepagePicture record, @Param("example") HomepagePictureExample example);

    int updateByPrimaryKeySelective(HomepagePicture record);

    int updateByPrimaryKey(HomepagePicture record);
}