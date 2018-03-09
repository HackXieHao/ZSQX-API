package com.selfcreate.qingxie.exception;

/**
 * @author evans 2018/3/8 11:11
 */

public class ValidationFailureException extends RuntimeException {
    public ValidationFailureException(String message){
        super(message);
    }
    public ValidationFailureException(String message, Throwable cause){
        super(message,cause);
    }
}
