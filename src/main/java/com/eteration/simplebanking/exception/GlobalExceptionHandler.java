package com.eteration.simplebanking.exception;

import com.eteration.simplebanking.exception.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Global exception handler for handling various exceptions and generating error responses.
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    /**
     * Handles the exception when the requested media type is not supported.
     *
     * @param ex      The HttpMediaTypeNotSupportedException that was thrown.
     * @param headers The HTTP headers.
     * @param status  The HTTP status.
     * @param request The web request.
     * @return A ResponseEntity with an error response for unsupported media type.
     */
    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
                                                                     HttpHeaders headers,
                                                                     HttpStatus status,
                                                                     WebRequest request) {
        log.error(ex.getMessage(), ex);

        List<String> details = new ArrayList<>();

        StringBuilder builder = new StringBuilder();
        builder.append(ex.getContentType());
        builder.append(" media type is not supported. Supported media types are ");
        ex.getSupportedMediaTypes().forEach(t -> builder.append(t).append(", "));

        details.add(builder.toString());

        ErrorResponse errorResponse = ErrorResponse.builder()
                .errorDetails(details)
                .message("Invalid JSON")
                .statusCode(status.value())
                .status(HttpStatus.valueOf(status.value()))
                .build();

        return ResponseEntity.status(status).body(errorResponse);
    }

    /**
     * Handles the exception when the HTTP message is not readable, often due to malformed JSON.
     *
     * @param ex      The HttpMessageNotReadableException that was thrown.
     * @param headers The HTTP headers.
     * @param status  The HTTP status.
     * @param request The web request.
     * @return A ResponseEntity with an error response for a malformed JSON request.
     */
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        log.error(ex.getMessage(), ex);


        List<String> details = new ArrayList<>();
        details.add(ex.getMessage());

        ErrorResponse errorResponse = ErrorResponse.builder()
                .errorDetails(details)
                .message("Malformed JSON request")
                .statusCode(status.value())
                .status(HttpStatus.valueOf(status.value()))
                .build();

        return ResponseEntity.status(status).body(errorResponse);
    }

    /**
     * Handles the exception when method arguments fail validation.
     *
     * @param ex      The MethodArgumentNotValidException that was thrown.
     * @param headers The HTTP headers.
     * @param status  The HTTP status.
     * @param request The web request.
     * @return A ResponseEntity with an error response for validation errors.
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        log.error(ex.getMessage(), ex);

        List<String> details = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getObjectName() + " : " + error.getDefaultMessage())
                .collect(Collectors.toList());

        ErrorResponse errorResponse = ErrorResponse.builder()
                .errorDetails(details)
                .message("Validation Errors")
                .statusCode(status.value())
                .status(HttpStatus.valueOf(status.value()))
                .build();

        return ResponseEntity.status(status).body(errorResponse);

    }

    /**
     * Handles the exception when a required request parameter is missing.
     *
     * @param ex      The MissingServletRequestParameterException that was thrown.
     * @param headers The HTTP headers.
     * @param status  The HTTP status.
     * @param request The web request.
     * @return A ResponseEntity with an error response for missing request parameters.
     */
    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
                                                                          HttpHeaders headers,
                                                                          HttpStatus status,
                                                                          WebRequest request) {

        log.error(ex.getMessage(), ex);

        List<String> details = new ArrayList<>();
        details.add(ex.getParameterName() + " parameter is missing");

        ErrorResponse errorResponse = ErrorResponse.builder()
                .errorDetails(details)
                .message("Validation Errors")
                .statusCode(status.value())
                .status(HttpStatus.valueOf(status.value()))
                .build();

        return ResponseEntity.status(status).body(errorResponse);
    }

    /**
     * Handles the custom exception InsufficientBalanceException.
     *
     * @param exception The InsufficientBalanceException that was thrown.
     * @return A ResponseEntity with an error response for insufficient balance.
     */
    @ExceptionHandler(InsufficientBalanceException.class)
    protected ResponseEntity<Object> handleNotFoundException(InsufficientBalanceException exception) {

        log.error(exception.getMessage(), exception);

        ErrorResponse errorResponse = ErrorResponse.builder()
                .message(exception.getMessage())
                .statusCode(HttpStatus.NOT_MODIFIED.value())
                .status(HttpStatus.NOT_MODIFIED)
                .build();

        return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(errorResponse);
    }

    /**
     * Handles the custom exception AccountNotFoundException.
     *
     * @param exception The AccountNotFoundException that was thrown.
     * @return A ResponseEntity with an error response for account not found.
     */
    @ExceptionHandler(AccountNotFoundException.class)
    protected ResponseEntity<Object> handleNotFoundException(AccountNotFoundException exception) {

        log.error(exception.getMessage(), exception);

        ErrorResponse errorResponse = ErrorResponse.builder()
                .message(exception.getMessage())
                .statusCode(HttpStatus.NOT_FOUND.value())
                .status(HttpStatus.NOT_FOUND)
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

}
