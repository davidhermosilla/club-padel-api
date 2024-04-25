package com.club.padel.exception;

import com.club.padel.exception.model.MSErrorSeverity;

public class MSGeneralException extends RuntimeException {

    private String[] args;
    private String errorCode;
    private int status;
    private MSErrorSeverity severity = MSErrorSeverity.SEVERITY_ERROR;

    public MSGeneralException() {

    }

    public MSGeneralException(Exception ex, int status, String errorCode, String... args) {
        super(ex);
        this.args = args;
        this.errorCode = errorCode;
        this.status = status;
    }

    public MSGeneralException(int status, String errorCode, String... args) {
        this.args = args;
        this.errorCode = errorCode;
        this.status = status;
    }

    public String[] getArgs() {
        return args;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public int getStatus() {
        return status;
    }

    public void setArg(String arg) {
        this.args = new String[]{arg};
    }

    public void setArgs(String[] args) {
        this.args = args;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public MSErrorSeverity getSeverity() {
        return severity;
    }

    public void setSeverity(MSErrorSeverity severity) {
        this.severity = severity;
    }

}