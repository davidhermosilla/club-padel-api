package com.club.padel.exception;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import com.club.padel.exception.model.MSError;

public abstract class MSExceptionHandlingController extends DefaultHandlerExceptionResolver {

    public static final Logger ERROR_LOG = LoggerFactory.getLogger(MSExceptionHandlingController.class);
    
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<MSError> handleExceptions(Exception ex, HttpServletRequest request, HttpServletResponse response, Locale locale) {
////        HttpMSServletResponseWrapper responseWrapper = new HttpMSServletResponseWrapper(response);
//        MSError obtMainError = new MSError(getGenericExceptionPropertyMessage(locale));
////        responseWrapper.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
////
////        this.doResolveException(request, responseWrapper, this, ex);
////
////        if (responseWrapper.getStatus() != HttpStatus.INTERNAL_SERVER_ERROR.value()) {
////            obtMainError = new MSError(String.valueOf(responseWrapper.getStatus()), ex.getMessage());
////        }
//        
//        final MSError msErrorResponse = new MSError("CLUB01",ex.getMessage());
//        ERROR_LOG.error(obtMainError.getMessage(), ex);
//        return new ResponseEntity<MSError>(msErrorResponse, HttpStatus.valueOf(response.getStatus()));
//    }

    public abstract String getGenericExceptionPropertyMessage(Locale locale);

}
