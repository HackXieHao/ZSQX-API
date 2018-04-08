package com.selfcreate.qingxie.util;

import com.selfcreate.qingxie.exception.QingxieInnerException;
import org.springframework.util.DigestUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author evans 2018/3/10 15:39
 */

public class FileUtil {
    private static final String SALT = "lawejgoianw3t43tnzlkesg";
    public static final String ICON_PATH = "/qingxie-img/";
    private static final String ICON_SAVE_PATH = "/home/develop/qingxie/icon";
    private static final String FILE_SAVE_PATH = "/home/develop/qingxie/files";
    private static final String PIC_SAVE_PATH = "/home/develop/qingxie/pics";

    /**
     * 返回头像访问路径
     *
     * @param userId
     * @param fileName
     * @return
     */
    public static String getAccessPath(int userId, String fileName) {
        return ICON_PATH + "icon" + "/" + getSubPath(userId, fileName);
    }

    /**
     * 返回头像保存路径
     *
     * @param userId
     * @param fileName
     * @return
     */
    public static String getSavePath(int userId, String fileName) {

        return ICON_SAVE_PATH + "/" + getSubPath(userId, fileName);
    }

    private static void mkdir(String path) {
        File file = new File(path);
        //判断文件目录是否存在
        if (!file.exists()) {
            //如果文件目录不存在则新建目录
            if (!file.mkdirs()) {
                //创建失败
                throw new QingxieInnerException("服务器磁盘读写失败");
            }
        }
    }

    /**
     * 根据userId将用户图片分文件夹存放
     *
     * @param userId
     * @param fileName
     * @return
     */
    private static String getSubPath(int userId, String fileName) {
        String type = "";
        if (fileName.contains(".")) {
            type = fileName.substring(fileName.lastIndexOf("."));
        }
        String base = fileName + "/" + SALT;
        String name = DigestUtils.md5DigestAsHex(base.getBytes());
        //分目录保存
        String dir = DigestUtils.md5DigestAsHex(((userId / 100) + SALT).getBytes());
        mkdir(ICON_SAVE_PATH + dir);
        return dir + "/" + name + type;
    }

    private static String getRandomName(String originName) {
        String type = "";
        if (originName.contains(".")) {
            type = originName.substring(originName.lastIndexOf("."));
        }
        String base = originName + "/" + SALT;
        String name = DigestUtils.md5DigestAsHex(base.getBytes());
        return name + type;
    }

    /**
     * 保存文件到本地
     *
     * @param in
     * @param name
     * @throws IOException
     * @throws QingxieInnerException
     */
    public static void save(InputStream in, String name) throws IOException, QingxieInnerException {
        mkdir(FILE_SAVE_PATH);
        try (FileOutputStream fos = new FileOutputStream(FILE_SAVE_PATH + "/" + name)) {
            byte[] bytes = new byte[in.available()];
            in.read(bytes);
            fos.write(bytes);
        }
    }

    /**
     * 保存活动图片的接口
     *
     * @param in
     * @param name
     * @return
     * @throws IOException
     * @throws QingxieInnerException
     */
    public static String savePic(InputStream in, String name) throws IOException, QingxieInnerException {
        mkdir(PIC_SAVE_PATH);
        String newName = getRandomName(name);
        try (FileOutputStream fos = new FileOutputStream(PIC_SAVE_PATH+"/"+newName)) {
            byte[] bytes = new byte[in.available()];
            in.read(bytes);
            fos.write(bytes);
        }
        return ICON_PATH + "pics/" + newName;
    }

}
