package com.selfcreate.qingxie.exception;

/**
 * @author evans 2018/3/8 11:14
 */

public class InvalidPasswordException extends ValidationFailureException {
    public InvalidPasswordException(String message) {
        super(message);
    }

    public InvalidPasswordException(String message, Throwable cause) {
        super(message, cause);
    }
}
