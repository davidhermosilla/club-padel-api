package com.club.padel.exception;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.club.padel.exception.model.MSError;


@ControllerAdvice
public class ExceptionHandlingController extends MSExceptionHandlingController {

	 public static final Logger ERROR_LOG = LoggerFactory.getLogger(ExceptionHandlingController.class);
	    
	@Autowired
	private final MessageSource messageSource;

    @Autowired
    public ExceptionHandlingController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
    
    @ExceptionHandler(ClubPadelException.class)
    public ResponseEntity<MSError> resourceObtApiService(ClubPadelException ex, Locale locale) {
        
        final MSError response = new MSError(ex.getErrorCode(),ex.getMessage());
        ERROR_LOG.info("MSErrorResponse-code:"+response.getCode());
        ERROR_LOG.info("MSErrorResponse-message:"+response.getMessage());
       
        return new ResponseEntity<MSError>(response, HttpStatus.valueOf(ex.getStatus()));
    }

    @Override
    public String getGenericExceptionPropertyMessage(Locale locale) {
        return messageSource.getMessage(ExceptionErrorDetail.EXCEPTION_UNEXPECTED.value(), null, locale);
    }


}