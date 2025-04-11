package com.yago.libsystem.core.response;

import lombok.Data;

@Data
public final class GenericResponse<T> {
    private boolean success;
    private String message;
    private T data;

    public GenericResponse() {}

    public GenericResponse(boolean success) {
        this.success = success;
    }

    public GenericResponse(boolean success, String message) {
        this(success);
        this.message = message;
    }

    public GenericResponse(boolean success, T data) {
        this(success);
        this.data = data;
    }

    public GenericResponse(boolean success, String message, T data) {
        this(success, message);
        this.data = data;
    }
}
