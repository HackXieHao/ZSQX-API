package com.selfcreate.qingxie.util;

import com.selfcreate.qingxie.exception.QingxieInnerException;
import org.springframework.util.DigestUtils;

import java.io.File;

/**
 * @author evans 2018/3/10 15:39
 */

public class FileUtil {
    private static final String SALT = "lawejgoianw3t43tnzlkesg";
    public static final String ICON_PATH = "/qingxie-img/";
    private static final String ICON_SAVE_PATH = "/home/develop/images/qingxie/";

    /**
     * 返回头像访问路径
     * @param userId
     * @param fileName
     * @return
     */
    public static String getAccessPath(int userId, String fileName) {
        return ICON_PATH + getSubPath(userId, fileName);
    }

    /**
     * 返回头像保存路径
     * @param userId
     * @param fileName
     * @return
     */
    public static String getSavePath(int userId, String fileName) {

        return ICON_SAVE_PATH + getSubPath(userId, fileName);
    }

    private static String getSubPath(int userId, String fileName) {
        String type = fileName.substring(fileName.lastIndexOf("."));
        String base = fileName + "/" + SALT;
        String name = DigestUtils.md5DigestAsHex(base.getBytes());
        //分目录保存
        String dir = DigestUtils.md5DigestAsHex(((userId / 100) + SALT).getBytes());
        File file=new File(ICON_SAVE_PATH+dir);
        //判断文件目录是否存在
        if(!file.exists()){
            //如果文件目录不存在则新建目录
            if(!file.mkdirs()){
                //创建失败
                throw new QingxieInnerException("服务器磁盘读写失败");
            }
        }
        return dir + "/" + name + type;
    }


}
