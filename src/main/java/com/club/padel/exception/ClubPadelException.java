package com.club.padel.exception;

import org.springframework.http.HttpStatus;

public class ClubPadelException extends Exception {

	private static final long serialVersionUID = 1L;
	HttpStatus status;
	String errorCode;
	
	public ClubPadelException(HttpStatus httpStatus,String errorCode,String message) {
		super(message);
		this.status = httpStatus;
	}

	public ClubPadelException(HttpStatus httpStatus, ExceptionErrorDetail errorCode,String message) {
		super(message);
		this.status = httpStatus;
		this.errorCode = errorCode.value();
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setHStatus(HttpStatus status) {
		this.status = status;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
