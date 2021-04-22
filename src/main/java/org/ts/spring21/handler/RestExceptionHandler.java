package org.ts.spring21.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.ts.spring21.controller.response.ApiResponse;
import org.ts.spring21.exception.BusinessException;
import org.ts.spring21.exception.BusinessExceptionDetails;

import java.time.LocalDateTime;

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
}
