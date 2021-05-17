package org.ts.spring21.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.ts.spring21.exception.ExceptionDetails;

@Data
@Builder
@AllArgsConstructor
public class ApiResponse<T> {
    private T data;
    private ExceptionDetails error;
}
