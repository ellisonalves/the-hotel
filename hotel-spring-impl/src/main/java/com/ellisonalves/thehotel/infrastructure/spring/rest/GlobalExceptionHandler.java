package com.ellisonalves.thehotel.infrastructure.spring.rest;

import static java.util.Arrays.asList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

import com.ellisonalves.thehotel.application.exceptions.ResourceNotFoundException;
import com.ellisonalves.thehotel.application.pojos.errors.ErrorMessageList;
import com.ellisonalves.thehotel.application.pojos.errors.ErrorPerFiledList;
import com.ellisonalves.thehotel.application.pojos.errors.ErrorsPerField;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSourceAccessor messageSourceAccessor;

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(Exception ex, WebRequest request) {
        debugException(ex, request);

        return createMessagesResponseEntity(ex, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        var messages = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(this::toErrorsPerField)
                .toList();

        return handleExceptionInternal(ex, new ErrorPerFiledList(messages), headers, HttpStatus.BAD_REQUEST,
                request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers,
            HttpStatusCode status, WebRequest request) {
        debugException(ex, request);

        if (body == null) {
            if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status))
                request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);

            return createMessagesResponseEntity(ex, headers, status);
        }

        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    private ResponseEntity<Object> createMessagesResponseEntity(Exception ex, HttpHeaders headers,
            HttpStatusCode status) {
        var body = ErrorMessageList.createSingleErrorMessageList(ex.getMessage());
        return new ResponseEntity<>(
                body,
                headers,
                status);
    }

    private ResponseEntity<Object> createMessagesResponseEntity(Exception ex, HttpStatus status) {
        HttpHeaders headers = new HttpHeaders();
        return createMessagesResponseEntity(ex, headers, status);
    }

    private void debugException(Exception exception, WebRequest request) {
        // log.debug("requestURI = {}, exception message = {}", request,
        // exception.getMessage());
    }

    private String parseCodeToMessage(String code) {
        try {
            return messageSourceAccessor.getMessage(code);
        } catch (NoSuchMessageException e) {
            // do nothing
            // if a message doesnt exist just don't use it.
        }
        return null;
    }

    private ErrorsPerField toErrorsPerField(FieldError fe) {
        String[] codes = fe.getCodes();
        if (codes == null || codes.length == 0) {
            return new ErrorsPerField(
                    fe.getField(),
                    asList(fe.getDefaultMessage()));
        }

        String parsedMessage = parseCodeToMessage(codes[0]);
        var message = parsedMessage == null ? fe.getDefaultMessage() : parsedMessage;

        return new ErrorsPerField(
                fe.getField(),
                asList(message));
    }

}