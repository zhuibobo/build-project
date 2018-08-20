package com.build4d.base.service.exception;

public class Build4DGenerallyException extends Build4DBaseException {

    static int defaultCode=0;

    public Build4DGenerallyException(String message) {
        super(defaultCode, message);
    }

    public Build4DGenerallyException(int errorCode, String message) {
        super(errorCode, message);
    }

    public Build4DGenerallyException(int errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }
}
