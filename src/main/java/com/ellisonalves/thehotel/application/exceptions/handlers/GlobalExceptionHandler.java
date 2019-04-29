package com.ellisonalves.thehotel.application.exceptions.handlers;

import com.ellisonalves.thehotel.application.exceptions.ResourceNotFoundException;
import com.ellisonalves.thehotel.application.exceptions.pojos.FieldMessages;
import com.ellisonalves.thehotel.application.exceptions.pojos.Message;
import com.ellisonalves.thehotel.application.exceptions.pojos.MessageSeverity;
import com.ellisonalves.thehotel.application.exceptions.pojos.Messages;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSourceAccessor messageSourceAccessor;

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(Exception ex, WebRequest request) {
        debugException(ex, request);

        return createMessagesResponseEntity(ex, HttpStatus.NOT_FOUND, MessageSeverity.ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<Messages> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError ->
                        {
                            List<Message> messages = asList(fieldError.getCodes())
                                    .stream()
                                    .map(code -> getMessage(code))
                                    .filter(message -> message != null)
                                    .collect(Collectors.toList());

                            return new Messages(fieldError.getField(), messages);
                        }
                )
                .collect(Collectors.toList());

        return handleExceptionInternal(ex, new FieldMessages(errors), headers, HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        debugException(ex, request);

        if (body == null) {
            if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status))
                request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);

            return createMessagesResponseEntity(ex, headers, status, MessageSeverity.ERROR);
        }

        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    private ResponseEntity<Object> createMessagesResponseEntity(Exception ex, HttpHeaders headers, HttpStatus status, MessageSeverity severity) {
        Messages body = new Messages(ex.getMessage(), severity);
        return new ResponseEntity<>(
                body,
                headers,
                status
        );
    }

    private ResponseEntity<Object> createMessagesResponseEntity(Exception ex, HttpStatus status, MessageSeverity severity) {
        HttpHeaders headers = new HttpHeaders();
        return createMessagesResponseEntity(ex, headers, status, severity);
    }

    private void debugException(Exception exception, WebRequest request) {
        log.debug("requestURI = {}, exception message = {}", request, exception.getMessage());
    }

    private Message getMessage(String code) {
        try {
            return new Message(messageSourceAccessor.getMessage(code), MessageSeverity.ERROR);
        } catch (NoSuchMessageException e) {
            // do nothing
            // if a message doesnt exist just don't use it.
        }
        return null;
    }

}