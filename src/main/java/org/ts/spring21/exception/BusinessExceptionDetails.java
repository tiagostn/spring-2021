package org.ts.spring21.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BusinessExceptionDetails {
    private String title;
    private int status;
    private String description;
    private LocalDateTime timestamp;
}
