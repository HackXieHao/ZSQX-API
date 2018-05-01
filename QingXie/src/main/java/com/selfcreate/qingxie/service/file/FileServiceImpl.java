package com.selfcreate.qingxie.service.file;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.OutputStream;

/**
 * @author evans 2018/4/8 23:53
 */
@Service
public class FileServiceImpl implements FileService {
    /**
     * 把文件保存到服务器，返回访问路径
     * @param in
     * @param name
     * @return
     */
    @Override
    public String save2Local(MultipartFile in, String name) {
        return null;
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
