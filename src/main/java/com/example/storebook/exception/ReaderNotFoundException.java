package com.example.storebook.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author pashtet
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ReaderNotFoundException extends RuntimeException {
    public ReaderNotFoundException(String message) {
        super(message);
    }

}
