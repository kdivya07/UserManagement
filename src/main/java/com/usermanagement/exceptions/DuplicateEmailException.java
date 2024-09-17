package com.usermanagement.exceptions;

import lombok.Data;

@Data
public class DuplicateEmailException extends RuntimeException {

    public DuplicateEmailException(String message) {
        super(message);
    }
}
