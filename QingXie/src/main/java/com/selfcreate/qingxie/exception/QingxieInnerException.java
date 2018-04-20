package com.selfcreate.qingxie.exception;

/**
 * 封装异常信息
 * @author evans 2018/3/9 10:58
 */

public class QingxieInnerException extends RuntimeException {
    public QingxieInnerException(String message) {
        super(message);
    }

    public QingxieInnerException(String message, Throwable cause) {
        super(message, cause);
    }
}