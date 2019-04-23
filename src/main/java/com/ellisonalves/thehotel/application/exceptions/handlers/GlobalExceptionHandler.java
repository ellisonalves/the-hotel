package com.ellisonalves.thehotel.application.exceptions.handlers;

import com.ellisonalves.thehotel.application.exceptions.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<ErrorInfo> handleNumberFormatException(HttpServletRequest request, Exception exception) {
        debugException(request, exception);

        return createBadRequestError(exception);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorInfo> handleResourceNotFoundException(HttpServletRequest request, Exception exception) {
        debugException(request, exception);

        return createBadRequestError(exception);
    }

    private ResponseEntity<ErrorInfo> createBadRequestError(Exception exception) {
        ErrorInfo errorInfo = new ErrorInfo(exception.getMessage(), ErrorInfo.Severity.ERROR);
        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }

    private void debugException(HttpServletRequest request, Exception exception) {
        log.debug("requestURI = {}, exception message = {}", request.getRequestURI(), exception.getMessage());
    }

}
