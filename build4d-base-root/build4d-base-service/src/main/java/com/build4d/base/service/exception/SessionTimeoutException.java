package com.build4d.base.service.exception;

public class SessionTimeoutException extends Build4DRunTimeException {

    public SessionTimeoutException() {
        super(Build4DErrorCode.SESSION_TIMEOUT_CODE, Build4DErrorCode.SESSION_TIMEOUT_MESSAGE);
    }

}
