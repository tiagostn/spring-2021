package org.ts.spring21.exception;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@EqualsAndHashCode
@SuperBuilder
public class ValidationExceptionDetails extends ExceptionDetails {
    private String fields;
    private String fieldsMessages;
}
