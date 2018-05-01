package com.selfcreate.qingxie.service.system;

import com.selfcreate.qingxie.bean.system.Notice;

import java.util.List;

/**
 * @author evans 2018/4/16 13:07
 */

public interface NoticeService {
    List getNotices();
    Notice getDetails(int id);
    void pushNotice(Notice record);
}
