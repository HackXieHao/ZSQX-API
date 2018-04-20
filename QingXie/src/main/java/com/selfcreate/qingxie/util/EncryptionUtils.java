package com.selfcreate.qingxie.util;

import org.springframework.util.DigestUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptionUtils {
    private static final String salt = "lkwaegnrqW?LMGe/anENglwa";

    /**
     * 密码加密方法
     *
     * @param str
     * @return
     */
    public static String encryptPassword(String str) {
        String base = str + "/" + salt;
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
