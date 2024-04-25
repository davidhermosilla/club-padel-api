package com.club.padel.exception;


public enum ExceptionErrorDetail {

	EXCEPTION_UNEXPECTED("Exception.unexpected"),
	ERROR_FILE("file.error"),
	ERROR_COMMAND("command.exception"),
    ERROR_GETTING_TOKEN("token.error"),
    ERROR_MAPPING_TOKEN_RESPONSE("token.response.error"),
    ERROR_EMPTY_PARAMS("emtpy.required.params"),
    TIMEOUT_EXCEPTION("timeout.exception"),
    MANDATORY_PARAM("mandatory.param"),
    FORBIDDEN("forbidden.exception"),

	;

    private final String value;

    ExceptionErrorDetail(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ExceptionErrorDetail fromValue(String v) {
        for (ExceptionErrorDetail c : ExceptionErrorDetail.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException();
    }
}
