package com.selfcreate.qingxie.service.file;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * @author evans 2018/3/30 13:02
 */

public interface ExcelTransfer {
    /**
     * 将excel文件数据转存到数据库
     *
     * @return
     */
    void excel2DB(InputStream in, String name) throws IOException;

    void save2Local(InputStream in,String name) throws IOException;
//    /**
//     * 将List转为excel文档
//     *
//     * @return
//     */
//    Workbook list2Excel();
}
