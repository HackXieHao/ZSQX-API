package com.selfcreate.qingxie.service.file;

import org.springframework.web.multipart.MultipartFile;

import java.io.OutputStream;

/**
 * @author evans 2018/4/8 23:50
 */

public interface FileService {
    String save2Local(MultipartFile in, String name);
    OutputStream loadFromLocal(String filePath);
}
