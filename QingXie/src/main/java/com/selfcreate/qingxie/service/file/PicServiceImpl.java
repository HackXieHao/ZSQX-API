package com.selfcreate.qingxie.service.file;

import com.selfcreate.qingxie.exception.QingxieInnerException;
import com.selfcreate.qingxie.util.FileUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author evans 2018/4/8 23:55
 */

@Service
public class PicServiceImpl implements FileService{
    final Logger logger=Logger.getLogger(this.getClass());
    /**
     * 将图片保存到本地，
     * 返回访问路径
     * FIXME:需加入权限验证，青协工作者及管理员所有
     * @param pic
     * @param name
     * @return
     */
    @Override
    public String save2Local(MultipartFile pic, String name) {
        try {
            //返回可访问的路径
            return FileUtil.savePic(pic.getInputStream(), name);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            throw new QingxieInnerException("服务器文件读写异常");
        }
    }

    /**
     * 从服务器读取文件，返回输出流
     * @return
     */
    @Override
    public OutputStream loadFromLocal(String filePath) {
        return null;
    }
}
