package com.respawn.hub.forum.exceptions;

public class DatabaseErrorException extends RuntimeException {

    public DatabaseErrorException(String message) {
        super(message);
    }

    public DatabaseErrorException(String message, Throwable cause) {
        super(message, cause);
    }

}
