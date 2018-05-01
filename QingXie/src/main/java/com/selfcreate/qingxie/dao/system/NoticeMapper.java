package com.selfcreate.qingxie.dao.system;

import com.selfcreate.qingxie.bean.system.Notice;
import com.selfcreate.qingxie.bean.system.NoticeExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NoticeMapper {
    int countByExample(NoticeExample example);

    int deleteByExample(NoticeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Notice record);

    int insertSelective(Notice record);

    List<Notice> selectByExample(NoticeExample example);

    Notice selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Notice record, @Param("example") NoticeExample example);

    int updateByExample(@Param("record") Notice record, @Param("example") NoticeExample example);

    int updateByPrimaryKeySelective(Notice record);

    int updateByPrimaryKey(Notice record);
}