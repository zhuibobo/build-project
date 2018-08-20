package com.build4d.base.service.exception;

public class Build4DBaseException  extends Exception {
    /**
     * 错误码
     */
    private Integer errorCode;

    public Build4DBaseException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public Build4DBaseException(int errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String toString() {
        return "errorCode: " + errorCode + ", " + super.toString();
    }
}
