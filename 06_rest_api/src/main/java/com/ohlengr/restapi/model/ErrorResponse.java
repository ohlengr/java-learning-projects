package com.ohlengr.restapi.model;

public class ErrorResponse {
    private final long status;
    private final String error;
    private final String message;

    public ErrorResponse(long status, String error, String message) {
        this.status = status;
        this.error = error;
        this.message = message;
    }

    public long getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }
}
