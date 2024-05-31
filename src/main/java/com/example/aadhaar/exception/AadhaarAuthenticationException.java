

package com.example.aadhaar.exception;

import org.springframework.http.HttpStatusCode;

public class AadhaarAuthenticationException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private final HttpStatusCode status;

    public AadhaarAuthenticationException(String message, HttpStatusCode status) {
        super(message);
        this.status = status;
    }

    public HttpStatusCode getStatus() {
        return status;
    }
}
