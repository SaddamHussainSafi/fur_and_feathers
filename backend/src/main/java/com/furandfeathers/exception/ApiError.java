package com.furandfeathers.exception;

import java.time.Instant;
import java.util.List;

public class ApiError {
    public final Instant timestamp = Instant.now();
    public final int status;
    public final String error;
    public final String message;
    public final String path;
    public final List<String> details;

    public ApiError(int status, String error, String message, String path, List<String> details) {
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
        this.details = details;
    }
}
