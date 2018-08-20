package com.build4d.base.service.exception;

public class Build4DRunTimeException extends RuntimeException  {

    /**
     * 错误码
     */
    private Integer errorCode;

    public Build4DRunTimeException(String message) {
        super(message);
    }

    public Build4DRunTimeException(Integer errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public Build4DRunTimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public Build4DRunTimeException(Integer errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String toString() {
        return (errorCode != null ? "errorCode: " + errorCode + ", " : "") + super.toString();
    }


}
