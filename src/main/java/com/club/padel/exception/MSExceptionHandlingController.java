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

import com.club.padel.exception.model.HttpMSServletResponseWrapper;
import com.club.padel.exception.model.MSError;
import com.club.padel.exception.model.MSErrorResponse;

public abstract class MSExceptionHandlingController extends DefaultHandlerExceptionResolver {

    public static final Logger ERROR_LOG = LoggerFactory.getLogger(MSExceptionHandlingController.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MSErrorResponse> handleExceptions(Exception ex, HttpServletRequest request, HttpServletResponse response, Locale locale) {
        HttpMSServletResponseWrapper responseWrapper = new HttpMSServletResponseWrapper(response);
        MSError obtMainError = new MSError(getGenericExceptionPropertyMessage(locale));
        responseWrapper.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

        this.doResolveException(request, responseWrapper, this, ex);

        if (responseWrapper.getStatus() != HttpStatus.INTERNAL_SERVER_ERROR.value()) {
            obtMainError = new MSError(String.valueOf(responseWrapper.getStatus()), ex.getMessage());
        }

        final MSErrorResponse msErrorResponse = new MSErrorResponse();
        msErrorResponse.setError(obtMainError);
        ERROR_LOG.error(obtMainError.getMessage(), ex);
        return new ResponseEntity<MSErrorResponse>(msErrorResponse, HttpStatus.valueOf(response.getStatus()));
    }

    public ResponseEntity handleSpecificException (String propertyMessage, MSGeneralException ex) {
        final MSErrorResponse response = new MSErrorResponse();
        final MSError obtMainError = new MSError(propertyMessage);
        response.setError(obtMainError);
        ERROR_LOG.error(obtMainError.getMessage(), ex);
        return new ResponseEntity<MSErrorResponse>(response, HttpStatus.valueOf(ex.getStatus()));
    }

    public abstract String getGenericExceptionPropertyMessage(Locale locale);

}
