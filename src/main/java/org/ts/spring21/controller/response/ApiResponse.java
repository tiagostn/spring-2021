package org.ts.spring21.controller.response;

import lombok.Builder;
import lombok.Data;
import org.ts.spring21.exception.ExceptionDetails;

@Data
@Builder
public class ApiResponse {
    private Object data;
    private ExceptionDetails error;
}
