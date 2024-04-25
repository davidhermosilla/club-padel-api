package com.club.padel.exception.model;

import java.util.List;

import com.club.padel.exception.utils.MSMessageUtils;

public class MSMessage extends MSError {
    private Integer severity;
    private String extramessage;

    public MSMessage(Integer severity, String propertyMessage, String propertyExtraMessage) {
        super();
        this.severity = severity;
        this.setMessage(propertyMessage);
        this.setExtraMessageAndCodeFromProperty(propertyExtraMessage);
    }

    public Integer getSeverity() {
        return severity;
    }

    public void setSeverity(Integer severity) {
        this.severity = severity;
    }

    public String getExtramessage() {
        return extramessage;
    }

    private void setExtramessage(String extramessage) {
        this.extramessage = extramessage;
    }

    public void setExtraMessageAndCodeFromProperty(String propertyMessage) {
        List<String> result = MSMessageUtils.getCodeAndDetails(propertyMessage);
        this.setCode(!result.isEmpty() ? result.get(MSMessageUtils.CODE_LIST_POSITION) : null);
        this.setExtramessage(!result.isEmpty() && result.size() == 2 ? result.get(MSMessageUtils.DETAILS_LIST_POSITION) : null);
    }

    @Override
    public String toString() {
        return "ProblemDetails [severity=" + severity + ", code=" + getCode() + ", message=" + getMessage() + ", extramessage="
                + extramessage + "]";
    }

}
