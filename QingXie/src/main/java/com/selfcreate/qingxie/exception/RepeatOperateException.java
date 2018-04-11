package com.selfcreate.qingxie.exception;

/**
 * @author evans 2018/4/11 8:54
 */

public class RepeatOperateException extends ValidationFailureException {

    public RepeatOperateException(String message) {
        super(message);
    }

    public RepeatOperateException(String message, Throwable cause) {
        super(message, cause);
    }
}
