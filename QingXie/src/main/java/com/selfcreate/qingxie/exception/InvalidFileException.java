package com.selfcreate.qingxie.exception;

/**
 * @author evans 2018/3/30 13:22
 */

public class InvalidFileException extends ValidationFailureException{
    public InvalidFileException(String message) {
        super(message);
    }

    public InvalidFileException(String message, Throwable cause) {
        super(message, cause);
    }
}
