package com.club.padel.exception.model;

import java.util.List;

import com.club.padel.exception.utils.MSMessageUtils;

public class MSError {
    private String code;
    private String message;

    protected MSError() {

    }

    public MSError(String code, String message) {
        super();
        this.setCode(code);
        this.setMessage(message);
    }

    public MSError(String propertyMessage) {
        super();
        setMessageAndCodeFromProperty(propertyMessage);
    }

    public String getCode() {
        return code;
    }

    protected void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    protected void setMessage(String message) {

        this.message = message;
    }

    public void setMessageAndCodeFromProperty(String propertyMessage) {
        List<String> result = MSMessageUtils.getCodeAndDetails(propertyMessage);
        this.setCode(!result.isEmpty() ? result.get(MSMessageUtils.CODE_LIST_POSITION) : null);
        this.setMessage(!result.isEmpty() && result.size() == 2 ? result.get(MSMessageUtils.DETAILS_LIST_POSITION) : null);
    }

}
