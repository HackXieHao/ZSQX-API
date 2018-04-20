package com.selfcreate.qingxie.exception;

/**
 * 用户验证信息异常类
 * @author evans 2018/2/26 23:29
 */

public class InvalidStudentIdException extends ValidationFailureException {
    public InvalidStudentIdException(String message) {
        super(message);
    }

    public InvalidStudentIdException(String message, Throwable cause) {
        super(message, cause);
    }
}
