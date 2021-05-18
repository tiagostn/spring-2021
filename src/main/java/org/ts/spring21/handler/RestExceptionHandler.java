package org.ts.spring21.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;
import org.ts.spring21.controller.response.ApiResponse;
import org.ts.spring21.exception.BusinessException;
import org.ts.spring21.exception.BusinessExceptionDetails;
import org.ts.spring21.exception.ExceptionDetails;
import org.ts.spring21.exception.ValidationExceptionDetails;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.web.context.request.RequestAttributes.SCOPE_REQUEST;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, SCOPE_REQUEST);
        }

        var apiResponse = ApiResponse.builder().error(
                ExceptionDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .status(status.value())
                        .title(ex.getCause() == null ? ex.getMessage() : ex.getCause().getMessage())
                        .description(ex.getMessage())
                        .build()
        ).build();

        return new ResponseEntity<>(apiResponse, headers, status);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiResponse<Object>> handleBusinessException(BusinessException be) {
        return new ResponseEntity<>(
                ApiResponse.builder().error(
                        BusinessExceptionDetails.builder()
                                .timestamp(LocalDateTime.now())
                                .status(HttpStatus.BAD_REQUEST.value())
                                .title("Business Exception")
                                .description(be.getMessage())
                                .build()
                ).build(),
                HttpStatus.BAD_REQUEST
        );
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<FieldError> errors = exception.getBindingResult().getFieldErrors();
        String fields = errors.stream().map(FieldError::getField).collect(Collectors.joining(", "));
        String fieldsMessages = errors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));
        return new ResponseEntity<>(
                ApiResponse.builder().error(
                        ValidationExceptionDetails.builder()
                                .timestamp(LocalDateTime.now())
                                .status(HttpStatus.BAD_REQUEST.value())
                                .title("Validation Exception")
                                .description("Check the field(s) errors")
                                .fields(fields)
                                .fieldsMessages(fieldsMessages)
                                .build()
                ).build(), HttpStatus.BAD_REQUEST
        );
    }
}
