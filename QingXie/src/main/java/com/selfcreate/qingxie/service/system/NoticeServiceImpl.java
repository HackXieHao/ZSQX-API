package com.selfcreate.qingxie.service.system;

import com.selfcreate.qingxie.bean.system.Notice;
import com.selfcreate.qingxie.bean.system.NoticeExample;
import com.selfcreate.qingxie.dao.system.NoticeMapper;
import com.selfcreate.qingxie.exception.QingxieInnerException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author evans 2018/4/16 13:10
 */
@Service
public class NoticeServiceImpl implements NoticeService {
    private final Logger logger = Logger.getLogger(this.getClass());
    @Autowired
    NoticeMapper noticeMapper;

    @Override
    public List getNotices() {
        try {
            NoticeExample example = new NoticeExample();
            return noticeMapper.selectByExample(example);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new QingxieInnerException("数据库读取异常");
        }
    }

    @Override
    public Notice getDetails(int id) {
        NoticeExample example=new NoticeExample();
        example.createCriteria().andIdEqualTo(id);
        List<Notice> record;
        try {
            record=noticeMapper.selectByExample(example);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new QingxieInnerException("数据库读取异常");
        }
        if(record.isEmpty()){
            throw new QingxieInnerException("无效id");
        }
        return record.get(0);
    }

    @Override
    public void pushNotice(Notice record) {
        try {
            noticeMapper.insert(record);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new QingxieInnerException("数据库读取异常");
        }
    }
}
