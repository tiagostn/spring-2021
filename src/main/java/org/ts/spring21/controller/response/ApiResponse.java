package org.ts.spring21.controller.response;

import lombok.Builder;
import lombok.Data;
import org.ts.spring21.exception.BusinessExceptionDetails;

@Data
@Builder
public class ApiResponse {
    private Object data;
    private BusinessExceptionDetails error;
}
