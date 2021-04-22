package org.ts.spring21.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.ts.spring21.controller.response.ApiResponse;
import org.ts.spring21.exception.BusinessException;
import org.ts.spring21.exception.BusinessExceptionDetails;
import org.ts.spring21.exception.ValidationExceptionDetails;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiResponse> handlerBusinessException(BusinessException be) {
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> handlerMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
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
