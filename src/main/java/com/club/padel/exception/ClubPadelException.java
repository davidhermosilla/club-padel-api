package com.club.padel.exception;

import org.springframework.http.HttpStatus;

public class ClubPadelException extends MSGeneralException {

	private static final long serialVersionUID = 1L;

	public ClubPadelException(HttpStatus httpStatus, ExceptionErrorDetail ex, String... params) {
		setStatus(httpStatus.value());
		setErrorCode(ex.value());
		setArgs(params);
	}

}
