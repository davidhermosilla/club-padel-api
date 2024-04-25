package com.club.padel.exception;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import com.club.padel.exception.model.MSError;


@ControllerAdvice
public class ExceptionHandlingController extends DefaultHandlerExceptionResolver	 {

	 public static final Logger ERROR_LOG = LoggerFactory.getLogger(ExceptionHandlingController.class);
	    
	@Autowired
	private final MessageSource messageSource;

    @Autowired
    public ExceptionHandlingController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
    
    @ExceptionHandler(value = {ClubPadelException.class})
    public ResponseEntity<MSError> handleExceptions(ClubPadelException ex, HttpServletRequest request, HttpServletResponse response, Locale locale) {
        
        final MSError responseException = new MSError(ex.getErrorCode(),ex.getMessage());
        ERROR_LOG.info("MSErrorResponse-code:"+responseException.getCode());
        ERROR_LOG.info("MSErrorResponse-message:"+responseException.getMessage());
       
        return new ResponseEntity<MSError>(responseException, ex.getStatus());
    }

}