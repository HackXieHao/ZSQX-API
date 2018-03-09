package com.selfcreate.qingxie.util;

import org.springframework.util.DigestUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptionUtils {
    private final String salt = "lkwaegnrqW?LMGe/anENglwa";

    /**
     * 密码加密方法
     *
     * @param str
     * @return
     */
    public static String encryptPassword(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte[] b = md.digest();

            int i;

            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }
            str = buf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    private String encryptPassword(int userId) {
        String base = userId + "/" + salt;
        return DigestUtils.md5DigestAsHex(base.getBytes());
    }

    /**
     * 用于产生token 验证值的方法
     * TODO:待完善
     *
     * @param obj
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String getToken(Object obj) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        return null;
    }

}
