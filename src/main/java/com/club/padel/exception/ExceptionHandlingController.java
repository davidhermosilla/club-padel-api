package com.club.padel.exception;

import java.util.ArrayList;
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
import com.club.padel.exception.model.MSErrorResponse;


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
    public ResponseEntity<MSErrorResponse> resourceObtApiService(ClubPadelException ex, Locale locale) {
        String propertyMessage = messageSource.getMessage(ex.getErrorCode(), ex.getArgs(), locale);
        String propertyMessageCode = messageSource.getMessage(ex.getErrorCode()+".code", ex.getArgs(), locale);
        ERROR_LOG.info("ExceptionValue-getErrorCode:"+ex.getErrorCode());
        ERROR_LOG.info("ExceptionValue-ex.getArgs():"+ex.getArgs());
        ERROR_LOG.info("ExceptionValue-propertyMessage:"+propertyMessage);
        ERROR_LOG.info("ExceptionValue-propertyMessage:"+propertyMessageCode);
        
        final MSErrorResponse response = new MSErrorResponse();
        final MSError obtMainError = new MSError(propertyMessageCode,propertyMessage);
        response.setError(obtMainError);
        response.setMessages(new ArrayList<>());
        ERROR_LOG.info("MSErrorResponse-code:"+response.getError().getCode());
        ERROR_LOG.info("MSErrorResponse-message:"+response.getError().getMessage());
       
        return new ResponseEntity<MSErrorResponse>(response, HttpStatus.valueOf(ex.getStatus()));
    }

    @Override
    public String getGenericExceptionPropertyMessage(Locale locale) {
        return messageSource.getMessage(ExceptionErrorDetail.EXCEPTION_UNEXPECTED.value(), null, locale);
    }


}